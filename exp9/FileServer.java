
import java.net.*;
import java.io.*;
import java.nio.file.*;

class FileServer {
    public static void main(String[] args) throws Exception {
        ServerSocket ss = new ServerSocket(5004);
        System.out.println("File Server Started");

        while (true) {
            Socket s = ss.accept();

            new Thread(() -> {
                try {
                    BufferedReader file = new BufferedReader(
                        new FileReader("test.txt"));
                    PrintWriter out = new PrintWriter(
                        s.getOutputStream(), true);

                    String line;
                    while ((line = file.readLine()) != null)
                        out.println(line);

                    file.close();
                    s.close();
                } catch (Exception e) {
                    System.out.println("Error");
                }
            }).start();
        }
    }
}
