/* sample call
    Piece ship = new Piece(i);
*/
import javafx.scene.shape.Rectangle;

public class Piece {
    private int width;
    private int length;
    boolean isHorizontal = true;
    boolean[] hitbox;
    public Piece(int length) {
        width = 1;
        this.length = length;
        boolean [] hitbox = new boolean[this.length];
        //use images here
    }

    //swaps width and length
    //due to symmetry 2 90 degree rotations does not change anything so simply swapping the variables is sufficient
    //ideally the top left space is always where the mouse is
    public void rotate() {
        int temp = length;
        length = width;
        width = temp;
        isHorizontal = !isHorizontal;
        //swap orientation of images here
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

    //uses the detection of where the player clicks and updates the hitbox appropriately
    public void hitDetect() {
        if(board[i][j] == "Hit") {

        }
    }

    //places the hit boxes for the ships appropriately where the player clicks
    //returns the success value of if the ship was successfully placed or not
    /*
    * How to bind hit box with specific board spot
    * HOW TO GET BOARDSIZE
    *
    * */
    public boolean checkValid(int x, int y, Rectangle[][] board) {
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
                if(board[j][y].getId() != "Empty"){
                    return false;
                }
            }
        }
        else  {
            for (int k = x; k < hitbox.length; k++) {
                if(board[x][k].getId() != "Empty"){
                    return false;
                }
            }
        }

        //if none of the previous conditions are violated the piece can successfully be placed
        return true;
    }
    //directly link hitbox 1 to 1 with board space
    public void placePiece()
}
