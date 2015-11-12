import java.awt.Color;

import javax.swing.JFrame;

public class GameWindow extends JFrame {
	
	public GameWindow(){
		setTitle("Frogger");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Screen screen = new Screen();
		add(screen);
		setSize(400, 680);
		pack();
		
		screen.setFocusable(true);
		screen.requestFocusInWindow();
		
		setVisible(true);
	}
	
	
	public static void main (String[] args){
		GameWindow window = new GameWindow();
	}
}
