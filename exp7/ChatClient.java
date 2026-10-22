import java.net.*;
import java.io.*;

class ChatClient {
    public static void main(String[] args) throws Exception {
        Socket s = new Socket("localhost",5502);
        BufferedReader in = new BufferedReader(
            new InputStreamReader(s.getInputStream()));
        PrintWriter out = new PrintWriter(s.getOutputStream(),true);
        BufferedReader kb = new BufferedReader(
            new InputStreamReader(System.in));

        new Thread(() -> {
            try {
                String m;
                while ((m=in.readLine())!=null) System.out.println(m);
            } catch (Exception e) {}
        }).start();

        String m;
        while ((m=kb.readLine())!=null) {
            if (m.equalsIgnoreCase("exit")) break;
            out.println(m);
        }
        s.close();
    }
}
