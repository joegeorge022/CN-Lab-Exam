import java.net.*;
import java.io.*;
import java.util.*;

class FileClient {
    public static void main(String[] args) throws Exception {
        Socket s = new Socket("localhost",5504);
        BufferedReader in = new BufferedReader(
            new InputStreamReader(s.getInputStream()));
        PrintWriter out = new PrintWriter(s.getOutputStream(),true);
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter filename: ");
        out.println(sc.nextLine());

        String line;
        while ((line=in.readLine())!=null) System.out.println(line);
        s.close();
    }
}
