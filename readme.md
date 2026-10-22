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
