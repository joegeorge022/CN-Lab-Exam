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
