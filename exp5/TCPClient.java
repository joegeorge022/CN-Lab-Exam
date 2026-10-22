import java.net.*;
import java.io.*;
import java.util.*;

class TCPClient {
    public static void main(String[] args) throws Exception {
        Socket s = new Socket("localhost",5500);
        DataOutputStream out = new DataOutputStream(s.getOutputStream());
        BufferedReader in = new BufferedReader(
            new InputStreamReader(s.getInputStream()));
        Scanner sc = new Scanner(System.in);
        Random r = new Random();

        System.out.print("Enter N: ");
        int n=sc.nextInt();
        out.writeInt(n);

        for(int i=0;i<n;i++)
            for(int j=0;j<n;j++) {
                int x=r.nextInt(50)+1;
                System.out.print(x+" ");
                out.writeInt(x);
            }

        out.flush();
        System.out.println("\nType: "+in.readLine());
        s.close();
    }
}
