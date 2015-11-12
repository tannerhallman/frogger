import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SpriteSheetLoader {
	
	private BufferedImage[] sprites;
	private BufferedImage spriteToUnpack;
	
	private int width;
	private int height;
	private int rows;
	private int columns;
	
	
	public SpriteSheetLoader(BufferedImage img, int w, int h, int r, int c) throws IOException {
		this.width = img.getWidth() / c; 
		this.height = img.getHeight();
		this.rows = r;
		this.columns = c;
		
		spriteToUnpack = img;
		
		sprites = new BufferedImage[rows*columns];
		
		
		for(int i = 0; i < rows; i++){
			for(int j = 0; j < columns; j++){
				sprites[(i*columns) + j] = spriteToUnpack.getSubimage(j*width, i*height, width, height);
			}
		}
		/*Walkthrough iteration
		 * step 1 (for alligator sprite sheet)
		 * i = 0, j = 0, rows = 1, columns = 2, width = 102, height = 41
		 * 		sprites[(0*2)+0= 0] = subImage(0*102 = 0, 0*
		 */
	}
	
	public BufferedImage[] getSprites(){
		return sprites;
	}
	
}
