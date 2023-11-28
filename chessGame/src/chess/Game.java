package chess;

import java.lang.reflect.Array;
import java.util.ArrayList;

import chessgui.ChessFrame;
import pieces.Piece;
import pieces.Queen;

/**
 * 
 */
public class Game {
	private ChessBoard chessBoard;
	private Player player1;
	private Player player2;
	private Player playerAtTurn;
	private boolean gameEnd = false;
	
	ArrayList<Piece> defeatedPieces = new ArrayList<Piece>();

	/**
	 * 
	 */
	public Game() {
	}
	
	/**
	 * initialize chessboard and create two new players.
	 * 
	 */
	public void init() {
		this.chessBoard = new ChessBoard();
		
		this.player1 = new Player(Player.Side.WHITE, this);
		this.player2 = new Player(Player.Side.BLACK, this);
		defeatedPieces = new ArrayList<Piece>();
			
	}
	
	public void startGame() {
		init();
		playerAtTurn = player1;
		
	}
	
	public ArrayList<Piece> getDefeatedPieces() {
		return defeatedPieces;
	}
	
	public Player getPlayerAtTurn() {
		return playerAtTurn;
	}
	public Player getPlayerNotAtTurn() {
	
		if(player1 == getPlayerAtTurn()) {
			return player2;
		}else {
			return player1;
		}
		
	}
	
	public void setPlayerAtTurn(Player player) {
		playerAtTurn = player;
	}
	
	/**
	 * this function fires when a player clicks on a piece.
	 * @param x the x value of the clicked piece.
	 * @param y the y value of the clicked piece.
	 */
	public void clickedOnPiece(int x, int y) {
		if(!gameEnd) {
			
		
        Position position = getPositions().get(x).get(y);
		Player playerAtTurn = getPlayerAtTurn();
		if((playerAtTurn.getActivePosition() == null) || (playerAtTurn.getActivePosition() == position)) {
			setActivePlayerPosition(position);
		}else {
			checkMove(playerAtTurn.getActivePosition(),position);
			
		}
		}
		
	}
	
	/**
	 * sets the active position of the player at turn.
	 * If the active position is a legal position.
	 * 
	 * 
	 * @param position
	 */
	public void setActivePlayerPosition(Position position) {
		Player playerAtTurn = getPlayerAtTurn();
		playerAtTurn.setActivePosition(position,getPositions());
	}
	
	/**
	 * checks the move to be made. This function is only fired when the 
	 * player at turn has a active position. If the move is legal, the move is made.
	 * If the move is not legal the move is not made and the player at turn needs to make a new move.
	 * 
	 * @param curPosition the current position that is the active position of the player( the piece the player wants to move).
	 * @param newPosition the position the player wants to move the piece at the curPosition.
	 */
	public void checkMove(Position curPosition,Position newPosition) {
		Piece piece = curPosition.getPiece();
		if(piece != null) {
		ArrayList<ArrayList<Position>> positions = getPositions();
		ArrayList<ArrayList<Integer>> possibleMoves = getPlayerAtTurn().getPossibleMoves();
		boolean isPossible =false;
		
		boolean isKing = getPlayerAtTurn().getActivePosition().getPiece().getTypeString().equals("king");
		
		if(getPlayerAtTurn().getICanEscape()) {

			
			if(piece.checkLegalMove(curPosition,newPosition,positions,true,false)){
				
				if(!isMyKingCheckedInNewPosition(positions,curPosition,newPosition,getPlayerAtTurn().getSide())) {
					isPossible =piece.checkLegalMove(curPosition,newPosition,positions,false,true);
					
				}else {
					isPossible=false;
				}
			}
			
		}
		else if((getPlayerAtTurn().getKingIsChecked()&&isKing)||isKing) {
			
			 isPossible = piece.checkLegalMove(curPosition,newPosition,positions,possibleMoves);
		}else {
			isPossible =piece.checkLegalMove(curPosition,newPosition,positions,false,true);
		}

		
		if(isPossible ) {
			
			
			Position newPos = positions.get(newPosition.getX()).get(newPosition.getY());
			Piece curPieceOnNewSquare = newPos.getPiece();
			if(curPieceOnNewSquare != null) {
				 defeatedPieces.add(curPieceOnNewSquare);
			}
			newPos.setPiece(piece);
			curPosition.setPiece(null);
			setActivePlayerPosition(null);
			getPlayerAtTurn().setKingIsChecked(false);

			getPlayerAtTurn().setICanEscape(false);
			getPlayerNotAtTurn().setPossibleMoves(getMyKingPossibleMoves(positions,getNotActiveSide()));
			
			if(newPos.getPiece().getTypeString().equals("pawn")) {
				if(newPos.getPiece().getSide() == Player.Side.BLACK) {
					if(newPos.getX() == 7) {
						newPos.setPiece(new Queen(Player.Side.BLACK));
					}					
				}else {
					if(newPos.getX() == 0) {
						newPos.setPiece(new Queen(Player.Side.WHITE));
					}					
				}
				
			}
			
			if(isMyKingChecked(positions,false, getNotActiveSide())) {
				getPlayerNotAtTurn().setKingIsChecked(true);
				
				ArrayList<ArrayList<Integer>> possibleMoves2= getMyKingPossibleMoves(positions,getNotActiveSide());
				if(possibleMoves2.size()==0) {
					if(!isMate(positions)) {
						//getPlayerNotAtTurn().setKingIsChecked(true);

						switchTurns();
					}else {
						gameIsDone();
					}
					
				}else {
					switchTurns();
				}
				
			}else {
				switchTurns();
			}
			
		}else if(getPlayerAtTurn().canCastle(positions,newPosition)) {
			getPlayerAtTurn().castle(positions,newPosition);
			
			getPlayerAtTurn().setPossibleMoves(getMyKingPossibleMoves(positions,getActiveSide()));
			switchTurns();
			
		}
		}
		
	}
	
	public void movePiece(int cx, int cy, int nx, int ny, Position position) {
		ArrayList<ArrayList<Position>> positions = getPositions();
		positions.get(nx).get(ny).setPiece(position.getPiece());
		positions.get(cx).get(cy).setPiece(null);
		
		
	}
	
	/**
	 * sets the game at game over.
	 */
	public void gameIsDone() {
		gameEnd=true;
	}
	
    /*
     * returns true if the game is finished and returns false if the game is not finished.
     */
	public boolean isGameDone() {
		return gameEnd;
	}
	
	public void setGameDone(boolean gameEnd) {
		this.gameEnd=gameEnd;
	}
	
	/**
	 * returns the current positions of the board.
	 * @return the positions of the board.
	 */
	public ArrayList<ArrayList<Position>> getPositions(){
		return chessBoard.getPositions();
		}
	
	/**
	 * game moves to the next move. This means that the player at turn and the player not at turn switch places.
	 */
	public void switchTurns() {
		Player curPlayer = getPlayerAtTurn();
		curPlayer.setActivePositionForce();
		if(player1 == curPlayer) {
			setPlayerAtTurn(player2);
		}else {
			setPlayerAtTurn(player1);
		}
		
	}
	
	/**
	 * Returns the side of the player at turn.
	 * If the player at turn plays with black it returns Player.Side.BLACK.
	 * If the player at turn plays with white it returns Player.Side.WHITE.
	 * @return Player.Side
	 */
	public Player.Side getActiveSide(){
		return getPlayerAtTurn().getSide();
	}
	
	/**
	 * Returns the side of the player not at turn.
	 * If the player not at turn plays with black it returns Player.Side.BLACK.
	 * If the player not at turn plays with white it returns Player.Side.WHITE.
	 * @return Player.Side
	 */
	public Player.Side getNotActiveSide(){
		return getPlayerNotAtTurn().getSide();
	}
	

	

	/**
	 * Returns the position of the king at the desired side.
	 * If the param side is Player.Side.WHITE it returns the position of the white king.
	 * If the param side is Player.Side.BLACK it returns the position of the black king.
	 * @param positions positions of the board.
	 * @param side side of the king to be found.
	 * @return
	 */
	public Position getPositionKing(ArrayList<ArrayList<Position>> positions, Player.Side side) {
		Player.Side activeSide = side;
		
				for(ArrayList<Position> row: positions) {
					for(Position position: row) {
						if(position.getPiece()!= null && position.getPiece().getSide()== activeSide&& position.getPiece().getTypeString().equals("king")) {
							return position;
							
						}
					}
				}
				return null;
	}
	

	
	

	/**
	 * Checks if the king is checked at the desired side.
	 * If the param side is Player.Side.WHITE it returns the white king is checked.
	 * If the param side is Player.Side.BLACK it returns the black king is checked.
	 * @param positions the positions of the board to be checked.
	 * @param simulation does the function needs to run as a simulation.
	 * @param side side of the king.
	 * @return
	 */
	public boolean isMyKingChecked(ArrayList<ArrayList<Position>> positions,boolean simulation,Player.Side side) {
		Player.Side activeSide = side==Player.Side.WHITE?Player.Side.BLACK:Player.Side.WHITE;
		Position posOppositeKing = getPositionKing(positions,side);
		
		boolean checked = false;
		
		for(ArrayList<Position> row: positions) {
			for(Position position: row) {
				if(position.getPiece()!= null && position.getPiece().getSide()== activeSide) {
					
					if(position.getPiece().checkLegalMove(position,posOppositeKing,positions,true,false)) {

						if(!simulation) {
							getPlayerNotAtTurn().addPieceToBeRemoved(position);
						}
						
						checked = true;
					}
				}
			}
		}
		return checked;
	}
	
	/**
	 * Checks if my king(the king at the side) is checked if I move the piece at the position moveFrom to the position moveTo.
	 * @param positions positions of the board.
	 * @param moveFrom  position of the piece to be moved.
	 * @param moveTo	position of the piece to be moved to.
	 * @param side 		side of the king.
	 * @return
	 */
	public boolean isMyKingCheckedInNewPosition(ArrayList<ArrayList<Position>> positions,Position moveFrom,Position moveTo, Player.Side side) {
		ArrayList<ArrayList<Position>> newPositions = copyPositions(positions);
		
		newPositions.get(moveTo.getX()).get(moveTo.getY()).setPiece(moveFrom.getPiece());
		newPositions.get(moveFrom.getX()).get(moveFrom.getY()).setPiece(null);
		
		
		if(isMyKingChecked(newPositions,true, side)) {
			return true;
		}else {
		return false;
		}
	}
	

	/**
	 * Gets the possible moves of the king in the given position.
	 * @param positions
	 * @param side
	 * @return
	 */
	public  ArrayList<ArrayList<Integer>> getMyKingPossibleMoves(ArrayList<ArrayList<Position>> positions, Player.Side side) {
		Position posKing = getPositionKing(positions,side);
		int[][] moves = {{-1,-1},{-1,0},{-1,1},
		{0,-1},{0,1},
		{1,-1},{1,0},{1,1}};
		ArrayList<ArrayList<Integer>> possibleMoves = new ArrayList();
		
		for(int i=0;i<8;i++) {
			int x = posKing.getX()+moves[i][0];
			int y = posKing.getY()+moves[i][1];
			if((x>=0&&x<8)&&(y>=0&&y<8)) {
				
				if(positions.get(x).get(y).getPiece()==null||(positions.get(x).get(y).getPiece()!=null&&positions.get(x).get(y).getPiece().getSide()!= posKing.getPiece().getSide())) {
					
					ArrayList<ArrayList<Position>> newPositions = copyPositions(positions);
					
					newPositions.get(x).get(y).setPiece(posKing.getPiece());
					newPositions.get(posKing.getX()).get(posKing.getY()).setPiece(null);
					
					if(!isMyKingChecked(newPositions,true, side)) {
						
						ArrayList<Integer> newMove = new ArrayList<Integer>();
						newMove.add(x);
						newMove.add(y);
						possibleMoves.add(newMove);
						
					}
				}
			}
		}
		
		
		return possibleMoves;
	}
	
	/**
	 * checks if the position is mate
	 * @param positions
	 * @return
	 */
	public boolean isMate(ArrayList<ArrayList<Position>> positions) {
		boolean isMate=true;
		for(ArrayList<Position> row: positions) {
			
			for(Position position: row) {
				if(position.getPiece()!=null && position.getPiece().getSide() == getNotActiveSide()) {
					isMate = !getPlayerNotAtTurn().canIescape(positions, position);
					if(!isMate) {
							return false;
						}
				}
			}
			
		}
		return isMate;
	}
	
	/**
	 * copies the positions and returns a new arraylist of the copy of the positions.
	 * 
	 * This function makes a deep copy.
	 * @param positions positions to be copied.
	 * @return the copied positions.
	 */
	public ArrayList<ArrayList<Position>> copyPositions(ArrayList<ArrayList<Position>> positions){
		ArrayList<ArrayList<Position>> newPositions = new ArrayList();
		for(ArrayList<Position> row: positions) {
			ArrayList<Position> newRow = new ArrayList();
			for(Position position: row) {
				newRow.add(position.copy());
			}
			newPositions.add(newRow);
		}
		return newPositions;
	}

}
