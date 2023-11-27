package chessgui;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import chess.Game;
import chess.Player;
import chess.Position;
import pieces.Piece;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ChessFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	Game game = new Game();
	ArrayList<ArrayList<SquareOnBoard>> squares= new ArrayList<ArrayList<SquareOnBoard>>();
	ButtonGroup buttongroup = new ButtonGroup();
	private Map<JButton, SquareOnBoard> buttonSquareMap = new HashMap<>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					ChessFrame frame = new ChessFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ChessFrame() {
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 800);
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);
	
		mijnInit();
		
	}
	
	public void createChessFrame() {
		
		 
		for (int j = 0; j < 8; j++) {
			ArrayList<SquareOnBoard> rowList = new ArrayList<>();

				for(int i=0;i<8;i++) {
 			    JButton button = new JButton();	
				//MyButton button = new MyButton(j,i);
 			   
				
				button.setBounds(160+i*60, 50+j*60, 60, 60);
				button.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						clickedOnPiece(e);
					}
				});
				contentPane.add(button);
				SquareOnBoard square = new SquareOnBoard(button,j,i);
				buttonSquareMap.put(button, square);
			    rowList.add(square);

			}
			squares.add(rowList);
		}
	}
	
	public void clickedOnPiece(ActionEvent e) {
		JButton clickedButton = (JButton) e.getSource();

		 SquareOnBoard correspondingSquare = buttonSquareMap.get(clickedButton);
	    // Now you can access the x and y values of the clicked MyButton
		 if (correspondingSquare != null) {
	            int clickedX = correspondingSquare.getX();
	            int clickedY = correspondingSquare.getY();
	            
	            game.clickedOnPiece(clickedX,clickedY);
	    
	            updateGame(); 
	        }  
	}
	
	public void updateGame() {
		 ArrayList<ArrayList<Position>> positions = game.getPositions();
		 renderPositions(positions);
		 if(game.isGameDone()) {
				
				renderGameOver();
			}
	}
	
	public void gameInit() {
		createChessFrame();
		game.startGame();
		 updateGame();
		
	}
	
	public void renderGameOver() {
		JLabel label2 = new JLabel("game over");
		label2.setBounds(100, 550, 100, 100);
		JButton button2 = new JButton("Back to start screen");
		button2.setBounds(400, 550, 200, 150);
		button2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mijnInit();
			}
		});
		contentPane.add(button2);
		contentPane.add(label2);
		contentPane.revalidate();
		contentPane.repaint();
	}

	public void mijnInit() {
		System.out.println("mijninit");
		cleanScreen();
		renderStartScreen();
	}
	
	public void cleanScreen() {
		contentPane.removeAll();
	}
	
	public void startGame() {
		cleanScreen();
		game.setGameDone(false);
		gameInit();
	}
	
	public void renderStartScreen() {
		JButton button = new JButton("Start");
		button.setBounds(100, 200, 100, 100);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				startGame();
			}
		});
		contentPane.add(button);
		contentPane.revalidate();
		contentPane.repaint();
	}
	
	

	public void renderPositions( ArrayList<ArrayList<Position>> positions) {
		 Player playerAtTurn = game.getPlayerAtTurn();
         Position activePosition = playerAtTurn.getActivePosition();
        
         int col =0;
         boolean turn = true;
		
		for (ArrayList<SquareOnBoard> squaresRow: squares) {
			for (SquareOnBoard square: squaresRow) {
			JButton button = square.getButton();
	  
			Position position = positions.get(square.getX()).get(square.getY());
			if(col%2==0) {
        		button.setBackground(Color.WHITE);
        	}else {
        		button.setBackground(new Color(150,75,0));
        	}
			if(activePosition == position ) {
				button.setBackground(Color.GRAY);
			}

			
        	Piece piece = position.getPiece();
        	
        	
        	if(piece != null) {

        		//button.setText(""+piece.getSymbol());
        		 ImageIcon icon = new ImageIcon(piece.getSymbol());
        		button.setIcon(icon);
        	}else {
        		//button.setText("");
        		button.setIcon(null);
        	}
        		col++;
			}
			col=turn?1:0;
			turn = !turn;
			
		}
		contentPane.revalidate();
		contentPane.repaint();
	}
}

