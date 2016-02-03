package edu.udel.mazur.squares;


import edu.udel.mazur.gameframework.ConsoleListener;
import edu.udel.mazur.gameframework.Game;

// Yifeng Liu  Ben Mazur

// This is not the final version of the game because we will definitely improve the game in the last week.
public class SquaresGame extends Game {
	private Connect[][] board;
	private char turn;
	private char notTurn;
	private int score;
	
	    
	    
	public SquaresGame(Connect[][] board, char turn, char notTurn,int score) {
		this.board = board;
	    this.turn = turn;
	    this.notTurn = notTurn;
	    this.score = score;
	}

	public int getScore() {
		return score;
	}

	

	public Connect[][] getBoard() {
	    return board;
	}

	public char getTurn() {
	    return turn;
	}

	public char getNotTurn() {
	    return notTurn;
	}
	    
	    
	public void changeTurn() {
		if( this.getNotTurn() == 'x' && this.getTurn() == 'o'){
			this.notTurn = 'o';
		    this.turn = 'x';
		}
		else{
		    this.notTurn = 'x';
		    this.turn = 'o';
		}
		 

	}
	public void setConnect(int row, int column, Connect Connect) {
	    board[row][column] = Connect;
	}
	    
	public String getStatus() {
		if (isEnd()) {
			if (isWinner(getTurn())) {
				return "Player " + getTurn() + " wins!\n";
	        }
	        else if (isWinner(getNotTurn())) {
	        	return "Player " + getNotTurn() + " wins!\n";
	        }
	        else {
	        	return "It is a draw.\n";
	        }
	    }
	    else {
	    	return "Player " + turn + "'s turn\n";
	    }
	}
	public String toString() {
        StringBuilder buffer = new StringBuilder();
        buffer.append(getStatus());
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                buffer.append(board[i][j]);
            }
            buffer.append("\n");
        }
        return buffer.toString();
    }
	public boolean isWithinBounds(int row, int column) {
        return row >= 0 && column >= 0 && 
                row < getBoard().length && 
                column < getBoard()[row].length;
    }
	public boolean isBox(int a, int b){
		board = this.getBoard();
		boolean result = false;
		if(board[a][b].equals('x')||board[a][b].equals('o')){
					result = true;
			}
		return result;	
		}
//     public boolean isMissile(int a, int b){
//    	 board = this.getBoard();
//    	 boolean result = false;
//    	 if(board[a][b].equals('x')||board[a][b].equals('o')){
//				result = true;
//    	 }
//    	 return result;
//     }
	
	public boolean makeBox(int row, int column, char symbol) {
		boolean result = false;
        for( int a = 1; a < board.length; a+=2){
        	for( int b = 1; b < board[a].length;b+=2){
        		if ((board[a+1][b].getSymbol() == 'l') && 
        			(board[a-1][b].getSymbol() == 'l') && 
        			(board[a][b+1].getSymbol() == 'l') && 
        			(board[a][b-1].getSymbol() == 'l') && 
        			(board[a][b].getSymbol() == 'B')){
        			setConnect(a, b, new Connect(this.turn));
        			result = true;
        		}
        	}
        		
        	}
        
        return result;

    }
	
		
	
	public int getScore(char symbol) {
    	int Score = 0;
    	for(int a = 1; a < board.length && a % 2 == 1; a+=2){
    		for( int b = 1; b < board[a].length && b % 2== 1; b+=2){
    			if(board[a][b].getSymbol() == symbol ){
    				Score ++;
    			}
    		}
    	}
        return Score;
    }
	 public boolean isWinner(char symbol) {
		
	   return getScore(symbol) > 8;
			
		}
	        
    
	 public boolean hasEmptySpace() {
	        for(int i = 0; i < board.length ; i++){
	        	for(int a = 0; a < board.length; a++){
	        		if(board[i][a].isEmpty()){
	        			return true;
	        		}
	        	}	
	        }
	        return false;
	       
	    }
	 public boolean isEnd() {
	        return !this.hasEmptySpace();
	    }
	 public static SquaresGame makeStartGame(char turn, char notTurn) {
	        // creates a starting board
	        Connect E = new Connect(Connect.EMPTY);
	        Connect D = new Connect(Connect.DOT);
	        Connect B = new Connect(Connect.Box);
	        
	        Connect[][] board = new Connect[][]
	                {{D, E, D, E, D, E, D, E, D},
	                 {E, B, E, B, E, B, E, B, E},
	                 {D, E, D, E, D, E, D, E, D},
	                 {E, B, E, B, E, B, E, B, E},
	                 {D, E, D, E, D, E, D, E, D},
	                 {E, B, E, B, E, B, E, B, E},
	                 {D, E, D, E, D, E, D, E, D},
	                 {E, B, E, B, E, B, E, B, E},
	                 {D, E, D, E, D, E, D, E, D},
	                 };
	        return new SquaresGame(board, turn, notTurn,0);
	    }
	 public static void main(String[] args) {
	        SquaresGame game = makeStartGame('x', 'o');
	        game.addGameListener(new ConsoleListener());
	        game.addGameListener(new SquaresAI(game, "x", 1000));
	        game.addGameListener(new SquaresAI(game, "o", 1000));  
	 
	 game.start();
	 }
	 
}

