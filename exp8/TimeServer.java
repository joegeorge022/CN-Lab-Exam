import java.net.*;
import java.util.*;

class TimeServer {
    public static void main(String[] args) throws Exception {
        DatagramSocket ds = new DatagramSocket(5503);
        System.out.println("Time Server Started");

        while (true) {
            byte[] b = new byte[100];
            DatagramPacket p = new DatagramPacket(b, b.length);
            ds.receive(p);

            InetAddress ip = p.getAddress();
            int port = p.getPort();

            new Thread(() -> {
                try {
                    byte[] time = new Date().toString().getBytes();
                    synchronized (ds) {
                        ds.send(new DatagramPacket(
                            time, time.length, ip, port));
                    }
                } catch (Exception e) {}
            }).start();
        }
    }
}
