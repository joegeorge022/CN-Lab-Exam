
import java.net.*;

class TimeClient {
    public static void main(String[] args) throws Exception {
        DatagramSocket ds = new DatagramSocket();

        byte[] b = new byte[100];
        InetAddress ip = InetAddress.getByName("localhost");

        ds.send(new DatagramPacket(b, b.length, ip, 5003));

        DatagramPacket p = new DatagramPacket(b, b.length);
        ds.receive(p);

        System.out.println("Time: " +
            new String(p.getData(), 0, p.getLength()));

        ds.close();
    }
}
