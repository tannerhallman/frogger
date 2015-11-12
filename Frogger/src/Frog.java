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
	public Frog(BufferedImage img, double spe, String dir, int row, int col, boolean activeFrog) {
		super(img, spe, dir, row, col);
		this.isFrogActive = activeFrog;
	}

	/* (non-Javadoc)
	 * @see GameObject#move()
	 */
	@Override
	public void move() {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see GameObject#collision()
	 */
	@Override
	public void collision() {
		// TODO Auto-generated method stub

	}

}
