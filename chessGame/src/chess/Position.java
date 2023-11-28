/**
 * 
 */
package chess;

import java.util.ArrayList;

import pieces.Piece;

/**
 * 
 */
public class Position {
	private ArrayList<Integer> position;
	private Piece piece;

	/**
	 * Class constructor
	 */
	public Position(ArrayList<Integer> position, Piece piece) {
		// TODO Auto-generated constructor stub
		this.position = position;
		this.piece = piece;
	}
	
	public Piece getPiece() {
		if(piece!= null) {
			return piece;
			
		}else {
			return null;
		}
		
	}
	
	/**
	 * Makes a copy of the position.
	 * @return
	 */
	public Position copy() {
		ArrayList<Integer> newpos = new ArrayList();
		newpos.add(this.position.get(0));
		newpos.add(this.position.get(1));
		Piece newPiece = this.getPiece();
		return new Position(newpos,newPiece);
	}
	
	public void setPiece(Piece piece) {
		this.piece = piece;
	}
	
	public ArrayList<Integer> getPosition(){
		return position;
	}
	
	public int getX() {
		return position.get(0);
	}
	public int getY() {
		return position.get(1);
	}

}
