
import java.net.*;

class UDPClient {
    public static void main(String[] args) throws Exception {
        DatagramSocket ds = new DatagramSocket();

        byte[] b = "Hello Server".getBytes();
        InetAddress ip = InetAddress.getByName("localhost");

        ds.send(new DatagramPacket(b, b.length, ip, 5001));

        byte[] r = new byte[1024];
        DatagramPacket p = new DatagramPacket(r, r.length);
        ds.receive(p);

        System.out.println("Server: " +
            new String(p.getData(), 0, p.getLength()));

        ds.close();
    }
}
