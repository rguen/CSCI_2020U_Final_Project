import javafx.scene.shape.Rectangle;

public class Human implements Player {
	
	private String name = "";
	public String playerType = "Human";
	private int playerNumber; // 1 or 2
	public int numLives = 17; // same for both human and CPU

	public boolean isTurn;
	
	public Rectangle[][] playableBoard;
	
	public Human(String name, int playerNumber) {
		this.name = name;
		this.playerNumber = playerNumber;
		
		gameStart();
	}
	
	@Override
	public void gameStart() {
		// TODO Auto-generated method stub
		if(playerNumber == 1) {
			this.isTurn = true;
		}else {
			this.isTurn = false;
		}
	}


	@Override
	public boolean hit(int x, int y) {
		// TODO Auto-generated method stub
		
		return false;
	}

	@Override
	public boolean isTurn() {
		// TODO Auto-generated method stub
		return this.isTurn;
	}
	
	public void setTurn(boolean torf) {
		this.isTurn = torf;
	}

	@Override
	public String getPlayerType() {
		// TODO Auto-generated method stub
		return this.playerType;
	}
	
	public int getPlayerNumber() {
		return this.playerNumber;
	}

	@Override
	public String setPlayerType(String playerType) {
		// TODO Auto-generated method stub
		return this.playerType = playerType;
	}

	@Override
	public int getLives() {
		// TODO Auto-generated method stub
		return this.numLives;
	}
	
	public void reduceLives() {
		this.numLives -=1;
	}

	@Override
	public void startTurn() {
		// TODO Auto-generated method stub
		
	}

}
