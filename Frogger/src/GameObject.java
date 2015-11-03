import java.awt.Color;
import java.awt.Image;

/**
 * This is the abstract GameObject that is the parent for most objects in the game.
 * @author th
 *
 */

public abstract class GameObject {
	public double speed; //the speed of the object
	public Color color; //the color of the object
	public int lane; // the lane or row the object is in.
	public boolean safe; //true by default
	public TwoDPoint location;
	public Image image; //the shape/image of the image
	
	/**
	 * This is the full constructor that allows you to explicitly assign all values.
	 * @param spe the speed of the object.
	 * @param dir the direction.
	 * @param col the color.
	 * @param lan the lane.
	 * @param saf if the object is safe to land on or not.
	 * @param loc the location of the object.
	 */
	public GameObject (Image img, double spe, String dir, Color col, int lan, boolean saf, TwoDPoint loc){
		this.image = img;
		this.speed = spe;
		this.color = col;
		this.lane = lan;
		this.safe = saf;
		this.location = loc;
	}
	
	/**
	 * @param sha
	 * @param spe
	 * @param dir
	 * @param lan
	 * @param loc
	 */
	public GameObject (Shape sha, double spe, String dir, int lan, TwoDPoint loc) {
		this.shape = sha;
		this.speed = spe;
		this.lane = lan;
		this.safe = true;
		this.location = loc;
	}
	
	/**
	 * 
	 */
	public abstract void move();
	
	/**
	 * 
	 */
	public abstract void collision();

	/**
	 * @return
	 */
	public Image getImage() {
		return image;
	}

	/**
	 * @param shape
	 */
	public void setImage(Image img) {
		this.image = img;
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
	public Color getColor() {
		return color;
	}

	/**
	 * @param color
	 */
	public void setColor(Color color) {
		this.color = color;
	}

	/**
	 * @return
	 */
	public int getLane() {
		return lane;
	}

	/**
	 * @param lane
	 */
	public void setLane(int lane) {
		this.lane = lane;
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

	/**
	 * @return
	 */
	public TwoDPoint getLocation() {
		return location;
	}

	/**
	 * @param location
	 */
	public void setLocation(TwoDPoint location) {
		this.location = location;
	}
	
	
}
