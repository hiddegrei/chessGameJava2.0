package pieces;

import java.util.ArrayList;

import chess.Player;
import chess.Position;

public class Knight extends Piece {

	public Knight(Player.Side Side) {
		super( Side, '\u2501');
		
	}
	
	public String getSymbol() {
		if(this.getSide() == Player.Side.WHITE) {
			return "C:\\\\Users\\\\hidde\\\\Downloads\\\\knightwhite.png\\";
			
		}else {
			return "C:\\\\Users\\\\hidde\\\\Downloads\\\\knightblack.png\\";
			
		}
		
		
	}
	
	public char getSymbolChar() {
		if(this.getSide() == Player.Side.WHITE) {
			return '\u2658';
			
		}else {
			return '\u265E';
			
		}
		
	}
	
	public String getName() {
		if(this.getSide() == Player.Side.WHITE) {
			return "white knight";
			
		}else {
			return "black knight";
			
		}
	}
	
	public String getTypeString() {
		return "knight";
	}
	
	@Override
	public  Piece copy() {
		return new Knight(this.getSide());
	}

	@Override
	public boolean checkLegalMove(Position curPosition,Position newPosition,ArrayList<ArrayList<Position>> positions,boolean simulation, boolean checkFirstMove) {
		int cx = curPosition.getX();
		int cy = curPosition.getY();
		
		int nx = newPosition.getX();
		int ny = newPosition.getY();
		
		
		//max 8 opties
		
		if(checkSide(this.getSide(), nx, ny, positions)) {
		if((Math.abs(nx-cx)==2&&Math.abs(ny-cy)==1)||(Math.abs(nx-cx)==1&&Math.abs(ny-cy)==2)) {
			return true;
		}
		}

		
		return false;
		
		
	}
	public boolean checkLegalMove(Position curPosition,Position newPosition,ArrayList<ArrayList<Position>> positions,ArrayList<ArrayList<Integer>> possibleMoves) {
		return false;
	}

}
