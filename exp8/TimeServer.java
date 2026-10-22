import java.net.*;
import java.util.*;

class TimeServer {
    public static void main(String[] args) throws Exception {
        DatagramSocket ds = new DatagramSocket(5503);
        while (true) {
            DatagramPacket p = new DatagramPacket(new byte[100],100);
            ds.receive(p);
            new Thread(() -> {
                try {
                    byte[] t = new Date().toString().getBytes();
                    ds.send(new DatagramPacket(t,t.length,
                        p.getAddress(),p.getPort()));
                } catch (Exception e) {}
            }).start();
        }
    }
}
