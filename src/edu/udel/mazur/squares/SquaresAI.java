package edu.udel.mazur.squares;

import java.util.ArrayList;
import java.util.List;

import edu.udel.mazur.gameframework.AI;
import edu.udel.mazur.gameframework.Action;



public class SquaresAI extends AI<SquaresGame> {
	public SquaresAI(SquaresGame game, String symbol) {
        super(game, symbol);
    }
    public SquaresAI(SquaresGame game, String symbol, long turnTimeLength) {
        super(game, symbol, turnTimeLength);
    }
    
    // convenience method
    private char getOurSymbol() {
        return getIdentifier().charAt(0);
    }

    protected boolean isMyTurn() {
        return getGame().getTurn() == getOurSymbol();
    }

   
    public List<Action<SquaresGame>> getAllValidActions(SquaresGame game) {
        List<Action<SquaresGame>> validMoves = new ArrayList<Action<SquaresGame>>();
        for( int i = 0 ; i < 9 ; i++){
			for( int j = 0; j < 9; j++){
				if(game.getBoard()[i][j].isEmpty() ){
				validMoves.add(new PlaceConnectAction(getOurSymbol(),i, j));
				}
				
			}
        }
        return validMoves;
    }

    Connect[][] copy;
    public double getHeuristicScore(Action<SquaresGame> action, SquaresGame game) {
    	PlaceConnectAction ppa = (PlaceConnectAction)action;
    	char turn = game.getTurn();
    	if (copy == null) {
    		copy = new Connect[game.getBoard().length][];
    		for (int i = 0; i < copy.length; i++) {
        		copy[i] = game.getBoard()[i].clone();
        	}
    	}
    	for (int i = 0; i < copy.length; i++) {
    		for (int j = 0; j < copy[i].length; j++) {
    			copy[i][j] = game.getBoard()[i][j];
    		}
    	}
//    	Connect E = new Connect(Connect.EMPTY);
    	action.update(game);
    	int result = game.getScore(getOurSymbol());
//    	game.getBoard()[ppa.getRow()][ppa.getColumn()]= E;
    	for (int i = 0; i < copy.length; i++) {
    		for (int j = 0; j < copy[i].length; j++) {
    			game.getBoard()[i][j] = copy[i][j];
    		}
    	}
    	if (game.getTurn() != turn) {
    		game.changeTurn();
    	}
        return result;
    }
    
}
