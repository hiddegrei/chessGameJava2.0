package chess;

import java.util.ArrayList;

import pieces.*;


public class ChessBoard {
	ArrayList<ArrayList<Position>> positions;

	
	public ChessBoard() {
		initializeBoard();
		
	}
	
	/**
	 * adds a piece to the row
	 * @param rowList
	 * @param x
	 * @param y
	 * @param piece
	 */
	public void addPieceToList(ArrayList<Position> rowList, int x, int y, Piece piece) {
	    ArrayList<Integer> coordinates = new ArrayList<>();
	    coordinates.add(x);
	    coordinates.add(y);
	    Position position = new Position(coordinates, piece);
	    rowList.add(position);
	}
	
	/**
	 * Initialize chess board with all the pieces in the start position.
	 */
	public void initializeBoard() {
		positions = new ArrayList<ArrayList<Position>>();

        
		//create the top row
		ArrayList<Position> rowList1 = new ArrayList<Position>();
		
		

		addPieceToList(rowList1, 0, 0, new Rook(Player.Side.BLACK));
		addPieceToList(rowList1, 0, 1, new Knight(Player.Side.BLACK));
		addPieceToList(rowList1, 0, 2, new Bishop(Player.Side.BLACK));
		addPieceToList(rowList1, 0, 3, new Queen(Player.Side.BLACK));
		addPieceToList(rowList1, 0, 4, new King(Player.Side.BLACK));
		addPieceToList(rowList1, 0, 5, new Bishop(Player.Side.BLACK));
		addPieceToList(rowList1, 0, 6, new Knight(Player.Side.BLACK));
		addPieceToList(rowList1, 0, 7, new Rook(Player.Side.BLACK));
		    
		    
		positions.add(rowList1);
		
		    
		//add top pawns
		for (int row = 1; row < 2; row++) {
			ArrayList<Position> rowList = new ArrayList<Position>();
			
			for (int col = 0; col < 8; col++) {
		    
		    ArrayList<Integer> coordinates = new ArrayList<Integer>();
		    coordinates.add(row); // Add the x-coordinate (0)
		    coordinates.add(col); // Add the y-coordinate (0)
		    Pawn pawn = new Pawn(Player.Side.BLACK); // Create a Rook object (assuming you have a Rook class)
		    Position pawnPosition = new Position(coordinates, pawn);
		    rowList.add(pawnPosition);
		   
			}
			positions.add(rowList);
		}
		
		//add the positions without pieces.
		
		for (int row = 2; row < 6; row++) {
			ArrayList<Position> rowList = new ArrayList<Position>();
			
			for (int col = 0; col < 8; col++) {
		    
		    ArrayList<Integer> coordinates = new ArrayList<Integer>();
		    coordinates.add(row); // Add the x-coordinate (0)
		    coordinates.add(col); // Add the y-coordinate (0)
		   
		    Position pawnPosition = new Position(coordinates, null);
		    rowList.add(pawnPosition);
		   
			}
			positions.add(rowList);
		}
		
		//add bottom pawns
				for (int row = 6; row < 7; row++) {
					ArrayList<Position> rowList = new ArrayList<Position>();
					
					for (int col = 0; col < 8; col++) {
				    
				    ArrayList<Integer> coordinates = new ArrayList<Integer>();
				    coordinates.add(row); // Add the x-coordinate (0)
				    coordinates.add(col); // Add the y-coordinate (0)
				    Pawn pawn = new Pawn(Player.Side.WHITE); // Create a Rook object (assuming you have a Rook class)
				    Position pawnPosition = new Position(coordinates, pawn);
				    rowList.add(pawnPosition);
				   
					}
					positions.add(rowList);
				}
				
				//create the bottom row
				ArrayList<Position> rowList2 = new ArrayList<Position>();
				
				

				addPieceToList(rowList2, 7, 0, new Rook(Player.Side.WHITE));
				addPieceToList(rowList2, 7, 1, new Knight(Player.Side.WHITE));
				addPieceToList(rowList2, 7, 2, new Bishop(Player.Side.WHITE));
				addPieceToList(rowList2, 7, 3, new Queen(Player.Side.WHITE));
				addPieceToList(rowList2, 7, 4, new King(Player.Side.WHITE));
				addPieceToList(rowList2, 7, 5, new Bishop(Player.Side.WHITE));
				addPieceToList(rowList2, 7, 6, new Knight(Player.Side.WHITE));
				addPieceToList(rowList2, 7, 7, new Rook(Player.Side.WHITE));
				    
				    
				positions.add(rowList2);
    }
		
		
	/**
	 * Returns the positions of the chess board.
	 * @return
	 */
	public ArrayList<ArrayList<Position>> getPositions(){
		return positions;
		
	}
	


}
