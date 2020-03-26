/* sample call
    Piece ship = new Piece(i);
*/
package project;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;

public class Piece {
    private int width;
    private int length;
    private boolean isHorizontal = true;
    private boolean[] hitbox;
    private int[] xHitbox;
    private int[] yHitbox;
    ImageView shipImageView;

    public Piece(int length) {
        width = 1;
        this.length = length;
        this.hitbox = new boolean[this.length];
        this.xHitbox = new int[this.length];
        this.yHitbox = new int[this.length];

        String filename = "\\Images\\battleship0" + (length - 1) + ".png";
        Image shipImage = new Image(filename);
        this.shipImageView = new ImageView(shipImage);

        //stretches the images to fit the respective spaces
        //will make the images look a bit weird as it does not preserve aspect ratio
        if(isHorizontal) {
            shipImageView.setFitWidth(length * Board.TILE_SIZE);
            shipImageView.setFitHeight(Board.TILE_SIZE);
        }
        else {
            shipImageView.setFitWidth(Board.TILE_SIZE);
            shipImageView.setFitHeight(length * Board.TILE_SIZE);
        }
    }

    //swaps width and length
    //due to symmetry 2 90 degree rotations does not change anything so simply swapping the variables is sufficient
    //ideally the top left space is always where the mouse is
    public void rotate() {
        int temp = length;                  //swap the variables every time this method is called
        length = width;
        width = temp;
        isHorizontal = !isHorizontal;       //swaps between true and false
        //swap orientation of images here
        shipImageView.setRotate(90);

    }

    //returns the state of a single space of a ship
    private boolean checkHit(boolean hitbox){
        return hitbox;
    }

    //calls isHit method for every spot to see if every spot is sunk
    public boolean isSunk() {
        for (int i = 0; i < this.length; i++) {
            if (!checkHit(hitbox[i])){
                return false;
            }
        }
        return true;
    }

    //returns the success value of if the ship was successfully placed
    public boolean checkValid(int x, int y, Board board) {
        Rectangle[][] pBoard = board.getBoard();
        //checking to see if the ship goes over the edges of the board
        if (isHorizontal && hitbox.length + x > Board.BOARD_SIZE) {
            return false;
        }
        if (!isHorizontal && hitbox.length + y > Board.BOARD_SIZE) {
            return false;
        }

        //checking to see if a ship has already been placed there
        if (isHorizontal) {
            for (int j = x; j < hitbox.length; j++) {
                if(pBoard[j][y].getId() != "Empty"){
                    return false;
                }
            }
        }
        else  {
            for (int k = x; k < hitbox.length; k++) {
                if(pBoard[x][k].getId() != "Empty"){
                    return false;
                }
            }
        }

        //if none of the previous conditions are violated the piece can successfully be placed
        return true;
    }

    //marks space (x, y) on the board as being occupied then marks the proceeding spaces
    public void placePiece(int x, int y, Board board) {
        Rectangle[][] pBoard = board.getBoard();
        if(isHorizontal) {  //if the ship is placed horizontal the y coordinate will be constant
            for (int j = x; j < x + this.length; j++) {
                pBoard[j][y].setId("Ship");
                xHitbox[j - x] = j;     //sets the corresponding x coordinate for the hitbox
                yHitbox[j - x] = y;     //sets the corresponding y coordinate for the hitbox
            }
        }
        else {              //if the ship is placed vertically the x coordinate will be constant
            for (int k = y; k < y + this.length; k++) {
                pBoard[x][k].setId("Ship");
                xHitbox[k - y] = x;
                yHitbox[k - y] = k;
            }
        }
    }

    //randomizes where the piece goes
    public void randomizePiecePosition(Board board) {
        Rectangle[][] pBoard = board.getBoard();
        int xRand;
        int yRand;
        do {
            //generating random x and y coordinates over and over until
            xRand = (int)(Math.random() * Board.BOARD_SIZE + 1); //max - min + 1
            yRand = (int)(Math.random() * Board.BOARD_SIZE + 1);
        } while(!this.checkValid(xRand, yRand, board)); //check if the coordinates are valid
        placePiece(xRand, yRand, board); //places the piece if the coordinates are valid
    }

    //NOTE THIS IS SUPER INEFFICIENT YOU HAVE TO CALL THIS METHOD FOR EVERY SHIP UNTIL YOU FIND THE PIECE HIT
    //scan the board to check for any hits on the ship
    //returns if the specific hit box has been found
    public boolean markHit(int x, int y) {
        for(int j = 0; j < Board.BOARD_SIZE; j++) {         //looping through the whole board
            for(int k = 0; k < Board.BOARD_SIZE; k++) {     //looping through the whole board
                for (int i = 0; i < this.length; i++){      //looping through the individual ship hitboxes
                    if(j == xHitbox[i] && k == yHitbox[i]){
                        hitbox[i] = true;
                        return true;
                    }
                }
            }
        }
        return false;
    }
    //display different stuff when ship is sunk
    //make sure only player has ships showing
}
