import java.net.*;
import java.io.*;

class FileServer {
    public static void main(String[] args) throws Exception {
        ServerSocket ss = new ServerSocket(5504);
        long pid = ProcessHandle.current().pid();

        while (true) {
            Socket s = ss.accept();
            new Thread(() -> {
                try {
                    BufferedReader in = new BufferedReader(
                        new InputStreamReader(s.getInputStream()));
                    PrintWriter out = new PrintWriter(
                        s.getOutputStream(),true);
                    String name = in.readLine();
                    out.println("PID: "+pid);

                    File f = new File(name);
                    if (f.isFile()) {
                        BufferedReader fr = new BufferedReader(new FileReader(f));
                        String line;
                        while ((line=fr.readLine())!=null) out.println(line);
                        fr.close();
                    } else out.println("File not found!");

                    s.close();
                } catch (Exception e) {}
            }).start();
        }
    }
}
