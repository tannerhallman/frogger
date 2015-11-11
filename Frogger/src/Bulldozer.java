import java.awt.Color;
import java.awt.Image;
import java.awt.Shape;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 * Bulldozer.
 * A
 * @author th
 *
 */
public class Bulldozer extends Car {
	Image img;
	ImageIcon ic;
	public Bulldozer(double spe, String dir, Color col, int lan, boolean saf, TwoDPoint loc) {
		super(spe, dir, col, lan, saf, loc);
		try {
			img = ImageIO.read(new File(""));
			ic = new ImageIcon(img);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} //TODO replace quotes
		
	}

	@Override
	public void move() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void collision() {
		// TODO Auto-generated method stub
		
	}

}
