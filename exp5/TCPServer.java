import java.net.*;
import java.io.*;

class TCPServer {
    public static void main(String[] args) throws Exception {
        ServerSocket ss = new ServerSocket(5500);
        Socket s = ss.accept();
        DataInputStream in = new DataInputStream(s.getInputStream());
        PrintWriter out = new PrintWriter(s.getOutputStream(), true);

        int n = in.readInt(), a[][] = new int[n][n];
        for (int i=0;i<n;i++)
            for (int j=0;j<n;j++) a[i][j]=in.readInt();

        boolean u=true,l=true,d=true;
        for(int i=0;i<n;i++)
            for(int j=0;j<n;j++) {
                if(i>j && a[i][j]!=0) u=false;
                if(i<j && a[i][j]!=0) l=false;
                if(i!=j && a[i][j]!=0) d=false;
            }

        out.println(d?"Diagonal":u?"Upper Triangular":
                    l?"Lower Triangular":"Neither");
        s.close(); ss.close();
    }
}
