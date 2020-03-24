import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Board {

    // Initializing variables
    private Rectangle[][] board;
    static final int BOARD_SIZE = 8;
    static final int TILE_SIZE = 50;

    // Board constructor - Initializes an 8x8 board
    public Board() {
        board = new Rectangle[BOARD_SIZE][BOARD_SIZE];
    }

    // This class displays the playing board on the given pane. The x and y variables allow you to to change the
    // coordinates/positioning of the board.
    public void displayBoard(Pane pane, int x, int y) {

        // xTilePos and yTilePos are two variables used to control the location of each individual tile within
        // the board
        int xTilePos = 0;
        int yTilePos = 0;

        // Initializing each tile in the board as a rectangle
        for(int i = 0; i < BOARD_SIZE; i++) {
            for(int j = 0; j < BOARD_SIZE; j++) {
                board[i][j] = new Rectangle();
                board[i][j].setWidth(TILE_SIZE);
                board[i][j].setHeight(TILE_SIZE);
                board[i][j].setStroke(Color.BLACK);
                board[i][j].setFill(Color.LIGHTSKYBLUE);

                // Setting the x and y coordinates of each tile
                board[i][j].setX(x + xTilePos);
                board[i][j].setY(y + yTilePos);

                // Adding tile to the pane
                pane.getChildren().addAll(board[i][j]);

                // Increasing the coordinates so tiles don't overlap
                yTilePos += TILE_SIZE;
            }
            xTilePos += TILE_SIZE;
            yTilePos = 0;
        }
    }

    /*

    MORE TO COME - FEEL FREE TO ADD (We need to add boardState)

     */
}
