import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 * @author th
 *
 */
public class Sedan extends GameObject{
	Image img;
	ImageIcon ic;
	/**
	 * 
	 */
	public Sedan (BufferedImage img, double spe, String dir, int r, int c) {
		super(img, spe, dir, r, c);
		
	}

}
