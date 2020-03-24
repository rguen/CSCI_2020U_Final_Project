/* sample call
for (int i = 0; i < 4; i++){
    Piece ship[i] = new Piece(i);
}
*/
public class Piece {
    private int width;
    private int length;
    boolean[] hitbox;
    public Piece(int length) {
        width = 1;
        this.length = length + 2;
        boolean [] hitbox = new boolean[this.length];
        //use images here
    }

    //swaps width and length
    //due to symmetry 2 90 degree rotations does not change anything so simply swapping the variables is sufficient
    //ideally the topleft space is always where the mouse is
    public void rotate() {
        int temp = length;
        length = width;
        width = temp;
        //swap orientation of images here
    }

    //returns the state of a single space of a ship
    private boolean checkHit(boolean hitbox){
        return hitbox;
    }

    //calls isHit method for everyspot to see if every spot is sunk
    public boolean isSunk() {
        for (int i = 2; i < this.length; i++) {
            if (!checkHit(hitbox[i])){
                return false;
            }
        }
        return true;
    }

    //uses the detection of where the player clicks and decreases the hitbox appropriately
    public void hitDetect() {

    }

    //places the hitboxes for the ships appropriately where the player clicks
    public void placeHitbox() {

    }


}
