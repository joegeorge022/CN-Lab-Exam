import java.net.*;

class UDPServer {
    public static void main(String[] args) throws Exception {
        DatagramSocket ds = new DatagramSocket(5501);
        byte[] b = new byte[2048];

        DatagramPacket p = new DatagramPacket(b, b.length);
        ds.receive(p);

        String msg = new String(p.getData(), 0, p.getLength());

        String[] shortWords = {
            "tbh", "ig", "tbf", "atm", "irl", "lol",
            "asap", "omg", "ttyl", "idk", "nvm"
        };
        String[] fullWords = {
            "to be honest", "I guess", "to be fair",
            "at the moment", "in real life", "laughing out loud",
            "as soon as possible", "oh my God",
            "talk to you later", "I don't know", "never mind"
        };

        for (int i = 0; i < shortWords.length; i++)
            msg = msg.replaceAll("(?i)\\b" + shortWords[i] + "\\b",
                                 fullWords[i]);

        byte[] reply = msg.getBytes();
        ds.send(new DatagramPacket(reply, reply.length,
            p.getAddress(), p.getPort()));

        ds.close();
    }
}
