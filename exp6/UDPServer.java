
import java.net.*;

class UDPServer {
    public static void main(String[] args) throws Exception {
        DatagramSocket ds = new DatagramSocket(5001);
        byte[] b = new byte[1024];

        DatagramPacket p = new DatagramPacket(b, b.length);
        ds.receive(p);

        System.out.println("Client: " +
            new String(p.getData(), 0, p.getLength()));

        byte[] reply = "Hello Client".getBytes();
        ds.send(new DatagramPacket(reply, reply.length,
            p.getAddress(), p.getPort()));

        ds.close();
    }
}
