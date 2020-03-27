package project;

import java.io.*;
import java.net.*;
import java.util.ArrayList;

public class Server {

    private int clientNumber = 0;

    ArrayList<HandleAClient> clients = new ArrayList<>();

    public Server() {

        new Thread( () -> {
            try {

                // Create a server socket
                ServerSocket serverSocket = new ServerSocket(7868);

                while (true) {

                    // New connection
                    Socket socket = serverSocket.accept();

                    // Increase client number
                    clientNumber++;

                    HandleAClient client = new HandleAClient(socket, clientNumber);
                    clients.add(client);
                    // Create and start a new thread for the connection
                    new Thread(client).start();

                }
            }
            catch(IOException ex) {
                System.err.println(ex);
            }
        }).start();
    }

    // Define the thread class for handling new connection
    class HandleAClient implements Runnable {
        private Socket socket;
        private int num;
        DataInputStream inputFromClient = null;
        DataOutputStream outputToClient = null;

        /** Construct a thread */
        public HandleAClient(Socket socket, int num) {
            this.num = num;
            this.socket = socket;
        }

        /** Run a thread */
        public void run() {
            try {

                // Create data input and output streams
                inputFromClient = new DataInputStream(socket.getInputStream());
                outputToClient = new DataOutputStream(socket.getOutputStream());

                // Continuously serve the client
                while (true) {

                    // Receive text from the client
                    String messageReceived = inputFromClient.readUTF();

                    for(int i = 0; i < clients.size(); i++) {
                        clients.get(i).outputToClient.writeUTF("Player" + i + messageReceived + "\n");
                    }
                }

            }
            catch(IOException ex) {
                ex.printStackTrace();
            }
        }
    }

}