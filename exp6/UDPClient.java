import java.net.*;
import java.util.*;

class UDPClient {
    public static void main(String[] args) throws Exception {
        DatagramSocket ds=new DatagramSocket();
        Scanner sc=new Scanner(System.in);

        System.out.print("Enter sentence: ");
        byte[] b=sc.nextLine().getBytes();
        InetAddress ip=InetAddress.getByName("localhost");
        ds.send(new DatagramPacket(b,b.length,ip,5501));

        byte[] r=new byte[2048];
        DatagramPacket p=new DatagramPacket(r,r.length);
        ds.receive(p);
        System.out.println(new String(p.getData(),0,p.getLength()));
        ds.close();
    }
}
