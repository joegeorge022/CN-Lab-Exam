import java.net.*;
import java.io.*;

class TCPServer {
    public static void main(String[] args) throws Exception {
        ServerSocket ss = new ServerSocket(5500);
        Socket s = ss.accept();

        DataInputStream in = new DataInputStream(s.getInputStream());
        PrintWriter out = new PrintWriter(s.getOutputStream(), true);

        int n = in.readInt();
        int[][] a = new int[n][n];

        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                a[i][j] = in.readInt();

        boolean upper = true, lower = true, diagonal = true;

        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++) {
                if (i > j && a[i][j] != 0) upper = false;
                if (i < j && a[i][j] != 0) lower = false;
                if (i != j && a[i][j] != 0) diagonal = false;
            }

        String type = diagonal ? "Diagonal Matrix" :
                      upper ? "Upper Triangular Matrix" :
                      lower ? "Lower Triangular Matrix" :
                      "Neither triangular nor diagonal";

        out.println(type);
        s.close();
        ss.close();
    }
}
