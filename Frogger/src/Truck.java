import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

/**
 * @author th
 *
 */
public class Truck extends GameObject {
	/**
	 * 
	 */
	Image img;
	ImageIcon ic;
	public Truck (BufferedImage img, double spe, String dir, int r, int c) {
		super(img, spe, dir, r, c);
		
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
