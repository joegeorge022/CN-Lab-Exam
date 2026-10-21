
import java.net.*;
import java.util.*;

class TimeServer {
    public static void main(String[] args) throws Exception {
        DatagramSocket ds = new DatagramSocket(5003);
        System.out.println("Time Server Started");

        while (true) {
            byte[] b = new byte[100];
            DatagramPacket p = new DatagramPacket(b, b.length);
            ds.receive(p);

            new Thread(() -> {
                try {
                    String time = new Date().toString();
                    byte[] r = time.getBytes();

                    ds.send(new DatagramPacket(r, r.length,
                        p.getAddress(), p.getPort()));
                } catch (Exception e) {}
            }).start();
        }
    }
}
