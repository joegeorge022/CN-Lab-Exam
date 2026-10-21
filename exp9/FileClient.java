
import java.net.*;
import java.io.*;

class FileClient {
    public static void main(String[] args) throws Exception {
        Socket s = new Socket("localhost", 5004);

        BufferedReader in = new BufferedReader(
            new InputStreamReader(s.getInputStream()));

        String line;
        while ((line = in.readLine()) != null)
            System.out.println(line);

        s.close();
    }
}
