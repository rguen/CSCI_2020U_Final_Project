import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class GameState extends Application {

    // Initializing all scenes needed for game
    Scene startMenuScene;
    Scene cpuDifficultyScene;
    Scene gameScene;

    @Override
    public void start(Stage primaryStage) throws Exception {

        // Initializing a grid pane for start menu
        Pane pane = new Pane();
        pane.setLayoutX(0);
        pane.setLayoutY(-140);

        // Creating and displaying the start menu background for battleship
        Image imageStart = new Image("\\Images\\battleship_start_background.png");
        ImageView imageViewStart = new ImageView(imageStart);
        imageViewStart.setFitWidth(750);
        imageViewStart.setFitHeight(700);

        // Adding the start menu and buttons to the grid pane
        pane.getChildren().addAll(imageViewStart);
        StartMenuButtons(pane, primaryStage);

        // Setting the scene
        startMenuScene = new Scene(pane, 750, 425);
        primaryStage.setScene(startMenuScene);
        primaryStage.setTitle("Battleship");
        primaryStage.show();
    }
// ======== To ADD ========
//    - Initialize Start Screen (load high score, best time, play buttons, etc...) <- DONE
//    - Play function (play against CPU/other player)
//    - Load Preset (loads different game boards based on selected difficulty - small/medium/large)
//    - Win/Lose function
//    - Time function (best time saved to .csv)
//    - Score function (best score saved to .csv)

    // A function to load the start menu for battleship
    public void StartMenuButtons(Pane pane, Stage primaryStage) {

        // Initializing images for start menu buttons
        Image onlineButtonImage = new Image("\\Images\\online_image.png");
        ImageView onlineButtonImageView = new ImageView(onlineButtonImage);

        Image cpuButtonImage = new Image("\\Images\\CPU_image.png");
        ImageView cpuButtonImageView = new ImageView(cpuButtonImage);

        // Initializing start menu buttons
        Button onlineButton = new Button("Play Online", onlineButtonImageView);
        onlineButton.setStyle("-fx-text-fill: #07004C; -fx-border-color: #07004C;");
        onlineButton.setFont(Font.font("Rockwell Extra Bold", FontWeight.BOLD, 15));
        onlineButton.setLayoutX(290);
        onlineButton.setLayoutY(315);
        SetButtonActionOnline(onlineButton, primaryStage);

        Button cpuButton = new Button("Play CPU", cpuButtonImageView);
        cpuButton.setStyle("-fx-text-fill: #07004C; -fx-border-color: #07004C;");
        cpuButton.setFont(Font.font("Rockwell Extra Bold", FontWeight.BOLD, 15));
        cpuButton.setLayoutX(303);
        cpuButton.setLayoutY(395);
        SetButtonActionCPU(cpuButton, primaryStage);

        // Creating a label for the current high score, which is saved in a .csv file named "high_scores"
        Label scoreLabel = new Label();
        try {
            LoadHighScore(scoreLabel);
        } catch (IOException e) {
            e.printStackTrace();
        }
        scoreLabel.setStyle("-fx-text-fill: #07004C;");
        scoreLabel.setFont(Font.font("Rockwell Extra Bold", FontWeight.BOLD, 15));
        scoreLabel.setLayoutX(665);
        scoreLabel.setLayoutY(144);

        // Adding buttons and high score to the start menu pane
        pane.getChildren().addAll(onlineButton, cpuButton, scoreLabel);
    }

    // A function used to retrieve the high score from a .csv file
    public void LoadHighScore(Label label) throws IOException {

        // Make sure the String "userPath" is changed depending on the user
        String userPath = "\\Users\\rguen";
        String row = "";
        BufferedReader csvReader = new BufferedReader(new FileReader("C:" + userPath +
                "\\CSCI_2020U_Final_Project\\src\\Database\\high_scores.csv"));
        while ((row = csvReader.readLine()) != null) {
            String[] data = row.split(",");
            label.setText(data[0]);
        }
        csvReader.close();
    }

    public void SetButtonActionCPU(Button button, Stage primaryStage) {
        Pane pane1 = new Pane();
        cpuDifficultyScene = new Scene(pane1, 750, 425);
        button.setOnAction(e -> {
            primaryStage.setScene(cpuDifficultyScene);
        });
    }

    public void SetButtonActionOnline(Button button, Stage primaryStage) {
        Pane pane1 = new Pane();
        gameScene = new Scene(pane1, 750, 425);
        button.setOnAction(e -> {
            primaryStage.setScene(gameScene);
        });
    }
}
