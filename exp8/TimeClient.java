import java.net.*;

class TimeClient {
    public static void main(String[] args) throws Exception {
        DatagramSocket ds = new DatagramSocket();
        byte[] b = "TIME".getBytes();

        ds.send(new DatagramPacket(b, b.length,
            InetAddress.getByName("localhost"), 5503));

        DatagramPacket p = new DatagramPacket(new byte[100], 100);
        ds.receive(p);

        System.out.println("Server Time: " +
            new String(p.getData(), 0, p.getLength()));
        ds.close();
    }
}
