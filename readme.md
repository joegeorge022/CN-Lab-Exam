# CN LAB EXAM

| Experiment | Title | Description |
|---|---|---|
| Exp 5 | Client-Server Communication Using TCP | The client inputs an integer N and creates an N × N square matrix populated with random numbers in the range [1,50]. It sends the matrix to the server, which identifies whether it is an upper triangular, lower triangular, or diagonal matrix. The server sends the matrix type as a string to the client, which prints it. |
| Exp 6 | Client-Server Communication Using UDP | The client inputs a new-generation English sentence containing abbreviations and sends it to the server. The server translates the sentence into formal English and sends the translated sentence back to the client, which prints it. Only the following abbreviations need to be considered: tbh, ig, tbf, atm, irl, lol, asap, omg, ttyl, idk, nvm. Sample input: "Really idc about this stupid server as it is of no use irl but atm, I will design one, tbf to the professor." Expected output: "Really I don't care about this stupid server as it is of no use in real life but at the moment, I will design one, to be fair to the professor." |
| Exp 7 | Multi-User Chat Server Using TCP | Implement a multi-user chat server using TCP as the transport layer protocol. |
| Exp 8 | Concurrent Time Server Using UDP | Implement a concurrent time server application using UDP to execute the program on a remote server. The client sends a time request to the server, which returns its system time. The client then displays the received time value. |
| Exp 9 | Concurrent File Server | Develop a concurrent file server that provides the file requested by the client if it exists. If the file does not exist, the server sends an appropriate message to the client. The server must also send its process ID (PID) to the client for display along with either the file contents or the error message. |


## Algorithms:

### Experiment 5: Client–Server Communication Using TCP

#### Client Algorithm
1. Start.
2. Create a TCP socket and connect to the server.
3. Read the order N of the matrix.
4. Generate an N x N matrix with random numbers from 1 to 50.
5. Display the matrix and send N and its elements to the server.
6. Receive the matrix type from the server.
7. Display the matrix type.
8. Close the socket and stop.

#### Server Algorithm
1. Start.
2. Create a ServerSocket and wait for a client connection.
3. Accept the client connection.
4. Receive N and the matrix elements.
5. Check whether the matrix is diagonal, upper triangular, or lower triangular.
6. Send the matrix type to the client.
7. Close the socket and stop.


### Experiment 6: Client–Server Communication Using UDP

#### Client Algorithm
1. Start.
2. Create a UDP socket.
3. Read a new-generation English sentence from the user.
4. Send the sentence to the server using a datagram packet.
5. Receive the translated sentence from the server.
6. Display the translated sentence.
7. Close the socket and stop.

#### Server Algorithm
1. Start.
2. Create a UDP socket and bind it to a port.
3. Wait for a sentence from the client.
4. Receive the sentence and convert it into a string.
5. Replace the specified abbreviations with their formal English equivalents.
6. Send the translated sentence back to the client.
7. Close the socket and stop.


### Experiment 7: Multi-user Chat Server Using TCP

#### Client Algorithm
1. Start.
2. Create a TCP socket and connect to the chat server.
3. Create input and output streams.
4. Start a separate thread to receive and display messages from the server.
5. Read messages entered by the user and send them to the server.
6. If the user enters "exit", close the connection.
7. Stop.

#### Server Algorithm
1. Start.
2. Create a ServerSocket and listen for client connections.
3. Accept a client connection.
4. Create a separate thread to handle the client.
5. Add the client's output stream to the list of connected clients.
6. Receive messages from the client.
7. Broadcast each message to all connected clients.
8. Remove disconnected clients and close their sockets.
9. Continue accepting new clients.


### Experiment 8: Concurrent Time Server Using UDP

#### Client Algorithm
1. Start.
2. Create a UDP socket.
3. Send a time request to the server using a datagram packet.
4. Wait for the server's response.
5. Receive and display the server's system time.
6. Close the socket and stop.

#### Server Algorithm
1. Start.
2. Create a UDP socket and bind it to a port.
3. Wait for a time request from a client.
4. Receive the request and obtain the client's IP address and port.
5. Create a separate thread to handle the request.
6. Obtain the server's current system time.
7. Send the time to the client using a datagram packet.
8. Continue waiting for requests from other clients.


### Experiment 9: Concurrent File Server

#### Client Algorithm
1. Start.
2. Create a TCP socket and connect to the file server.
3. Read the requested filename from the user.
4. Send the filename to the server.
5. Receive the server's process ID (PID) and response.
6. Display the PID and the file contents or the file-not-found message.
7. Close the socket and stop.

#### Server Algorithm
1. Start.
2. Create a ServerSocket and obtain the server's process ID.
3. Listen for incoming client connections.
4. Accept a client connection.
5. Create a separate thread to handle the client request.
6. Receive the requested filename.
7. Send the server's PID to the client.
8. Check whether the requested file exists.
9. If the file exists, read and send its contents; otherwise, send a file-not-found message.
10. Close the client connection and continue accepting requests.


# Experiment 5: Client–Server Communication Using TCP

### TCPServer.java

```java
import java.net.*;
import java.io.*;

class TCPServer {
    public static void main(String[] args) throws Exception {
        ServerSocket ss = new ServerSocket(5500);
        Socket s = ss.accept();
        DataInputStream in = new DataInputStream(s.getInputStream());
        PrintWriter out = new PrintWriter(s.getOutputStream(), true);

        int n = in.readInt();
        int[][] a = new int[n][n];

        for (int i=0; i<n; i++)
            for (int j=0; j<n; j++)
                a[i][j] = in.readInt();

        boolean u=true, l=true, d=true;
        for (int i=0; i<n; i++)
            for (int j=0; j<n; j++) {
                if (i>j && a[i][j]!=0) u=false;
                if (i<j && a[i][j]!=0) l=false;
                if (i!=j && a[i][j]!=0) d=false;
            }

        out.println(d ? "Diagonal" : u ? "Upper Triangular" :
                    l ? "Lower Triangular" : "Neither");
        s.close();
        ss.close();
    }
}
```

### TCPClient.java

```java
import java.net.*;
import java.io.*;
import java.util.*;

class TCPClient {
    public static void main(String[] args) throws Exception {
        Socket s = new Socket("localhost", 5500);
        DataOutputStream out = new DataOutputStream(s.getOutputStream());
        BufferedReader in = new BufferedReader(
            new InputStreamReader(s.getInputStream()));
        Scanner sc = new Scanner(System.in);
        Random r = new Random();

        System.out.print("Enter N: ");
        int n = sc.nextInt();
        out.writeInt(n);

        for (int i=0; i<n; i++) {
            for (int j=0; j<n; j++) {
                int x = r.nextInt(50)+1;
                System.out.print(x+" ");
                out.writeInt(x);
            }
            System.out.println();
        }

        out.flush();
        System.out.println("Type: "+in.readLine());
        s.close();
    }
}
```

# Experiment 6: Client–Server Communication Using UDP

### UDPServer.java

```java
import java.net.*;

class UDPServer {
    public static void main(String[] args) throws Exception {
        DatagramSocket ds = new DatagramSocket(5501);

        String[] a = {"tbh","ig","tbf","atm","irl","lol",
                      "asap","omg","ttyl","idk","nvm"};
        String[] b = {"to be honest","I guess","to be fair",
                      "at the moment","in real life","laughing out loud",
                      "as soon as possible","oh my God",
                      "talk to you later","I don't know","never mind"};

        byte[] buf = new byte[2048];
        DatagramPacket p = new DatagramPacket(buf, buf.length);
        ds.receive(p);

        String msg = new String(p.getData(), 0, p.getLength());

        for (int i=0; i<a.length; i++)
            msg = msg.replaceAll("(?i)\\b"+a[i]+"\\b", b[i]);

        byte[] reply = msg.getBytes();
        ds.send(new DatagramPacket(reply, reply.length,
            p.getAddress(), p.getPort()));
        ds.close();
    }
}
```

### UDPClient.java

```java
import java.net.*;
import java.util.*;

class UDPClient {
    public static void main(String[] args) throws Exception {
        DatagramSocket ds = new DatagramSocket();
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter sentence: ");
        byte[] b = sc.nextLine().getBytes();

        InetAddress ip = InetAddress.getByName("localhost");
        ds.send(new DatagramPacket(b, b.length, ip, 5501));

        byte[] r = new byte[2048];
        DatagramPacket p = new DatagramPacket(r, r.length);
        ds.receive(p);

        System.out.println("Formal English: "+
            new String(p.getData(), 0, p.getLength()));
        ds.close();
    }
}
```

# Experiment 7: Multi-user Chat Server Using TCP

### ChatServer.java

```java
import java.net.*;
import java.io.*;
import java.util.*;

class ChatServer {
    static Vector<PrintWriter> clients = new Vector<>();

    public static void main(String[] args) throws Exception {
        ServerSocket ss = new ServerSocket(5502);

        while (true) {
            Socket s = ss.accept();

            new Thread(() -> {
                PrintWriter out = null;
                try {
                    BufferedReader in = new BufferedReader(
                        new InputStreamReader(s.getInputStream()));
                    out = new PrintWriter(s.getOutputStream(), true);
                    clients.add(out);

                    String msg;
                    while ((msg = in.readLine()) != null)
                        synchronized (clients) {
                            for (PrintWriter c : clients)
                                c.println(msg);
                        }
                } catch (Exception e) {
                } finally {
                    if (out != null) clients.remove(out);
                    try { s.close(); } catch (Exception e) {}
                }
            }).start();
        }
    }
}
```

### ChatClient.java

```java
import java.net.*;
import java.io.*;

class ChatClient {
    public static void main(String[] args) throws Exception {
        Socket s = new Socket("localhost", 5502);
        BufferedReader in = new BufferedReader(
            new InputStreamReader(s.getInputStream()));
        PrintWriter out = new PrintWriter(s.getOutputStream(), true);
        BufferedReader kb = new BufferedReader(
            new InputStreamReader(System.in));

        new Thread(() -> {
            try {
                String m;
                while ((m = in.readLine()) != null)
                    System.out.println(m);
            } catch (Exception e) {}
        }).start();

        String m;
        while ((m = kb.readLine()) != null) {
            if (m.equalsIgnoreCase("exit")) break;
            out.println(m);
        }
        s.close();
    }
}
```

# Experiment 8: Concurrent Time Server Using UDP

### TimeServer.java

```java
import java.net.*;
import java.util.*;

class TimeServer {
    public static void main(String[] args) throws Exception {
        DatagramSocket ds = new DatagramSocket(5503);

        while (true) {
            DatagramPacket p = new DatagramPacket(new byte[100], 100);
            ds.receive(p);

            new Thread(() -> {
                try {
                    byte[] t = new Date().toString().getBytes();
                    synchronized (ds) {
                        ds.send(new DatagramPacket(t, t.length,
                            p.getAddress(), p.getPort()));
                    }
                } catch (Exception e) {}
            }).start();
        }
    }
}
```

### TimeClient.java

```java
import java.net.*;

class TimeClient {
    public static void main(String[] args) throws Exception {
        DatagramSocket ds = new DatagramSocket();
        byte[] b = "TIME".getBytes();

        ds.send(new DatagramPacket(b, b.length,
            InetAddress.getByName("localhost"), 5503));

        DatagramPacket p = new DatagramPacket(new byte[100], 100);
        ds.receive(p);

        System.out.println("Server Time: "+
            new String(p.getData(), 0, p.getLength()));
        ds.close();
    }
}
```

# Experiment 9: Concurrent File Server

### FileServer.java

```java
import java.net.*;
import java.io.*;

class FileServer {
    public static void main(String[] args) throws Exception {
        ServerSocket ss = new ServerSocket(5504);
        long pid = ProcessHandle.current().pid();

        while (true) {
            Socket s = ss.accept();

            new Thread(() -> {
                try {
                    BufferedReader in = new BufferedReader(
                        new InputStreamReader(s.getInputStream()));
                    PrintWriter out = new PrintWriter(
                        s.getOutputStream(), true);

                    String name = in.readLine();
                    out.println("PID: "+pid);

                    File f = new File(name);
                    if (f.isFile()) {
                        BufferedReader fr = new BufferedReader(
                            new FileReader(f));
                        String line;
                        while ((line = fr.readLine()) != null)
                            out.println(line);
                        fr.close();
                    } else {
                        out.println("File not found!");
                    }
                    s.close();
                } catch (Exception e) {}
            }).start();
        }
    }
}
```

### FileClient.java

```java
import java.net.*;
import java.io.*;
import java.util.*;

class FileClient {
    public static void main(String[] args) throws Exception {
        Socket s = new Socket("localhost", 5504);
        BufferedReader in = new BufferedReader(
            new InputStreamReader(s.getInputStream()));
        PrintWriter out = new PrintWriter(s.getOutputStream(), true);
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter filename: ");
        out.println(sc.nextLine());

        String line;
        while ((line = in.readLine()) != null)
            System.out.println(line);

        s.close();
    }
}
```

## How to Run

Compile the client and server files for each experiment:

```bash
javac TCPServer.java TCPClient.java
```

Run the server in one terminal:

```bash
java TCPServer
```

Run the client in another terminal:

```bash
java TCPClient
```

Use the appropriate filenames for each experiment. Start the server before running the client.
