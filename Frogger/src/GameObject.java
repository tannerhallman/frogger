import java.awt.image.BufferedImage;

/**
 * This is the abstract GameObject that is the parent for most objects in the game.
 * @author th
 *
 */

public abstract class GameObject {
	private double speed; //the speed of the object
	private int row; // the lane or row the object is in.
	private int col; // the column the object is in
	private boolean safe; //true by default
	private BufferedImage image; //the image of the object
	private int objectSeperatorDistance; //the distance between itself and another object "behind" it

	/**
	 * This is the full constructor that allows you to explicitly assign all values.
	 * @param img the image of the object.
	 * @param spe the speed of the object.
	 * @param dir the direction.
	 * @param rowOfObject the lane/row.
	 * @param colOfObject the column.
	 * @param saf if the object is safe to land on or not.
	 */
	public GameObject (BufferedImage img, double spe, String dir, int rowOfObject, int colOfObject, int objectSD){
		this.image = img;
		this.speed = spe;
		this.row = rowOfObject; //could also be Y coord depending on what value is passed
		this.col = colOfObject; //could also be X coord depending on what value is passed
		this.safe = true; // true by default
		this.objectSeperatorDistance = img.getWidth(); //default is width of itself so no overlapping objects
	}
	
	public GameObject (BufferedImage img, double spe, String dir, int r, int c){
		this.image = img;
		this.speed = spe;
		this.row = r;
		this.col = c;
		this.safe = true; // true by default
		objectSeperatorDistance = 2; //2 pixels by default
	}
	
	/**
	 * 
	 */
	public abstract void move();
	
	/**
	 * 
	 */
	public abstract void collision();
	
	
	public BufferedImage getImage() {
		return image;
	}

	public void setImage(BufferedImage newImage) {
		this.image = newImage;
	}

	/**
	 * @return
	 */
	public double getSpeed() {
		return speed;
	}

	/**
	 * @param speed
	 */
	public void setSpeed(double speed) {
		this.speed = speed;
	}

	/**
	 * @return
	 */
	public int getRow() {
		return row;
	}

	/**
	 * @param newRow
	 */
	public void setRow(int newRow) {
		this.row = newRow;
	}
	
	/**
	 * @return
	 */
	public int getCol() {
		return col;
	}

	/**
	 * @param newCol
	 */
	public void setCol(int newCol) {
		this.row = newCol;
	}

	/**
	 * @return
	 */
	public boolean isSafe() {
		return safe;
	}

	/**
	 * @param safe
	 */
	public void setSafe(boolean safe) {
		this.safe = safe;
	}

	public int getObjectSeperatorDistance() {
		return objectSeperatorDistance;
	}

	public void setObjectSeperatorDistance(int OSD) {
		this.objectSeperatorDistance = OSD;
	}
	
}
