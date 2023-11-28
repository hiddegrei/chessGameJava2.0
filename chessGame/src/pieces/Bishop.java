package pieces;

import java.util.ArrayList;

import chess.Player;
import chess.Position;

public class Bishop extends Piece {

	public Bishop(Player.Side Side) {
		super( Side);
		// TODO Auto-generated constructor stub
	}
	public String getSymbol() {
		if(this.getSide() == Player.Side.WHITE) {
			return "C:\\\\Users\\\\hidde\\\\Downloads\\\\whitebishop.png\\";
			
		}else {
			return "C:\\\\Users\\\\hidde\\\\Downloads\\\\blackbishop.png\\";
			
		}
		
		
	}
	
	public boolean getHasMoved() {
		return false;
	}
	
	@Override
	public void setHasMoved(boolean hasMoved) {
		// TODO Auto-generated method stub
		
	}
	
	public String getName() {
		if(this.getSide() == Player.Side.WHITE) {
			return "white bishop";
			
		}else {
			return "black bishop";
			
		}
	}
	
	public String getTypeString() {
		return "bishop";
	}
	
	@Override
	public  Piece copy() {
		return new Bishop(this.getSide());
	}
	
	public boolean checkLegalMove(Position curPosition,Position newPosition,ArrayList<ArrayList<Position>> positions,ArrayList<ArrayList<Integer>> possibleMoves) {
		return false;
	}
	
	@Override
	public boolean checkLegalMove(Position curPosition,Position newPosition,ArrayList<ArrayList<Position>> positions,boolean simulation, boolean checkFirstMove) {
		int cx = curPosition.getX();
		int cy = curPosition.getY();
		
		int nx = newPosition.getX();
		int ny = newPosition.getY();
		
		
		boolean moveH = Math.abs(ny-cy)!=0;
		boolean moveV = Math.abs(nx-cx)!=0;
		
		if(checkSide(this.getSide(), nx, ny, positions)) {
		if(moveH&&moveV) {
			
			return this.checkDia(cx, cy, nx, ny, positions,false);
		}
		}
		return false;
		
		
	}
	

}
