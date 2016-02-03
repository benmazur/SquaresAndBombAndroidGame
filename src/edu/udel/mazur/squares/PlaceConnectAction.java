package edu.udel.mazur.squares;

import edu.udel.mazur.gameframework.Action;








public class PlaceConnectAction implements Action<SquaresGame> {
    private char player;
    private int row;
    private int column;
    
    public PlaceConnectAction(char player, int row, int column) {
        this.player = player;
        this.row = row;
        this.column = column;
    }
    
    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }
    
   
    public boolean isValid(SquaresGame s) {
        return     		
                s.getTurn() == player &&
                s.getBoard()[row][column].isEmpty()
               ;
                
    }
    public boolean isValidMissile( SquaresGame s){
    	return s.getTurn() == player &&
    			s.getBoard()[row][column].isBox();
    }
    
    public void update(SquaresGame s) {
    	Connect X = new Connect('x');
    	Connect O = new Connect('o');
    	Connect L = new Connect('l');
    	
    	if(this.isValid(s)){
    				s.setConnect(row, column, L);
            		if (!s.makeBox(row, column, this.player)) {
            			s.changeTurn();
            		}
            		
                			
        	}
        }
            	    	  		
            		
    	
    	
    
    
    public String toString() {
        return "PlaceConnect:"+player+" at "+row+","+column;
    }
}
