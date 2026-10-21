
import java.net.*;
import java.io.*;

class TCPClient {
    public static void main(String[] args) throws Exception {
        Socket s = new Socket("localhost", 5000);

        DataInputStream in = new DataInputStream(s.getInputStream());
        DataOutputStream out = new DataOutputStream(s.getOutputStream());

        out.writeUTF("Hello Server");
        System.out.println("Server: " + in.readUTF());

        s.close();
    }
}
