package project;

import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class Board {

    // Initializing variables
    private Rectangle[][] board;
    static final int BOARD_SIZE = 8;
    static final int TILE_SIZE = 50;
    private Image imageRectangle = new Image("/Images/water.jpg");
    
    public int boardNum;

    public Rectangle[][] getBoard(){
    	return this.board;
    }
    
    
    // Board constructor - Initializes an 8x8 board
    public Board() {
        board = new Rectangle[BOARD_SIZE][BOARD_SIZE];
    }

    // This class displays the playing board on the given pane. The x and y variables allow you to to change the
    // coordinates/positioning of the board.
    public void displayBoard(Pane pane, int x, int y, Player p1, Player p2) {
    	
    	this.boardNum = p1.getPlayerNumber();
    	
        // xTilePos and yTilePos are two variables used to control the location of each individual tile within
        // the board
        int xTilePos = 0;
        int yTilePos = 0;

        // Initializing each tile in the board as a rectangle
        for(int i = 0; i < BOARD_SIZE; i++) {
            for(int j = 0; j < BOARD_SIZE; j++) {
            	board[i][j] = new Rectangle();
            	Rectangle cell = board[i][j];
                
            	cell.setWidth(TILE_SIZE);
            	cell.setHeight(TILE_SIZE);
            	cell.setStroke(Color.BLACK);
            	cell.setFill(new ImagePattern(imageRectangle));
            	cell.setId("Empty");
            	
                
                //adding mouse event listener to the rectangle
                cell.setOnMouseClicked(new EventHandler<MouseEvent>() {
                	public void handle(MouseEvent t) {
                		if(p2.isTurn()) {
                			
                    		System.out.println("Clicked on " + cell.getId() );
                    		if(cell.getId() == "Empty") {
                    			cell.setFill(Color.WHITE);                    			
                    			cell.setId("touched");
                    			p1.setTurn(true);
                    			p2.setTurn(false);
                    			
                    			p1.startTurn();
                    			p2.setTurn(true);
                    			p1.setTurn(false);
                    			
                    			
                    		}
                    		if(cell.getId() == "Ship") {
                    			cell.setFill(Color.RED);
                    			cell.setId("touched");
                    			p1.reduceLives();
                    			p2.updateScore();
                    			p1.setTurn(true);
                    			p2.setTurn(false);
                    			p1.startTurn();
                    			p2.setTurn(true);
                    			p1.setTurn(false);
                    			
                    		}
                    	}else {
                    		System.out.println("This is your board");
                    	}
                		System.out.println("" + p1.isTurn() + " " + p2.isTurn());
                	}
                		
                });

                // Setting the x and y coordinates of each tile
                cell.setX(x + xTilePos);
                cell.setY(y + yTilePos);

                // Adding tile to the pane
                pane.getChildren().addAll(cell);

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
