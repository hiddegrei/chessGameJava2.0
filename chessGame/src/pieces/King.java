package pieces;

import java.util.ArrayList;

import chess.Player;
import chess.Position;

public class King extends Piece {
	

	public King( Player.Side Side ) {
		
		super( Side,'\u2654');
		
		// TODO Auto-generated constructor stub
	}
	public String getSymbol() {
		if(this.getSide() == Player.Side.WHITE) {
			return "C:\\\\Users\\\\hidde\\\\Downloads\\\\kingwhite.png\\";
			
		}else {
			return "C:\\\\Users\\\\hidde\\\\Downloads\\\\kingblack.png\\";
			
		}
		
		
	}
	
	public char getSymbolChar() {
		if(this.getSide() == Player.Side.WHITE) {
			return '\u2654';
			
		}else {
			return '\u265A';
			
		}
		
		
	}
	
	public String getName() {
		if(this.getSide() == Player.Side.WHITE) {
			return "white king";
			
		}else {
			return "black king";
			
		}
	}
	
	public String getTypeString() {
		return "king";
	}
	
	public boolean checkLegalMove(Position curPosition,Position newPosition,ArrayList<ArrayList<Position>> positions,ArrayList<ArrayList<Integer>> possibleMoves) {
		
			boolean moveAllowed = false;
			for(ArrayList<Integer> pos:possibleMoves) {
				//System.out.println(pos);
				if(newPosition.getX()==pos.get(0)&&newPosition.getY()==pos.get(1)) {
					//isallowed
					moveAllowed = true;
				}
			}
			return moveAllowed;
		
	}
	
	@Override
	public  Piece copy() {
		return new King(this.getSide());
	}
	
	
	@Override
	public boolean checkLegalMove(Position curPosition,Position newPosition,ArrayList<ArrayList<Position>> positions,boolean simulation, boolean checkFirstMove) {
		int cx = curPosition.getX();
		int cy = curPosition.getY();
		
		int nx = newPosition.getX();
		int ny = newPosition.getY();
		
		boolean moveH = Math.abs(ny-cy)==1;
		boolean moveV = Math.abs(nx-cx)==1;
		
		
		
		if(checkSide(this.getSide(), nx, ny, positions)) {
			if(moveH&&moveV) {
				
				return this.checkDia(cx, cy, nx, ny, positions,true);
			}
			else if(((cx == nx) && (Math.abs(ny-cy) == 1))) {
			//sideways 1 square
				return true;
			
			}else if(((cy == ny) && (Math.abs(nx-cx) == 1))) {
			//vertical 1 square
				return true;
			
			}
		}
		
		
		return false;
		
		
	}

	

	

}
