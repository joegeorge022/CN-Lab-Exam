import java.net.*;
import java.io.*;

class ChatClient {
    public static void main(String[] args) throws Exception {
        Socket s = new Socket("localhost", 5502);

        BufferedReader in = new BufferedReader(
            new InputStreamReader(s.getInputStream()));
        PrintWriter out = new PrintWriter(
            s.getOutputStream(), true);
        BufferedReader kb = new BufferedReader(
            new InputStreamReader(System.in));

        new Thread(() -> {
            try {
                String msg;
                while ((msg = in.readLine()) != null)
                    System.out.println(msg);
            } catch (Exception e) {}
        }).start();

        String msg;
        while ((msg = kb.readLine()) != null) {
            if (msg.equalsIgnoreCase("exit")) break;
            out.println(msg);
        }
        s.close();
    }
}
