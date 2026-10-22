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
