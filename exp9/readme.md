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
