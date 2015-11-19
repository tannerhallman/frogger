import java.awt.image.BufferedImage;

/**
 * This is the abstract GameObject that is the parent for most objects in the game.
 * @author th
 *
 */

public class GameObject {
	private int speed; //the speed of the object , 1 - 10 pixels/second
	private int row; // the lane or row the object is in.
	private int col; // the column the object is in
	private boolean safe; //true by default
	private BufferedImage image; //the image of the object
	private int width;
	private String direction;
	private boolean moveWithoutInput = true;
	
	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public boolean isMoveWithoutInput() {
		return moveWithoutInput;
	}

	public void setMoveWithoutInput(boolean moveWithoutInput) {
		this.moveWithoutInput = moveWithoutInput;
	}

	/**
	 * This is the full constructor that allows you to explicitly assign all values.
	 * @param img the image of the object.
	 * @param spe the speed of the object.
	 * @param dir the direction.
	 * @param rowOfObject the lane/row.
	 * @param colOfObject the column.
	 * @param saf if the object is safe to land on or not.
	 */
	public GameObject (BufferedImage img, int spe, String dir, int rowOfObject, int colOfObject){
		this.image = img;
		this.speed = spe;
		this.row = rowOfObject; // Y value
		this.col = colOfObject; // X value
		this.safe = true; // true by default
		this.direction = dir;
	}
	
	/**
	 * 
	 */
	public void move(){
		if (direction.equalsIgnoreCase("East")){
			col += speed;
		} else if (direction.equalsIgnoreCase("West")){
			col -= speed;
		} else if (direction.equalsIgnoreCase("North")){
			row += speed;
		} else if (direction.equalsIgnoreCase("South")){
			row -= speed;
		}
	}
	
	/**
	 * 
	 */
	public void collision(){
		
	}
	
	
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
	public void setSpeed(int speed) {
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
	 * had to change this, it was row before
	 * @param newCol
	 */
	public void setCol(int newCol) {
		this.col = newCol;
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
	
}
