package chessgui;

import javax.swing.JButton;

public class MyButton extends JButton {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public int x;
	private int y;
	
	
	public MyButton( int x, int y) {
		this.x = x;
		this.y = y;	
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}

}
