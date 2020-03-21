import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class GameState extends Application {
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
        StartMenuButtons(pane);

        // Setting the scene
        Scene scene = new Scene(pane, 750, 425);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Battleship");
        primaryStage.show();
    }
// ======== To ADD ========
//    - Initialize Start Screen (load high score, best time, play buttons, etc...)
//    - Play function (play against CPU/other player)
//    - Load Preset (loads different game boards based on selected difficulty - small/medium/large)
//    - Win/Lose function
//    - Time function (best time saved to .csv)
//    - Score function (best score saved to .csv)

    public void LoadHighScore() {

    }

    public void StartMenuButtons(Pane pane) {

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

        Button cpuButton = new Button("Play CPU", cpuButtonImageView);
        cpuButton.setStyle("-fx-text-fill: #07004C; -fx-border-color: #07004C;");
        cpuButton.setFont(Font.font("Rockwell Extra Bold", FontWeight.BOLD, 15));
        cpuButton.setLayoutX(303);
        cpuButton.setLayoutY(395);

        // Adding buttons to the start menu pane
        pane.getChildren().addAll(onlineButton, cpuButton);
    }
}
