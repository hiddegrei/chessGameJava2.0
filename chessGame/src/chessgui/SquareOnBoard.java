package chessgui;

import javax.swing.JButton;

public class SquareOnBoard {
	private JButton button;
	private int x;
	private int y;
	
	public SquareOnBoard(JButton button, int x, int y) {
		this.button = button;
		this.x = x;
		this.y =y;
		
	}
	public JButton getButton() {
		return button;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}

}
