import java.net.*;

class TimeClient {
    public static void main(String[] args) throws Exception {
        DatagramSocket ds = new DatagramSocket();
        ds.setSoTimeout(5000);

        byte[] b = "TIME".getBytes();
        InetAddress ip = InetAddress.getByName("localhost");

        ds.send(new DatagramPacket(b, b.length, ip, 5503));

        byte[] r = new byte[100];
        DatagramPacket p = new DatagramPacket(r, r.length);
        ds.receive(p);

        System.out.println("Server Time: " +
            new String(p.getData(), 0, p.getLength()));

        ds.close();
    }
}
