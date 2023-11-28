package pieces;

import java.util.ArrayList;

import chess.Player;
import chess.Position;

public class Rook extends Piece {
	private boolean hasMoved = false;

	

	public Rook(Player.Side Side) {
		
		super( Side);
		// TODO Auto-generated constructor stub
		
	}
	public String getSymbol() {
		if(this.getSide() == Player.Side.WHITE) {
			return "C:\\\\Users\\\\hidde\\\\Downloads\\\\rookwhite.png\\";
			
		}else {
			return "C:\\\\Users\\\\hidde\\\\Downloads\\\\rookblack.png\\";
			
		}
		
		
	}
	
	public boolean getHasMoved() {
		return hasMoved;
	}
	
	public void setHasMoved(boolean hasMoved) {
		 this.hasMoved = hasMoved;
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
			boolean check = this.checkStraight(cx, cy, nx, ny, positions);
			
		return check;
		}
		return false;
		
	}
	public boolean checkLegalMove(Position curPosition,Position newPosition,ArrayList<ArrayList<Position>> positions,ArrayList<ArrayList<Integer>> possibleMoves) {
		return false;
	}
	
	
	
	

}
