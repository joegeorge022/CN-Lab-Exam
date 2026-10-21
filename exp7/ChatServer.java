import java.net.*;
import java.io.*;
import java.util.*;

class ChatServer {
    static Vector<PrintWriter> clients = new Vector<>();

    public static void main(String[] args) throws Exception {
        ServerSocket ss = new ServerSocket(5502);
        System.out.println("Chat Server Started");

        while (true) {
            Socket s = ss.accept();

            new Thread(() -> {
                PrintWriter out = null;
                try {
                    BufferedReader in = new BufferedReader(
                        new InputStreamReader(s.getInputStream()));
                    out = new PrintWriter(s.getOutputStream(), true);
                    clients.add(out);

                    String msg;
                    while ((msg = in.readLine()) != null) {
                        for (PrintWriter c : clients)
                            c.println(msg);
                    }
                } catch (Exception e) {
                    System.out.println("Client disconnected");
                } finally {
                    if (out != null) clients.remove(out);
                    try { s.close(); } catch (Exception e) {}
                }
            }).start();
        }
    }
}
