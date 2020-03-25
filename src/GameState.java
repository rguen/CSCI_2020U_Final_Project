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

        // Loading the start menu
        Pane startMenuPane = new Pane();
        InitializeStartMenuPane(startMenuPane, primaryStage);

        // Setting the scene
        startMenuScene = new Scene(startMenuPane, 750, 425);
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



    // ================ Start Menu Functions and Initialization ================ //

    // This function initializes the pane that is used in the start menu scene of the game
    public void InitializeStartMenuPane(Pane pane, Stage primaryStage) {

        // Initializing a pane for start menu
        pane.setLayoutX(0);
        pane.setLayoutY(-140);

        // Creating and displaying the start menu background for battleship
        Image imageStart = new Image("\\Images\\battleship_start_background.png");
        ImageView imageViewStart = new ImageView(imageStart);
        imageViewStart.setFitWidth(750);
        imageViewStart.setFitHeight(700);

        // Adding the start menu and buttons to the pane
        pane.getChildren().addAll(imageViewStart);
        StartMenuButtons(pane, primaryStage);
    }



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



    // Setting the CPU button action
    public void SetButtonActionCPU(Button button, Stage primaryStage) {
        Pane cpuPane = new Pane();
        InitializeCPUMenuPane(cpuPane, primaryStage);
        cpuDifficultyScene = new Scene(cpuPane, 750, 425);
        button.setOnAction(e -> {
            primaryStage.setScene(cpuDifficultyScene);
        });
    }



    // Setting the online button action
    public void SetButtonActionOnline(Button button, Stage primaryStage) {
        Pane pane1 = new Pane();
        gameScene = new Scene(pane1, 750, 425);
        button.setOnAction(e -> {
            primaryStage.setScene(gameScene);
        });
    }



    // ================ CPU Menu Functions and Initialization ================ //

    // This function initializes the pane that is used in the cpu selection scene of the game
    public void InitializeCPUMenuPane(Pane pane, Stage primaryStage) {

        // Initializing the pane layout for the CPU Scene
        pane.setLayoutX(0);
        pane.setLayoutY(-120);

        // Creating and displaying the background for the cpu selection scene
        Image imageBackground = new Image("\\Images\\battleship_grid.png");
        ImageView imageViewStart = new ImageView(imageBackground);
        imageViewStart.setFitWidth(800);
        imageViewStart.setFitHeight(550);

        Label difficultyLabel = new Label("Select Difficulty");
        difficultyLabel.setStyle("-fx-text-fill: #07004C;");
        difficultyLabel.setFont(Font.font("Rockwell Extra Bold", FontWeight.BOLD, 30));
        difficultyLabel.setLayoutX(240);
        difficultyLabel.setLayoutY(190);

        // Initializing CPU difficulty buttons
        Button beginnerButton = new Button("Beginner");
        beginnerButton.setStyle("-fx-text-fill: #07004C; -fx-border-color: #07004C;");
        beginnerButton.setFont(Font.font("Rockwell Extra Bold", FontWeight.BOLD, 15));
        beginnerButton.setLayoutX(320);
        beginnerButton.setLayoutY(250);

        Button intermediateButton = new Button("Intermediate");
        intermediateButton.setStyle("-fx-text-fill: #07004C; -fx-border-color: #07004C;");
        intermediateButton.setFont(Font.font("Rockwell Extra Bold", FontWeight.BOLD, 15));
        intermediateButton.setLayoutX(300);
        intermediateButton.setLayoutY(300);

        Button backButton = new Button("Back");
        backButton.setStyle("-fx-text-fill: #07004C; -fx-border-color: #07004C;");
        backButton.setFont(Font.font("Rockwell Extra Bold", FontWeight.BOLD, 15));
        backButton.setLayoutX(335);
        backButton.setLayoutY(350);
        SetButtonActionBack(backButton, primaryStage);

        // Adding to the pane
        pane.getChildren().addAll(imageViewStart, difficultyLabel, beginnerButton, intermediateButton, backButton);
    }



    // Creating a back button function. This function will set the given button's action to return to the inputted
    // scene.
    public void SetButtonActionBack(Button button, Stage primaryStage) {
        button.setOnAction(e -> {
            primaryStage.setScene(startMenuScene);
        });
    }



    // ================ Gameplay Functions and Initialization ================ //

    // Setting the play button action
    public void SetButtonActionPlay(Button button, Stage primaryStage) {
        Pane gamePane = new Pane();
        gameScene = new Scene(gamePane, 800, 800);
        button.setOnAction(e -> {
            primaryStage.setScene(gameScene);
        });
    }

    // This function initializes the pane that is used in the gameplay scene of the application
    public void InitializeGamePane(Pane pane, Player p1, Player p2) {

    }

}
