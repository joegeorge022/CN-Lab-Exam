import java.net.*;
import java.io.*;
import java.util.*;

class TCPClient {
    public static void main(String[] args) throws Exception {
        Socket s = new Socket("localhost", 5500);
        DataOutputStream out = new DataOutputStream(s.getOutputStream());
        BufferedReader in = new BufferedReader(
            new InputStreamReader(s.getInputStream()));
        Scanner sc = new Scanner(System.in);
        Random r = new Random();

        System.out.print("Enter N: ");
        int n = sc.nextInt();
        out.writeInt(n);

        int[][] a = new int[n][n];
        System.out.println("Matrix:");

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                a[i][j] = r.nextInt(50) + 1;
                System.out.print(a[i][j] + "\t");
                out.writeInt(a[i][j]);
            }
            System.out.println();
        }

        out.flush();
        System.out.println("Matrix Type: " + in.readLine());
        s.close();
    }
}
