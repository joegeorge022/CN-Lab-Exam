import java.net.*;

class UDPServer {
    public static void main(String[] args) throws Exception {
        DatagramSocket ds = new DatagramSocket(5501);
        String[] a={"tbh","ig","tbf","atm","irl","lol","asap","omg","ttyl","idk","nvm"};
        String[] b={"to be honest","I guess","to be fair","at the moment",
            "in real life","laughing out loud","as soon as possible",
            "oh my God","talk to you later","I don't know","never mind"};

        byte[] buf=new byte[2048];
        DatagramPacket p=new DatagramPacket(buf,buf.length);
        ds.receive(p);
        String msg=new String(p.getData(),0,p.getLength());

        for(int i=0;i<a.length;i++)
            msg=msg.replaceAll("(?i)\\b"+a[i]+"\\b",b[i]);

        byte[] reply=msg.getBytes();
        ds.send(new DatagramPacket(reply,reply.length,p.getAddress(),p.getPort()));
        ds.close();
    }
}
