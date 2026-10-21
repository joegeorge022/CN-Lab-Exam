import java.net.*;
import java.io.*;

class FileServer {
    public static void main(String[] args) throws Exception {
        ServerSocket ss = new ServerSocket(5504);
        long pid = ProcessHandle.current().pid();

        System.out.println("File Server Started. PID: " + pid);

        while (true) {
            Socket s = ss.accept();

            new Thread(() -> {
                try {
                    BufferedReader in = new BufferedReader(
                        new InputStreamReader(s.getInputStream()));
                    PrintWriter out = new PrintWriter(
                        s.getOutputStream(), true);

                    String filename = in.readLine();
                    File file = new File(filename);

                    out.println("Server PID: " + pid);

                    if (file.isFile()) {
                        out.println("File found:");
                        BufferedReader fr = new BufferedReader(
                            new FileReader(file));

                        String line;
                        while ((line = fr.readLine()) != null)
                            out.println(line);

                        fr.close();
                    } else {
                        out.println("File not found!");
                    }

                    s.close();
                } catch (Exception e) {
                    System.out.println("Error");
                }
            }).start();
        }
    }
}
