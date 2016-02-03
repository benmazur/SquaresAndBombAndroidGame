package edu.udel.mazur.squares;

public class Connect {
	public static final char EMPTY = ' ';
	public static final char DOT = '.';
	public static final char Box = 'B';
	
	private char symbol;
	
	 public Connect(char symbol) {
	        this.symbol = symbol;
	       
	    }
	    
	    public char getSymbol() {
	        return symbol;
	    }

	    public boolean equals(Object obj) {
	        return obj instanceof Connect && ((Connect)obj).symbol == symbol;
	    }

	    public String toString() {
	        return Character.toString(symbol);
	    }
	    
	    public boolean isEmpty() {
	        return symbol == EMPTY;
	    }
	    public boolean isBox(){
	    	return symbol == Box;
	    }
}

		
	    
		
		

