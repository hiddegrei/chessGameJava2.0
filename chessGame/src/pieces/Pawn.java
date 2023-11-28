package pieces;

import java.util.ArrayList;

import chess.Player;
import chess.Position;

public class Pawn extends Piece {
	private boolean firstMove = true;

	public Pawn(Player.Side Side) {
		super( Side);
		// TODO Auto-generated constructor stub
	}
	@Override
	public String getSymbol() {
		if(this.getSide() == Player.Side.WHITE) {
			return "C:\\\\Users\\\\hidde\\\\Downloads\\\\pawnwhite.png\\";
			
		}else {
			return "C:\\\\Users\\\\hidde\\\\Downloads\\\\pawnblack.png\\";
			
		}
		
		
	}
	@Override
	public boolean getHasMoved() {
		return false;
	}
	
	@Override
	public void setHasMoved(boolean hasMoved) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public String getName() {
		if(this.getSide() == Player.Side.WHITE) {
			return "white pawn";
			
		}else {
			return "black pawn";
			
		}
	}
	@Override
	public String getTypeString() {
		return "pawn";
	}
	
	@Override
	public  Piece copy() {
		return new Pawn(this.getSide());
	}
	
	public boolean canTake(Position curPosition,Position newPosition, ArrayList<ArrayList<Position>> positions) {
		int cx = curPosition.getX();
		int cy = curPosition.getY();
		
		int nx = newPosition.getX();
		int ny = newPosition.getY();
		
		int direction = (this.getSide()==Player.Side.WHITE?-1:1);
		
		
		if(checkSide(this.getSide(), nx, ny, positions)) {
		
       //this check if the pawn can take a piece, can move sideways
       
       
       if((Math.abs(cy-ny)==1 && (nx-cx == direction))) {
    	   if(positions.get(nx).get(ny).getPiece() != null) {
    		  
    		        firstMove = false;
					return true;
				}
       }
		}
       
       
		return false;
	}
	
	@Override
	public boolean checkLegalMove(Position curPosition,Position newPosition, ArrayList<ArrayList<Position>> positions,boolean simulation, boolean checkFirstMove) {
		int cx = curPosition.getX();
		int cy = curPosition.getY();
		
		int nx = newPosition.getX();
		int ny = newPosition.getY();
		
		int direction = (this.getSide()==Player.Side.WHITE?-1:1);
		
		int jumpStart = firstMove?2:1;
		if(!checkFirstMove) {
				jumpStart=1;
			}
		
		
		if(checkSide(this.getSide(), nx, ny, positions)) {
		// this check if pawn just wants to move up(down)
			
				
			if(!simulation||(simulation&&!checkFirstMove)&&newPosition.getPiece()==null) {
       if(((cy == ny) && ((nx-cx == (firstMove?2:1)*direction)||(nx-cx == direction)))) {
			//vertical 1 square is possible moves
			//now check if it legal move
    	   
    	   //check if piece is in front
    	   if(direction>0) {
    		   for(int i=cx;i<cx+jumpStart+1;i=i+direction) {
   				if(positions.get(i).get(cy).getPiece() == null) {
   					if(checkFirstMove) {
   						firstMove = false;
   					}
   					
   					return true;
   				}
   			}
    	   }else {
    		   
    		   for(int i=cx;i>cx-jumpStart-1;i=i+direction) {
    			   
   				if(positions.get(i).get(cy).getPiece() == null) {
   					if(checkFirstMove) {
   						firstMove = false;
   					}
   					return true;
   				}
   			}
    	   }
			
		}
			}
			
       //this check if the pawn can take a piece, can move sideways
       if((Math.abs(cy-ny)==1 && (nx-cx == direction))) {
    	   if(positions.get(nx).get(ny).getPiece() != null) {
    		  
    		   if(checkFirstMove) {
						firstMove = false;
					}
					return true;
				}
       }
		}
       
       
		return false;
		
		
	}
	public boolean checkLegalMove(Position curPosition,Position newPosition,ArrayList<ArrayList<Position>> positions,ArrayList<ArrayList<Integer>> possibleMoves) {
		return false;
	}

}
