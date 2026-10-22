import java.net.*;
import java.io.*;
import java.util.*;

class ChatServer {
    static Vector<PrintWriter> clients = new Vector<>();

    public static void main(String[] args) throws Exception {
        ServerSocket ss = new ServerSocket(5502);
        while (true) {
            Socket s = ss.accept();
            new Thread(() -> {
                try {
                    BufferedReader in = new BufferedReader(
                        new InputStreamReader(s.getInputStream()));
                    PrintWriter out = new PrintWriter(
                        s.getOutputStream(), true);
                    clients.add(out);
                    String msg;
                    while ((msg = in.readLine()) != null)
                        for (PrintWriter c : clients) c.println(msg);
                    clients.remove(out);
                    s.close();
                } catch (Exception e) {}
            }).start();
        }
    }
}
