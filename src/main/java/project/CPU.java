package project;

import java.util.Random;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class CPU implements Player {

	private String name = "CPU";
	public String playerType = "CPU";
	private int playerNumber; // 1 or 2
	public int numLives = 17; // same for both human and CPU

	public boolean isTurn;
	private String difficulty; // easy or normal
	private int recSelection[] = {0, 0};
	public Rectangle[][] playableBoard;
	private Human opponent;
	
	public CPU(String name, int playerNumber, Human opponent) {
		this.name = name;
		this.playerNumber = playerNumber;
		this.opponent = opponent;
		
		gameStart();
	}
	
	
	public void setDifficulty(String difficulty) {
		this.difficulty = difficulty;
	}
	
	public String getDifficulty() {
		return this.difficulty;
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
	
	public void randomMove() {
		//generates 2 random ints from 0-7 for selection spaces on the board
		Random rand = new Random();
		int randX = rand.nextInt(8); 
		int randY = rand.nextInt(8);
		this.recSelection[0] = randX;
		this.recSelection[1] = randY;
	}
	
	
	public String makeMove(int x, int y) {
		// TODO Auto-generated method stub
		String hitOrMiss = "";
		//Rectangle[][] pBoard = board.getBoard();
		
		if(playableBoard[x][y].getId() == "Empty") {
			playableBoard[x][y].setFill(Color.WHITE);
			System.out.println( this.name +" Splashed!");
			this.isTurn = false;
			hitOrMiss = "miss";
			playableBoard[x][y].setId("touched");
		}else if(playableBoard[x][y].getId() == "touched"){
			System.out.println(this.name + " Already Targeted");
			startTurn();
		}
		else if(playableBoard[x][y].getId() == "Ship") {
			playableBoard[x][y].setFill(Color.RED);
			System.out.println( this.name +" Hit!");
			this.isTurn = false;
			this.opponent.reduceLives();
			hitOrMiss = "hit";
			playableBoard[x][y].setId("touched");
		}
		return hitOrMiss;
	}

	public void startTurn() {
		//This function will start the turn of the CPU player. This should be called within
		// the game state class as the system cycles through each players' turn.
		
		randomMove();
		makeMove(recSelection[0], recSelection[1]);
		
	}
	

	@Override
	public boolean hit(int x, int y) {
		// TODO Auto-generated method stub
		return false;
	}

	public void updateScore() {
		System.out.println("This user has no score");
	}
	
	public int getScore() {
		return 0;
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
		return "Computer";
	}

	@Override
	public String setPlayerType(String playerType) {
		// TODO Auto-generated method stub
		return playerType;
	}

	public int getPlayerNumber() {
		return this.playerNumber;
	}
	public void main (String[] args) {
		
	}

	public void setBoard(Rectangle[][] board) {
		this.playableBoard = board;
	}
	@Override
	public int getLives() {
		// TODO Auto-generated method stub
		return this.numLives;
	}


	@Override
	public void reduceLives() {
		// TODO Auto-generated method stub
		this.numLives -= 1;
	}

}
