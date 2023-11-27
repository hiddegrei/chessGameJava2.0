package pieces;

import java.util.ArrayList;

import chess.Player;
import chess.Position;

public class Queen extends Piece {

	public Queen( Player.Side Side) {
		super( Side,'\u2501');
		// TODO Auto-generated constructor stub
	}
	public String getSymbol() {
		if(this.getSide() == Player.Side.WHITE) {
			return "C:\\\\Users\\\\hidde\\\\Downloads\\\\queenwhite.png\\";
			
		}else {
			return "C:\\\\Users\\\\hidde\\\\Downloads\\\\queenblack.png\\";
			
		}
		
		
	}
	@Override
	public char getSymbolChar() {
		if(this.getSide() == Player.Side.WHITE) {
			return '\u2655';
			
		}else {
			return '\u265B';
			
		}
		
	}
	@Override
	public String getName() {
		if(this.getSide() == Player.Side.WHITE) {
			return "white queen";
			
		}else {
			return "black queen";
			
		}
	}
	
	public String getTypeString() {
		return "queen";
	}
	@Override
	public  Piece copy() {
		return new Queen(this.getSide());
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
			}else if((moveV&&!moveH)||(moveH&&!moveV)) {
				return this.checkStraight(cx, cy, nx, ny, positions);
			}
		}
		
		
		return false;
		
		
		
		
		
	}
	public boolean checkLegalMove(Position curPosition,Position newPosition,ArrayList<ArrayList<Position>> positions,ArrayList<ArrayList<Integer>> possibleMoves) {
		return false;
	}

}
