
import java.net.*;
import java.io.*;
import java.util.*;

class ChatServer {
    static ArrayList<PrintWriter> clients = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        ServerSocket ss = new ServerSocket(5002);
        System.out.println("Chat Server Started");

        while (true) {
            Socket s = ss.accept();

            new Thread(() -> {
                try {
                    BufferedReader in = new BufferedReader(
                        new InputStreamReader(s.getInputStream()));
                    PrintWriter out = new PrintWriter(
                        s.getOutputStream(), true);

                    synchronized (clients) {
                        clients.add(out);
                    }

                    String msg;
                    while ((msg = in.readLine()) != null) {
                        synchronized (clients) {
                            for (PrintWriter c : clients)
                                c.println(msg);
                        }
                    }
                } catch (Exception e) {
                    System.out.println("Client disconnected");
                }
            }).start();
        }
    }
}
