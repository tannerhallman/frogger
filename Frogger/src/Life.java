import java.awt.image.BufferedImage;

/**
 * 
 */

/**
 * @author th
 *
 */
public class Life extends GameObject {

	private double speed; //the speed of the object
	private int row; // the lane or row the object is in.
	private int col; // the column the object is in
	private boolean safe; //true by default
	private BufferedImage image; //the image of the object
	private int objectSeperatorDistance; //the distance between itself and another object "behind" it
	

	public Life(BufferedImage img, int spe, String dir, int rowOfObject, int colOfObject, int objectSD) {
		super(img, spe, dir, rowOfObject, colOfObject, objectSD);
		
		this.image = img;
		this.speed = spe;
		this.row = rowOfObject; //could also be Y coord depending on what value is passed
		this.col = colOfObject; //could also be X coord depending on what value is passed
		this.safe = true; // true by default
		this.objectSeperatorDistance = img.getWidth(); //default is width of itself so no overlapping objects
	}

	/**
	 * @param img
	 * @param spe
	 * @param dir
	 * @param r
	 * @param c
	 */
	public Life(BufferedImage img, int spe, String dir, int r, int c) {
		super(img, spe, dir, r, c);
		// TODO Auto-generated constructor stub
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
