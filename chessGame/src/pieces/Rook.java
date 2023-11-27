package pieces;

import java.util.ArrayList;

import chess.Player;
import chess.Position;

public class Rook extends Piece {
	

	

	public Rook(Player.Side Side) {
		
		super( Side,'\u2501');
		// TODO Auto-generated constructor stub
		
	}
	public String getSymbol() {
		if(this.getSide() == Player.Side.WHITE) {
			return "C:\\\\Users\\\\hidde\\\\Downloads\\\\rookwhite.png\\";
			
		}else {
			return "C:\\\\Users\\\\hidde\\\\Downloads\\\\rookblack.png\\";
			
		}
		
		
	}
	public char getSymbolChar() {
		if(this.getSide() == Player.Side.WHITE) {
			return '\u2656';
			
		}else {
			return '\u265C';
			
		}
		
	}
	public String getName() {
		if(this.getSide() == Player.Side.WHITE) {
			return "white rook";
			
		}else {
			return "black rook";
			
		}
	}
	
	public String getTypeString() {
		return "rook";
	}
	
	public  Piece copy() {
		return new Rook(this.getSide());
	}
	
	@Override
	public boolean checkLegalMove(Position curPosition,Position newPosition,ArrayList<ArrayList<Position>> positions,boolean simulation, boolean checkFirstMove) {
		int cx = curPosition.getX();
		int cy = curPosition.getY();
		
		int nx = newPosition.getX();
		int ny = newPosition.getY();
		
		
		if(checkSide(this.getSide(), nx, ny, positions)) {
		return this.checkStraight(cx, cy, nx, ny, positions);
		}
		return false;
		
	}
	public boolean checkLegalMove(Position curPosition,Position newPosition,ArrayList<ArrayList<Position>> positions,ArrayList<ArrayList<Integer>> possibleMoves) {
		return false;
	}
	
	
	
	

}
