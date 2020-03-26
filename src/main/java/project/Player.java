import javafx.scene.shape.Rectangle;

public interface Player {
	//This method will be called once at the start of the game for each player
	//This is when the player will place their ships on the board
	public void gameStart();
	
	//Makes the move on the board. Board could be a  2D String array
	
	
	//Checks to  see if the move was a hit. Updates self if hit was take
	public boolean hit(int x, int y);
	
	//player type variable. Human or CPU
	public String playerType = "";
	
	//Starting number of ships. Decreases as ships are destroyed. when 0, player loses
	public int getLives();
	
	public void reduceLives();
	
	//return true if it is the player's turn. False if it is not
	public boolean isTurn();
	
	public void startTurn();
	
	public void setTurn(boolean torf);
	
	//Returns Human or CPU
	public String getPlayerType();
	
	public int getPlayerNumber();
	
	//set the player type
	public String setPlayerType(String playerType);
	
}