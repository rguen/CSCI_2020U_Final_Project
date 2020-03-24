import javafx.scene.shape.Rectangle;

public class Human implements Player {
	
	private String name = "";
	public String playerType = "Human";
	private int playerNumber; // 1 or 2
	public int numShips = 6; // same for both human and CPU

	public boolean isTurn;
	
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
	public void makeMove(int x, int y, Board board) {
		
		// TODO Auto-generated method stub
		/* 
		*  This method should be called when a player clicks on a square on the board.
		*  It will then check the state of the board at that location for determining if there is a ship present.
		*  If there is no ship, and the space is "Empty" set the value for isTurn to false.
		*  If the space is occupied by a "Ship" then the isTurn value does not change and the player can make another move
		*/
		Rectangle[][] pBoard = board.getBoard();
		
		if(pBoard[x][y].getId() == "Empty") {
			System.out.println( this.name +" Splashed!");
			this.isTurn = false;
		}else {
			System.out.println(this.name + " Hit The Target!");
			/*
			 * I think that perhaps this should return some value if a ship is hit for the game state class to update
			 * the ship that was hit.
			 */
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

	@Override
	public String getPlayerType() {
		// TODO Auto-generated method stub
		return this.playerType;
	}

	@Override
	public String setPlayerType(String playerType) {
		// TODO Auto-generated method stub
		return this.playerType = playerType;
	}

}
