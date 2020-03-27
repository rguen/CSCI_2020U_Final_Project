package project;

import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import javafx.scene.layout.Pane;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Client {

    //IO streams
    DataOutputStream toServer = null;
    DataInputStream fromServer = null;

    public Client(Pane pane) {

        // Creating a text pane for the clients to type and interact
        Pane textPane = new Pane();
        textPane.setPrefWidth(870);
        textPane.setPrefHeight(40);
        textPane.setLayoutX(5);
        textPane.setLayoutY(460);

        // Creating the text field where they can type
        TextField textField = new TextField();
        textField.setPrefWidth(860);
        textField.setPrefHeight(10);
        textField.setLayoutX(0);
        textField.setLayoutY(0);

        // Creating the area where the text will be displayed
        TextArea textBox = new TextArea();
        textBox.setPrefWidth(840);
        ScrollPane scrollBox = new ScrollPane(textBox);
        scrollBox.setPrefWidth(860);
        scrollBox.setPrefHeight(50);
        scrollBox.setLayoutX(0);
        scrollBox.setLayoutY(30);

        textPane.getChildren().addAll(textField, scrollBox);
        pane.getChildren().addAll(textPane);


        textField.setOnAction(e -> {
            try {
                // Get the text from the text field
                String messageSent = textField.getText();

                // Send the radius to the Server
                toServer.writeUTF(messageSent);
                toServer.flush();

                // Getting the text from the server
                String messageReceived = fromServer.readUTF();
                textBox.appendText(messageReceived);


//                //Display to the text area
//                ta.appendText("Radius is " + radius + '\n');
//                ta.appendText("Area received from the server is " + area + '\n');


            } catch (IOException ex) {
                System.err.println(ex);
            }
        });

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
