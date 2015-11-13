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
	public Truck (BufferedImage img, int spe, String dir, int r, int c) {
		super(img, spe, dir, r, c);
		
	}

}
