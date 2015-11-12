import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;


public class Screen extends JPanel implements KeyListener {

	public static int screenWidth = 480;
	public static int screenHeight = 600;
	
	private int lives = 3; // initialize lives at 3
	private int score = 0; // initialize score at 0
	private int level = 1; //may not need more than 1 level
	
	private boolean isPlaying = true; //is true while the user is playing
	private boolean hasStarted = false; //is false the game has not yet started
	private boolean hasLost = false; //is false while player still has lives.
	
	private ArrayList<GameObject> movingGameObjects;
	
	/* Images */
	public static Image background; //the Image of the background
	public static Image frogLife;
	
	BufferedImage alligatorSpriteSheet; //width 204, height 41
	BufferedImage[] alligatorSprites; //an array to load all sprites from sprite sheet
	
	BufferedImage carSpriteSheet;
	BufferedImage[] carSprites;
	
	BufferedImage truckSpriteSheet;
	BufferedImage[] truckSprites;
	
	BufferedImage logSmallSpriteSheet;
	BufferedImage[] logSmallSprites;
	
	BufferedImage logMedSpriteSheet;
	BufferedImage[] logMedSprites;
	
	BufferedImage logLargeSpriteSheet;
	BufferedImage[] logLargeSprites;
	
	BufferedImage turtle2SpriteSheet;
	BufferedImage[] turtle2Sprites;
	
	BufferedImage turtle3SpriteSheet;
	BufferedImage[] turtle3Sprites;;
	
	BufferedImage frogSpriteSheet;
	BufferedImage[] frogSprites;
	
	BufferedImage bonusSpriteSheet;
	BufferedImage[] bonusSprites;
	
	BufferedImage livesSpriteSheet;
	BufferedImage[] livesSprites;
	
	
	
	public Screen(){
		setPreferredSize(new Dimension(screenWidth, screenHeight));
		movingGameObjects = new ArrayList<>();
		
		try {
			alligatorSpriteSheet = ImageIO.read(new File("assets/alligator_sprites.png"));
			alligatorSprites = new SpriteSheetLoader(alligatorSpriteSheet, 
					alligatorSpriteSheet.getWidth(), alligatorSpriteSheet.getHeight(), 1, 2).getSprites();
			
			carSpriteSheet = ImageIO.read(new File("assets/car_sprites.png"));
			carSprites  = new SpriteSheetLoader(carSpriteSheet, 
					carSpriteSheet.getWidth(), carSpriteSheet.getHeight(), 1, 4).getSprites();
			
			truckSpriteSheet = ImageIO.read(new File("assets/truck.png"));
			truckSprites = new SpriteSheetLoader(truckSpriteSheet, 
					truckSpriteSheet.getWidth(), truckSpriteSheet.getHeight(), 1, 1).getSprites();
			
			logSmallSpriteSheet = ImageIO.read(new File("assets/tree_1.png"));
			logSmallSprites = new SpriteSheetLoader(logSmallSpriteSheet, 
					logSmallSpriteSheet.getWidth(), logSmallSpriteSheet.getHeight(), 1, 1).getSprites();
			
			logMedSpriteSheet = ImageIO.read(new File("assets/tree_3.png"));
			logMedSprites = new SpriteSheetLoader(logMedSpriteSheet, 
					logMedSpriteSheet.getWidth(), logMedSpriteSheet.getHeight(), 1, 1).getSprites();
			
			logLargeSpriteSheet = ImageIO.read(new File("assets/tree_2.png"));
			logLargeSprites = new SpriteSheetLoader(logLargeSpriteSheet, 
					logLargeSpriteSheet.getWidth(), logLargeSpriteSheet.getHeight(), 1, 1).getSprites();
			
			turtle2SpriteSheet = ImageIO.read(new File("assets/turtle_2_sprites.png"));
			turtle2Sprites = new SpriteSheetLoader(turtle2SpriteSheet, 
					turtle2SpriteSheet.getWidth(), turtle2SpriteSheet.getHeight(), 1, 4).getSprites();
			
			turtle3SpriteSheet = ImageIO.read(new File("assets/turtle_2_sprites.png"));
			turtle3Sprites = new SpriteSheetLoader(turtle3SpriteSheet, 
					turtle3SpriteSheet.getWidth(), turtle3SpriteSheet.getHeight(), 1, 4).getSprites();
			
			frogSpriteSheet = ImageIO.read(new File("assets/frog_sprites.png"));
			frogSprites = new SpriteSheetLoader(frogSpriteSheet, 
					frogSpriteSheet.getWidth(), logSmallSpriteSheet.getHeight(), 1, 11).getSprites();
			
			bonusSpriteSheet = ImageIO.read(new File("assets/bonus_sprites.png"));
			bonusSprites = new SpriteSheetLoader(bonusSpriteSheet, 
					bonusSpriteSheet.getWidth(), bonusSpriteSheet.getHeight(), 1, 4).getSprites();
			
			livesSpriteSheet = ImageIO.read(new File("assets/lives.png"));
			livesSprites = new SpriteSheetLoader(livesSpriteSheet, 
					livesSpriteSheet.getWidth(), livesSpriteSheet.getHeight(), 1, 1).getSprites();
			
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		try{
			background = new ImageIcon("assets/background.png").getImage(); //initialize the Image to the background
			frogLife = new ImageIcon("assets/lives.png").getImage();
			
			
		} catch (Exception e){
			e.printStackTrace();
		}
		
		
		this.addKeyListener(this);
		
		
		
	}
	
	
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		
		g.drawImage(background, 0, 0, null);
		
		drawSprites(g, alligatorSprites, 10, 10);
		drawSprites(g, carSprites, 150, 10);
		drawSprites(g, logSmallSprites, 200, 10);
		drawSprites(g, logMedSprites, 200, 50);
		drawSprites(g, logLargeSprites, 200, 100);
		drawSprites(g, turtle2Sprites, 10, 200);
		drawSprites(g, turtle3Sprites, 10, 350);
		drawSprites(g, frogSprites, 200, 125);
		drawSprites(g, bonusSprites, 250, 125);
		drawSprites(g, truckSprites, 250, 350);
		drawSprites(g, livesSprites, 250, 400);
		
		
		
		/*for(int i = 0; i < movingGameObjects.size(); i++){
			if (movingGameObjects.get(i) instanceof ){
				
			}
		}*/
		
	}
	
	public void drawSprites(Graphics g, BufferedImage[] sprites, int x, int y){
		for (int j = 0; j < sprites.length; j++){
			g.drawImage(sprites[j], x, y + j * sprites[j].getHeight() + 30, sprites[j].getWidth(), sprites[j].getHeight(), null);
		}
	}
	
	
	/* KeyListener Section */
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}


