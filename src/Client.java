import javafx.scene.layout.Pane;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Client {

    //IO streams
    DataOutputStream toServer = null;
    DataInputStream fromServer = null;

    public Client() {

        try {

            // Creating a socket connection to the server
            Socket socket = new Socket("localhost", 7868);

            //Create an input stream to receive data to the server
            fromServer = new DataInputStream(socket.getInputStream());

            //Create an output stream to send data to the server
            toServer = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
