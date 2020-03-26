package project;

import java.util.Random;
import javafx.scene.shape.Rectangle;

public class CPU implements Player {

	private String name = "CPU";
	public String playerType = "Computer";
	private int playerNumber; // 1 or 2
	public int numShips = 6; // same for both human and CPU

	public boolean isTurn;
	private String difficulty; // easy or normal
	private int recSelection[] = {0, 0};

	
	
	
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

	public void startTurn(Board board) {
		//This function will start the turn of the CPU player. This should be called within
		// the game state class as the system cycles through each players' turn.
		
		randomMove();
		
		Random randDirection = new Random();
		int moveDirection = randDirection.nextInt(4);
		
		makeMove(recSelection[0], recSelection[1], board);
		while( this.isTurn == true) {
			int y = recSelection[1];
			int x = recSelection[0];
			
			if(difficulty == "easy") {
				randomMove();
				makeMove(recSelection[0], recSelection[1], board);
			}else {
				// Algorithm for selecting the next move
				if(moveDirection == 0) {
					//next move up
					if(y < 7) {
						y+=1;
					}else {
						makeMove(recSelection[0], y, board);
					}
				}else if(moveDirection == 1){
					//next move right
					if(x < 7) {
						x+=1;
					}else {
						makeMove(x, recSelection[1], board);
					}
					
				}else if(moveDirection == 2){
					//next move down
					if(y > 0) {
						y-=1;
					}else {
						makeMove(recSelection[0], y, board);
					}
					
				}else{
					//next move left
					if(x > 0) {
						x-=1;
					}else {
						makeMove(x, recSelection[1], board);
					}
					
				}
			}
		}
	}
	
	@Override
	public String makeMove(int x, int y, Board board) {
		// TODO Auto-generated method stub
		String hitOrMiss = "";
		Rectangle[][] pBoard = board.getBoard();
		
		if(pBoard[x][y].getId() == "Empty") {
			System.out.println( this.name +" Splashed!");
			this.isTurn = false;
			hitOrMiss = "miss";
		}else {
			System.out.println(this.name + " Hit The Target!");
			hitOrMiss = "hit";
		}
		return hitOrMiss;
		
		
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
		return "Computer";
	}

	@Override
	public String setPlayerType(String playerType) {
		// TODO Auto-generated method stub
		return playerType;
	}

	
	public void main (String[] args) {
		
	}
}
