import java.awt.image.BufferedImage;

/**
 * 
 */

/**
 * @author th
 *
 */
public class Frog extends GameObject {

	private boolean isFrogActive;
	/**
	 * 
	 */
	public Frog(BufferedImage img, int spe, String dir, int row, int col) {
		super(img, spe, dir, row, col);
		this.isFrogActive = true;
		this.setMoveWithoutInput(false);
	}

}
