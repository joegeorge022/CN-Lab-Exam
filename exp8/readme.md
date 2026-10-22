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
