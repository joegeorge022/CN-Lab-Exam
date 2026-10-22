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
