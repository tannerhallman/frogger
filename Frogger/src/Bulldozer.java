import java.awt.Color;
import java.awt.Image;
import java.awt.Shape;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 * Bulldozer.
 * Another change.1
 * @author th
 *
 */
public class Bulldozer extends Car {
	Image img = ImageIO.read(new File("")); //TODO replace quotes
	ImageIcon ic = new ImageIcon(img);
	public Bulldozer(double spe, String dir, Color col, int lan, boolean saf, TwoDPoint loc) {
		super(img, spe, dir, col, lan, saf, loc);
		
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
