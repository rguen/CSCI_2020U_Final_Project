package project;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.*;
import java.util.Scanner;


public class GameState extends Application {

    // Initializing all scenes needed for game
    Scene startMenuScene;
    Scene cpuDifficultyScene;
    Scene onlineMenuScene;
    Scene gameScene;
    Scene gameOverScene;

    // Make sure the String "userPath" is changed depending on the user
    //String userPath = "E:/Wyatt/Eclipse_Workspace/2020_Final_project";

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
        Image imageStart = new Image("battleship_start_background.png");
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
        Image onlineButtonImage = new Image("online_image.png");
        ImageView onlineButtonImageView = new ImageView(onlineButtonImage);

        Image cpuButtonImage = new Image("/Images/CPU_image.png");
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
        String row = "";
        BufferedReader csvReader = new BufferedReader(new FileReader("high_scores.csv"));
        while ((row = csvReader.readLine()) != null) {
            String[] data = row.split(",");
            label.setText(data[0]);
        }
        csvReader.close();
    }



    // Setting the CPU button action
    public void SetButtonActionCPU(Button button, Stage primaryStage) {
        button.setOnAction(e -> {
            Pane cpuPane = new Pane();
            InitializeCPUMenuPane(cpuPane, primaryStage);
            cpuDifficultyScene = new Scene(cpuPane, 750, 425);
            primaryStage.setScene(cpuDifficultyScene);
        });
    }



    // Setting the online button action
    public void SetButtonActionOnline(Button button, Stage primaryStage) {
        button.setOnAction(e -> {
            Pane pane1 = new Pane();
            gameScene = new Scene(pane1, 750, 425);
            primaryStage.setScene(gameScene);
        });
    }
    
    // ================ Online Menu Functions and Initialization ================ //

    // This function initializes the pane that is used in the cpu selection scene of the game
    public void InitializeOnlineMenuPane(Pane pane, Stage primaryStage) {

        // Initializing the pane layout for the CPU Scene
        pane.setLayoutX(0);
        pane.setLayoutY(-120);

        // Creating and displaying the background for the cpu selection scene
        Image imageBackground = new Image("battleship_grid.png");
        ImageView imageViewStart = new ImageView(imageBackground);
        imageViewStart.setFitWidth(800);
        imageViewStart.setFitHeight(550);

        Label multiLabel = new Label("Multiplayer");
        multiLabel.setStyle("-fx-text-fill: #07004C;");
        multiLabel.setFont(Font.font("Rockwell Extra Bold", FontWeight.BOLD, 30));
        multiLabel.setLayoutX(260);
        multiLabel.setLayoutY(190);

        // Initializing CPU difficulty buttons
        Button hostButton = new Button("Host");
        hostButton.setStyle("-fx-text-fill: #07004C; -fx-border-color: #07004C;");
        hostButton.setFont(Font.font("Rockwell Extra Bold", FontWeight.BOLD, 15));
        hostButton.setLayoutX(322);
        hostButton.setLayoutY(250);
        SetButtonActionHost(hostButton, primaryStage);

        Button joinButton = new Button("Join");
        joinButton.setStyle("-fx-text-fill: #07004C; -fx-border-color: #07004C;");
        joinButton.setFont(Font.font("Rockwell Extra Bold", FontWeight.BOLD, 15));
        joinButton.setLayoutX(325);
        joinButton.setLayoutY(300);
        SetButtonActionJoin(joinButton, primaryStage);

        Button backButton = new Button("Back");
        backButton.setStyle("-fx-text-fill: #07004C; -fx-border-color: #07004C;");
        backButton.setFont(Font.font("Rockwell Extra Bold", FontWeight.BOLD, 15));
        backButton.setLayoutX(335);
        backButton.setLayoutY(350);
        SetButtonActionBack(backButton, primaryStage);

        // Adding to the pane
        pane.getChildren().addAll(imageViewStart, multiLabel, hostButton, joinButton, backButton);
    }



    // Setting the play button action for host
    public void SetButtonActionHost(Button button, Stage primaryStage) {
        button.setOnAction(e -> {
            Pane gamePane = new Pane();
            Human p1 = new Human("Player 1", 1);
            CPU p2 = new CPU("Computer Player", 2, p1);
            InitializeGamePane(gamePane, p1, p2, primaryStage);
            new Server();
            new Client();
            gameScene = new Scene(gamePane, 870, 470);
            primaryStage.setScene(gameScene);
        });
    }



    // Setting the play button action for join
    public void SetButtonActionJoin(Button button, Stage primaryStage) {
        button.setOnAction(e -> {
            Pane gamePane = new Pane();
            Human p1 = new Human("Player 1", 1);
            CPU p2 = new CPU("Computer Player", 2, p1);
            InitializeGamePane(gamePane, p1, p2, primaryStage);
            new Client();
            gameScene = new Scene(gamePane, 870, 470);
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
        Image imageBackground = new Image("battleship_grid.png");
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
        SetButtonActionPlayCPUBeginner(beginnerButton, primaryStage);

        Button intermediateButton = new Button("Intermediate");
        intermediateButton.setStyle("-fx-text-fill: #07004C; -fx-border-color: #07004C;");
        intermediateButton.setFont(Font.font("Rockwell Extra Bold", FontWeight.BOLD, 15));
        intermediateButton.setLayoutX(300);
        intermediateButton.setLayoutY(300);
        SetButtonActionPlayCPUIntermediate(intermediateButton, primaryStage);

        Button backButton = new Button("Back");
        backButton.setStyle("-fx-text-fill: #07004C; -fx-border-color: #07004C;");
        backButton.setFont(Font.font("Rockwell Extra Bold", FontWeight.BOLD, 15));
        backButton.setLayoutX(335);
        backButton.setLayoutY(350);
        SetButtonActionBack(backButton, primaryStage);

        // Adding to the pane
        pane.getChildren().addAll(imageViewStart, difficultyLabel, beginnerButton, intermediateButton, backButton);
    }



    // Setting the play button action for the beginner difficulty CPU
    public void SetButtonActionPlayCPUBeginner(Button button, Stage primaryStage) {
        button.setOnAction(e -> {
            Pane gamePane = new Pane();
            Human p1 = new Human("Player 1", 1);
            CPU p2 = new CPU("Computer Player", 2, p1);
            InitializeGamePane(gamePane, p1, p2, primaryStage);
            gameScene = new Scene(gamePane, 870, 470);
            primaryStage.setScene(gameScene);
        });
    }



    // Setting the play button action for the beginner difficulty CPU
    public void SetButtonActionPlayCPUIntermediate(Button button, Stage primaryStage) {
        button.setOnAction(e -> {
            Pane gamePane = new Pane();
            Human p1 = new Human("Player 1", 1);
            CPU p2 = new CPU("Computer Player", 2, p1);
            InitializeGamePane(gamePane, p1, p2, primaryStage);
            gameScene = new Scene(gamePane, 870, 470);
            primaryStage.setScene(gameScene);
        });
    }



    // Creating a back button function. This function will set the given button's action to return to the inputted
    // scene.
    public void SetButtonActionBack(Button button, Stage primaryStage) {
        button.setOnAction(e -> {
            primaryStage.setScene(startMenuScene);
        });
    }



    // ================ Gameplay Functions and Initialization ================ //

    // This function initializes the pane that is used in the gameplay scene of the application
    public void InitializeGamePane(Pane pane, Player p1, CPU p2, Stage primaryStage) {

        // Generating a menu for "Instructions" so the player can go over how to play battleship whenever they would
        // like during the game
        final Menu menu1 = new Menu("Options");
        MenuItem instructions = new MenuItem("Instructions");
        instructions.setOnAction(e -> {
            try {
                DisplayInstructions();
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            }
        });

        // Closing and restarting the application so there are no errors
        MenuItem quit = new MenuItem("Quit");
        quit.setOnAction(e -> {
            primaryStage.close();
            try {
                start(new Stage());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        // Adding the "instructions" and "quit" menu with a menu mar to the pane
        menu1.getItems().addAll(instructions, quit);
        MenuBar menu_bar = new MenuBar();
        menu_bar.getMenus().addAll(menu1);
        pane.getChildren().addAll(menu_bar);

        // Generating a game board for each player
        Text playerBoard = new Text("Player Board");
        playerBoard.setX(20);
        playerBoard.setY(40);
        Board boardP1 = new Board();
        boardP1.displayBoard(pane, 20, 50, p2, p1);

        Text CPUBoard = new Text("CPU Board");
        CPUBoard.setX(450);
        CPUBoard.setY(40);
        Board boardP2 = new Board();
        boardP2.displayBoard(pane, 450, 50, p1, p2);
        pane.getChildren().addAll(CPUBoard, playerBoard);

        // Displaying battleship instructions at the beginning of the game
        try {
            DisplayInstructions();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        
        /*
         *  This section will be for implementing the feature for adding pieces to the board
         */
        
        //Game play start
        
        System.out.println("" + p1.isTurn() + " " + p2.isTurn());
        boolean gameOver = false;
        p2.setBoard(boardP2.getBoard());
        if(p1.getLives() == 0) {
        	gameOver = true;
        	//Get the game over window. 
        }
        if(p2.getLives() == 0) {
        	gameOver = true;
        	//Get the game over window
        	
        }
        
    }

    public void endGamePane(Player humanPlayer, int score, Stage primaryStage) {
    	int endScore = humanPlayer.getScore();
    	boolean isDead = (humanPlayer.getLives() == 0);
    	
    	Pane gameOverPane = new Pane();
    	if(isDead) {
    		Text gameOver = new Text("Game Over");
    	    gameOver.setX(gameOverPane.getMaxWidth()/2-50);
    	    gameOver.setY(gameOverPane.getMaxHeight()/2-50);
    	    gameOver.setStyle("-fx-text-fill: #07004C; -fx-border-color: #07004C;");
    	    gameOver.setFont(Font.font("Rockwell Extra Bold", FontWeight.BOLD, 50));
    	    Text lose = new Text("You Lose. Your final score is: " + endScore);
    	    lose.setX(gameOverPane.getMaxWidth()/2-50);
    	    lose.setY(gameOverPane.getMaxHeight()/2-120);
    	    lose.setStyle("-fx-text-fill: #07004C; -fx-border-color: #07004C;");
    	    lose.setFont(Font.font("Rockwell Extra Bold", FontWeight.BOLD, 25));
    	    
    	    gameOverPane.getChildren().addAll(gameOver, lose);
    	}else {
    		Text gameOver = new Text("Game Over");
   	     	gameOver.setX(gameOverPane.getMaxWidth()/2);
   	     	gameOver.setY(gameOverPane.getMaxHeight()/2);
   	     	gameOver.setStyle("-fx-text-fill: #07004C; -fx-border-color: #07004C;");
   	     	gameOver.setFont(Font.font("Rockwell Extra Bold", FontWeight.BOLD, 50));
   	     	Text win = new Text("You Win! Your score is: " + endScore);
   	     	win.setX(gameOverPane.getMaxWidth()/2-50);
   	     	win.setY(gameOverPane.getMaxHeight()/2-120);
   	     	win.setStyle("-fx-text-fill: #07004C; -fx-border-color: #07004C;");
   	     	win.setFont(Font.font("Rockwell Extra Bold", FontWeight.BOLD, 25));
   	     	
   	     	gameOverPane.getChildren().addAll(gameOver, win);
    	}
    	Button backButton = new Button("Back");
        backButton.setStyle("-fx-text-fill: #07004C; -fx-border-color: #07004C;");
        backButton.setFont(Font.font("Rockwell Extra Bold", FontWeight.BOLD, 15));
        backButton.setLayoutX(335);
        backButton.setLayoutY(350);
        
        SetButtonActionBack(backButton, primaryStage);
        gameOverPane.getChildren().addAll(backButton);
        gameOverScene = new Scene(gameOverPane, 870, 470);
        primaryStage.setScene(gameOverScene);
    }

    // Using FileIO to retrieve battleship instructions from a text file so they can be displayed during the game for
    // any player to view
    public void DisplayInstructions() throws FileNotFoundException {

        // Make sure the String "userPath" is changed depending on the user
        File file = new File("battleship_instructions.txt");

        String str = "";
        Scanner input = new Scanner(file);
        while (input.hasNext()){
            str += input.nextLine() + "\n";
        }
        input.close();

        // Creating new alert to display the battleship instructions
        Alert alert = new Alert(Alert.AlertType.INFORMATION, str);
        alert.setHeaderText("======== Battleship Instructions ========");
        alert.showAndWait();
    }

}
