package chessgui;

import javax.swing.JButton;

public class MyButton extends JButton {
	public int x;
	private int y;
	
	
	public MyButton( int x, int y) {
//		super(""+x+y);
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
