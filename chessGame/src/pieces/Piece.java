package pieces;
import java.util.ArrayList;

import chess.Player;
import chess.Position;

public abstract class Piece {
	
	private Player.Side Side;
	
	
	
	public Piece( Player.Side Side) {
		
		this.Side = Side;
		
	}
	
	/**
	 * returns side of player (WHITE or BLACK).
	 * @return
	 */
	public Player.Side getSide() {
		return Side;
	}
	
	public abstract Piece copy();
	
	public abstract String getName();
	
	public abstract String getTypeString();
	
	public abstract boolean getHasMoved();
	
	public abstract void  setHasMoved(boolean hasMoved);
	
	public abstract String getSymbol();
	
	/**
	 * Checks if the desired move is legal.
	 * @param curPosition
	 * @param newPosition
	 * @param positions
	 * @param simulation
	 * @param checkFirstMove
	 * @return
	 */
	public abstract boolean checkLegalMove(Position curPosition,Position newPosition,ArrayList<ArrayList<Position>> positions, boolean simulation, boolean checkFirstMove);
	
	/**
	 * Checks if the desired move is legal.
	 * 
	 * Checks if the desired move is one of the possible moves.
	 * @param curPosition
	 * @param newPosition
	 * @param positions
	 * @param possibleMoves
	 * @return
	 */
	public abstract boolean checkLegalMove(Position curPosition,Position newPosition,ArrayList<ArrayList<Position>> positions,ArrayList<ArrayList<Integer>> possibleMoves);
	
	public boolean checkSide(Player.Side side,int nx,int ny,ArrayList<ArrayList<Position>> positions ) {
		if(positions.get(nx).get(ny).getPiece()!=null&&positions.get(nx).get(ny).getPiece().getSide() == side) {
			return false;
		}
		return true;
	}
	
	/**
	 * checks if the piece can move horizontally or vertically.
	 * @param cx	current x value of the piece.
	 * @param cy	current y value of the piece.	
	 * @param nx	x value where the piece needs to move.
	 * @param ny	y value where the piece needs to move.
	 * @param positions positions of the board.
	 * @return
	 */
	public boolean checkStraight(int cx,int cy,int nx,int ny,ArrayList<ArrayList<Position>> positions ) {
		int directionX = (nx-cx<0)?-1:1;
		int directionY = (ny-cy<0)?-1:1;
		if(positions.get(nx).get(ny).getPiece()!=null && positions.get(cx).get(cy).getPiece()!=null &&positions.get(nx).get(ny).getPiece().getSide() == positions.get(cx).get(cy).getPiece().getSide()) {
			return false;
		}
		if(((cx == nx) && (Math.abs(ny-cy) < 8))) {
			boolean canMove = true;
			if(directionY>0) {
				for(int j=cy;j<cy+(directionY*(Math.abs(ny-cy)+1));j=j+directionY) {
	    			   
	   				if((positions.get(cx).get(j).getPiece() != null)&& positions.get(cx).get(j).getPiece()!= positions.get(nx).get(ny).getPiece()&&positions.get(cx).get(j).getPiece()!=this) {
	   					canMove = false;
	   					
	   					break;
	   				}
	   				
	   			   }
	    		   return canMove;
	    	   }else {
	    		  
	    		   for(int j=cy;j>cy+(directionY*(Math.abs(ny-cy)+1));j=j+directionY) {
	    			   
	   				if((positions.get(cx).get(j).getPiece() != null)&& positions.get(cx).get(j).getPiece()!= positions.get(nx).get(ny).getPiece()&&positions.get(cx).get(j).getPiece()!=this) {
	   					canMove = false;
	   					
	   					break;
	   				}
	   			   }
	    		   return canMove;
	    	   }
			
		}else if(((cy == ny) && (Math.abs(nx-cx) < 8))) {
			//vertical 1 square
			
			boolean canMove = true;
			if(directionX>0) {
				for(int i=cx;i<cx+(directionX*(Math.abs(nx-cx)+1));i=i+directionX) {
	    			   
	   				if((positions.get(i).get(cy).getPiece() != null)&& positions.get(i).get(cy).getPiece()!= positions.get(nx).get(ny).getPiece()&&positions.get(i).get(cy).getPiece()!=this) {
	   					canMove = false;
	   					
	   					break;
	   				}
	   			   }
	    		   return canMove;
	    	   }
			else {
	    		  
	    		   for(int i=cx;i>cx+(directionX*(Math.abs(nx-cx)+1));i=i+directionX) {
	    			   
	   				if((positions.get(i).get(cy).getPiece() != null)&& positions.get(i).get(cy).getPiece()!= positions.get(nx).get(ny).getPiece()&&positions.get(i).get(cy).getPiece()!=this) {
	   					canMove = false;
	   					
	   					break;
	   				}
	   			   }
	    		   return canMove;
	    	   }
			
		}
		return false;
	}
	
	/**
	 * checks if the piece can move diagnally.
	 * @param cx	current x value of the piece.
	 * @param cy	current y value of the piece.	
	 * @param nx	x value where the piece needs to move.
	 * @param ny	y value where the piece needs to move.
	 * @param positions positions of the board.
	 * @param isKing if the piece is the king.
	 * @return
	 */
	public boolean checkDia(int cx,int cy,int nx,int ny,ArrayList<ArrayList<Position>> positions,boolean isKing ) {
		
		int directionX = (nx-cx<0)?-1:1;
		int directionY = (ny-cy<0)?-1:1;
		
		//if the piece is the king it can only move 1 square diagnally
		int pathX = isKing?2:(directionX*(Math.abs(nx-cx)+1));
		int pathY = isKing?2:(directionY*(Math.abs(ny-cy)+1));
		

		
		boolean canMove = true;
		if(Math.abs(nx-cx)!=Math.abs(ny-cy)) {
			return false;
		}
		
		//checks if the piece can move in the desired direction
		if(directionX>0&&directionY>0) {
			//naar rechtsonder
			
			
			for(int i=cx;i<cx+pathX;i=i+directionX) {
				for(int j=cy;j<cy+pathY;j=j+directionY) {
				if(i!=cx && j !=cy&&(Math.abs(cx-i)==Math.abs(cy-j))) {
					
					
					if((positions.get(i).get(j).getPiece() != null)&& positions.get(i).get(j).getPiece()!= positions.get(nx).get(ny).getPiece()&&positions.get(i).get(j).getPiece()!=this) {
						canMove = false;
						
						break;
					}
				}
				}
			   }
    		   return canMove;
		}else if(directionX>0&&directionY<0) {
			//naar linksonder
			
			
			for(int i=cx;i<cx+pathX;i=i+directionX) {
				for(int j=cy;j>cy+pathY;j=j+directionY) {
				if(i!=cx && j !=cy&&(Math.abs(cx-i)==Math.abs(cy-j))) {
					
					
					if((positions.get(i).get(j).getPiece() != null)&& positions.get(i).get(j).getPiece()!= positions.get(nx).get(ny).getPiece()&&positions.get(i).get(j).getPiece()!=this) {
						canMove = false;
						
						break;
					}
				}
				}
			   }
		   return canMove;
			
		}else if(directionX<0&&directionY<0) {
			//naar linksboven
			
			
			for(int i=cx;i>cx+pathX;i=i+directionX) {
				for(int j=cy;j>cy+pathY;j=j+directionY) {
				if(i!=cx && j !=cy&&(Math.abs(cx-i)==Math.abs(cy-j))) {
					
					
					if((positions.get(i).get(j).getPiece() != null)&& positions.get(i).get(j).getPiece()!= positions.get(nx).get(ny).getPiece()&&positions.get(i).get(j).getPiece()!=this) {
						canMove = false;
						
						break;
					}
				}
				}
			   }
			 return canMove;
			
		}else if(directionX<0&&directionY>0) {
			//naar rechtsboven
			
			
			for(int i=cx;i>cx+pathX;i=i+directionX) {
				for(int j=cy;j<cy+pathY;j=j+directionY) {
				if(i!=cx && j !=cy&&(Math.abs(cx-i)==Math.abs(cy-j))) {
					
					
					if((positions.get(i).get(j).getPiece() != null)&& positions.get(i).get(j).getPiece()!= positions.get(nx).get(ny).getPiece()&&positions.get(i).get(j).getPiece()!=this) {
						canMove = false;
						
						break;
					}
				}
				}
			   }
			 return canMove;
			
		}
		
		
		return false;
	}
	
	
	
	

}
