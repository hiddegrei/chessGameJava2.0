/**
 * 
 */
package chess;

import java.util.ArrayList;

import pieces.Piece;

/**
 * 
 */
public class Player {
	
	public enum Side {WHITE,BLACK};
	private Side side = null;
	private Position activePosition;
	private ArrayList<ArrayList<Integer>> possibleMoves = new ArrayList<ArrayList<Integer>>(); //the possible moves of the king of the player.

	private ArrayList<Position> piecesToBeRemoved = new ArrayList<Position>(); //pieces to be removed or blocked to get out of a check.
	private Game game;
	private boolean kingIsChecked = false;
	private boolean iCanEscape = false;

	/**
	 * Class constructor
	 */
	public Player(Side side, Game game) {
		// TODO Auto-generated constructor stub
		this.side = side;
		this.game = game;
	}
	
	public Side getSide() {
		return side;
	}
	public boolean getKingIsChecked() {
		return kingIsChecked;
	}
	public boolean getICanEscape() {
		return iCanEscape;
	}
	public void setICanEscape(boolean iCanEscape) {
		this.iCanEscape =iCanEscape;
	}
	public void setKingIsChecked(boolean checked) {
		kingIsChecked= checked;
	}
	public void setPossibleMoves(ArrayList<ArrayList<Integer>> possibleMoves) {
		this.possibleMoves = possibleMoves;
	}
	
	public ArrayList<ArrayList<Integer>> getPossibleMoves() {
		return possibleMoves ;
	}
	
	public void addPieceToBeRemoved(Position position) {
		piecesToBeRemoved.add(position);
	}
	
	public Position getActivePosition() {
		return activePosition;
	}
	
	public void setActivePositionForce() {
		activePosition = null;
	}
	
	/**
	 * Performs castling for the player.
	 * @param positions
	 * @param position
	 */
	public void castle(ArrayList<ArrayList<Position>> positions,Position position) {
		if(getSide() == Player.Side.WHITE) {
			if(position.getX() == 7 && position.getY() == 6) {
				
				activePosition.getPiece().setHasMoved(true);
				positions.get(7).get(7).getPiece().setHasMoved(true);
				game.movePiece(7, 4, 7, 6, activePosition);
				game.movePiece(7, 7, 7, 5, positions.get(7).get(7));

			}else if(position.getX() == 7 && position.getY() == 2) {
				//long side castle
				
				activePosition.getPiece().setHasMoved(true);
				positions.get(7).get(0).getPiece().setHasMoved(true);
				game.movePiece(7, 4, 7, 2, activePosition);
				game.movePiece(7, 0, 7, 3, positions.get(7).get(0));

			}
		
		}else {
			
			if(position.getX() == 0 && position.getY() == 6) {
				
				activePosition.getPiece().setHasMoved(true);
				positions.get(0).get(0).getPiece().setHasMoved(true);
				game.movePiece(0, 4, 7, 6, activePosition);
				game.movePiece(0, 0, 0, 5, positions.get(0).get(0));

			}else if(position.getX() == 0 && position.getY() == 2) {
				//long side castle
				
				activePosition.getPiece().setHasMoved(true);
				positions.get(0).get(0).getPiece().setHasMoved(true);
				game.movePiece(0, 4, 0, 2, activePosition);
				game.movePiece(0, 0, 0, 3, positions.get(0).get(0));

			}
			
			
		}
	}
	
	/**
	 * Checks if there is any piece between the king and the rook.
	 * And checks if any of the opponent pieces prevents castling.
	 * 
	 * @param x
	 * @param y
	 * @param positions
	 * @return
	 */
	public boolean checkCanCastle(int x, int y,ArrayList<ArrayList<Position>> positions) {
		if(positions.get(x).get(y).getPiece() != null && positions.get(x).get(y).getPiece().getName().equals(side == Player.Side.WHITE?"white rook":"black rook") && !positions.get(x).get(y).getPiece().getHasMoved()) {
			ArrayList<ArrayList<Integer>> positionsToCheck = givePositionsToCheck(positions.get(x).get(y),activePosition);
			
			for(ArrayList<Integer> positionToCheck: positionsToCheck) {
				
				if(positions.get(positionToCheck.get(0)).get(positionToCheck.get(1)).getPiece() != null){
					
					return false;
					
				}
				for(ArrayList<Position> row: positions) {
					
					for(Position positionOpponent: row) {
						if(positionOpponent.getPiece() != null && positionOpponent.getPiece().getSide() != side) {
							if(positionOpponent.getPiece().checkLegalMove(positionOpponent, new Position(positionToCheck, null), positions,true,false)){
								return false;
							}
						}
					}
					
				}
			}																															
			return true;
		}else {
			return false;
		}
	}
	
	/**
	 * Checks if the player can castle.
	 * @param positions
	 * @param position
	 * @return
	 */
	public boolean canCastle(ArrayList<ArrayList<Position>> positions,Position position) {
		
		if(activePosition.getPiece()!=null && activePosition.getPiece().getTypeString().equals("king")){
			if(!activePosition.getPiece().getHasMoved()) {
				if(getSide() == Player.Side.WHITE) { 
					if(position.getX() == 7 && position.getY() == 6) {
						//short side castle
						
						return checkCanCastle(7,7,positions);
					}else if(position.getX() == 7 && position.getY() == 2) {
						//long side castle
						return checkCanCastle(7,0,positions);
					}
					}else {
						
						if(position.getX() == 0 && position.getY() == 6) {
							//short side castle
							
							return checkCanCastle(0,7,positions);
						}else if(position.getX() == 0 && position.getY() == 2) {
							//long side castle
							return checkCanCastle(0,0,positions);
						}
						
					}
			}
		}
		return false;
	}
	
	/**
	 * Sets the current active position of the player.
	 * 
	 * The current active position is highlighted on the chessboard.
	 * @param newActivePosition
	 * @param positions the current positions of the chessboard.
	 */
	public void setActivePosition(Position newActivePosition,ArrayList<ArrayList<Position>> positions) {
	   if(newActivePosition!=null&&newActivePosition.getPiece()!=null&&newActivePosition.getPiece().getTypeString().equals("king")&&newActivePosition.getPiece().getSide() == this.side) {
		   ArrayList<ArrayList<Integer>> posPositions = game.getMyKingPossibleMoves(positions,side);
		  
		   if(posPositions.size()==0) {
			   System.out.println("cant move king");
			   return;
		   }
		   else if(kingIsChecked&&posPositions.size()>0&&activePosition!=newActivePosition) {
			   activePosition = newActivePosition;
			   return;
		   }
		   
	   }
		
		if(activePosition == newActivePosition|| newActivePosition == null) {
			activePosition = null;
		}else if(kingIsChecked&&((activePosition != null && !activePosition.getPiece().getTypeString().equals("king"))||activePosition == null)) {
			boolean escape = canIescape(positions,newActivePosition);
			if(newActivePosition.getPiece().getTypeString().equals("king")&&newActivePosition.getPiece().getSide() == this.side&&!escape) {
				System.out.println("you need to move your king, the king is checked");
			}else if(newActivePosition.getPiece().getTypeString().equals("king")) {
				activePosition = newActivePosition;
			}else if(escape) {
				activePosition = newActivePosition;
			}
		}else if(newActivePosition.getPiece() == null) {
			System.out.println("there is no piece here");
		}
		else if(newActivePosition.getPiece().getSide() == this.side) {
			activePosition = newActivePosition;
		}else {
			System.out.println("this piece is not yours");
		}
		
	}
	/**
	 * returns true or false if the player can make a move to escape checkmate.
	 * 
	 * this can be taking the piece that produces the check or blocking the check.
	 * 
	 * @param positions
	 * @param newActivePosition
	 * @return
	 */
	public boolean canIescape(ArrayList<ArrayList<Position>> positions,Position newActivePosition) {
	
		boolean canEscape=false;
		
			ArrayList<ArrayList<Position>> newPositions = game.copyPositions(positions);
			for(Position piece: piecesToBeRemoved) {
				if(piece.getPiece()!=null) {
					    
					  // check if there is a piece of yours that is not the king that can block the check
                      if((piece.getPiece().getTypeString().equals("bishop")||piece.getPiece().getTypeString().equals("queen")
                    		  ||piece.getPiece().getTypeString().equals("rook"))&&!newActivePosition.getPiece().getTypeString().equals("king")) {
                    	  ArrayList<ArrayList<Integer>> positionsToCheck = givePositionsToCheck(piece,game.getPositionKing(positions, side));
                    	  
                    	  for(ArrayList<Integer> possiblePos:positionsToCheck) {
                    		  Position newPos =  new Position(possiblePos,null);
                    		 
                    		  if(newPos.getX()>=0&&newPos.getX()<8&&newPos.getY()>=0&&newPos.getY()<8) {
                    		  if(newActivePosition.getPiece().checkLegalMove(newActivePosition,newPos, newPositions,false,false)) {
                    			  newPositions.get(newPos.getX()).get(newPos.getY()).setPiece(newActivePosition.getPiece());
          						newPositions.get(newActivePosition.getX()).get(newActivePosition.getY()).setPiece(null);
          						
          						iCanEscape=true;
          						canEscape= !game.isMyKingChecked(newPositions,true,side);
          						if(canEscape) {
          							return true;
          						}
                    		  }
                    		  }
                    	  }
                      }
					
                    // check if the piece that the player wants to be the active position can take the piece that produces the check 
                    // and checks if that piece takes the check producing piece the king is not still checked.
					if(newActivePosition.getPiece() != null &&newActivePosition.getPiece().checkLegalMove(newActivePosition, piece, newPositions,true,false)) {
						newPositions.get(piece.getX()).get(piece.getY()).setPiece(newActivePosition.getPiece());
						newPositions.get(newActivePosition.getX()).get(newActivePosition.getY()).setPiece(null);
						
						canEscape= !game.isMyKingChecked(newPositions,true,side);
						if(canEscape) {
  							return true;
  						}
					}
				}
			}	
		return canEscape;
	}
	/**
	 * return positions where a piece can move to to block the check.
	 * @param piece
	 * @param positionKing
	 * @return
	 */
	public ArrayList<ArrayList<Integer>> givePositionsToCheck(Position piece,Position positionKing){
		int cx = positionKing.getX();
  		int cy = positionKing.getY();
  		
  		int nx = piece.getX();
  		int ny = piece.getY();
  		  		
  		int directionX = (nx-cx<0)?-1:1;
		int directionY = (ny-cy<0)?-1:1;
  		
  		int moveH =Math.abs(nx-cx);
  		int moveV =Math.abs(ny-cy);
  		if(moveH==0) {
  			directionX=0;
  		}
  		if(moveV==0) {
  			directionY=0;
  		}
  		 		
  		ArrayList<ArrayList<Integer>> positionsToCheck = new ArrayList<ArrayList<Integer>>();
  		cx += directionX;
	    cy += directionY;
	   
  		for(int i=0;i<8;i++) {
  			if((cx!=nx&&cy!=ny)||cx!=nx&&cy==ny||cy!=ny&&cx==nx) {
  				
  	  	        ArrayList<Integer> newMove = new ArrayList<Integer>();
  				newMove.add(cx);
  				newMove.add(cy);
  				positionsToCheck.add(newMove);
  				cx += directionX;
  	  	        cy += directionY;
  				
  			}
  		}
  		
  		return positionsToCheck;
	}

}
