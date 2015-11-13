import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.event.MouseInputListener;


 /*
  * Ways a frog can die
  * 
   	Being hit by or running into a road vehicle
	Jumping into the river's water
	Running into snakes, otters or into an alligator's jaws in the river
	Jumping into a home invaded by an alligator
	Staying on top of a diving turtle until it has completely submerged
	Riding a log, alligator, or turtle off the side of the screen
	Jumping into a home already occupied by a frog
	Jumping into the side of a home or the bush
	Running out of time
  */

public class Screen extends JPanel implements KeyListener, MouseInputListener{

	public static int screenWidth = 480;
	public static int screenHeight = 640; // extra 40 pixels is for added lives/timer panel at bottom
	
	public static int backgroundWidth = screenWidth;
	public static int backgroundHeight = screenHeight - 40; 
	
	private final Color backgroundColor = new Color(0,0,77); //dark blue of background
	
	public static Font sizedFont;

	private final String scoreString = "SCORE";
	private final String timerString = "TIME";
	
	private javax.swing.Timer timer;
	
	/* Game board is 12 col by 16 row, but last row, 16, is where frog starts */
	private final int tileSize = 40; //This is the size of 1 tile 40x40 pixels
	
	private int lives = 3; // initialize lives at 3
	private int livesX = 4;
	private int livesY = backgroundHeight + 2;
	
	private int level = 1; //may not need more than 1 level
	private int score = 0; // initialize score at 0
	/*
	 * Related to SCORE variable:
	 * For every vertical block the frog moves, you get 10 pts
	 * For every home that a frog lands in, you get 125
	 * For every fly caught in home, you get a bonus of 125
	 * For every female frog taken to home, you get a bonus of 75
	 */
	
	private int frogStartingX; // the starting X position of the frog.
	private int frogStartingY; // the starting Y position of the frog.
	
	
	/* Variables associated with time and timerBar */
	private final int frameRate = 30; //the rate at which the screen refreshes
	public final int gameTimeSeconds = 30;
	public final int gameTimeMilliseconds = gameTimeSeconds * frameRate; //30 second game time or 600 milliseconds
	public int gameTimeIterator = gameTimeMilliseconds; //the start that the gameTimerIterator counts from
	
	private final int timerBarHeight = 25; //final height of the timer meter bar on the bottom timer panel
	private int timerBarWidthStart = 200; //final width of the timer meter bar
	private int timerBarWidth = timerBarWidthStart; // current width of the timerBar
	public final int timerBarXStart = 175; //final timer bar X coordinate
	public int timerBarX = timerBarXStart; // current X coordinate of timerBar
	
	public double timerBarIncrementValue =  timerBarWidth / gameTimeSeconds;
	
	
	/* number of each object on screen */
	private int numOfSmallLogs = 4; //seperated by alternating 5 and 6 blocks going east
	private int numOfMedLogs = 4;//seperated by alternating between 3, then 2 blocks going east
	private int numOfLargeLogs = 4; //seperated by 4 blocks? going east
	
	private int numOfTrucks = 3; //seperated by 5 and 9 blocks going west
	private int numOfSedan = 3; //seperated between 4 and 8 blocks going west
	private int numOfBulldozer = 4; // seperated by 4 and 8 blocks going east
	private int numOfYellowRaceCar = 4; // seperated by 4 and 6 blocks going west
	private int numOfGreenRaceCar = 1; //only 1 on screen at a time at lvl 1 going east
	
	private int numOf2Turtles = 4; //seperated by 3 blocks? going west
	private int numOf3Turtles = 4; //seperated by 2 blocks? going west 
	
	/* game booleans */
	private boolean isPlaying = true; //is true while the user is playing
	private boolean hasStarted = false; //is false the game has not yet started
	private boolean hasLost = false; //is false while player still has lives.
	
	/* game arrays */
	private ArrayList<GameObject> movingGameObjects; //Turtles, logs, cars, frog, visible game Timer
	private ArrayList<GameObject> idleGameObjects; //Lives, Score... 
	
	public static Image background; //the Image of the background
	
	/* All the other sprite sheets followed by the array they are loaded into */
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
		idleGameObjects = new ArrayList<>();
		movingGameObjects = new ArrayList<>();
		loadFont();
		loadSprites();
		
		setupStartingBoard();
		
		timer = new javax.swing.Timer(frameRate, new TimerListener());
		timer.start();
		
		/* Add key and mouse listeners */
		this.addKeyListener(this);
		this.addMouseListener(this);
		
	}
	
	public void loadFont(){ //loads the custom Font
		try{
			File font_file = new File("assets/fonts/PressStart2P.ttf");
			Font font = Font.createFont(Font.TRUETYPE_FONT, font_file);
			sizedFont = font.deriveFont(25f);
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	public void loadSprites(){
		/* Load background sprite */
		try{
			background = new ImageIcon("assets/background.png").getImage(); //initialize the Image to the background		
		} catch (Exception e){
			e.printStackTrace();
		}
		
		/* Load all the other sprites in arrays of BufferedImages with the naming convention *Sprites   */
		try {
			alligatorSpriteSheet = ImageIO.read(new File("assets/alligator_sprites.png"));
			alligatorSprites = new SpriteSheetLoader(alligatorSpriteSheet, 
					alligatorSpriteSheet.getWidth(), alligatorSpriteSheet.getHeight(), 1, 2).getSprites();
			alligatorSpriteSheet = null;
			
			carSpriteSheet = ImageIO.read(new File("assets/car_sprites.png"));
			carSprites  = new SpriteSheetLoader(carSpriteSheet, 
					carSpriteSheet.getWidth(), carSpriteSheet.getHeight(), 1, 4).getSprites();
			carSpriteSheet = null;
			
			truckSpriteSheet = ImageIO.read(new File("assets/truck.png"));
			truckSprites = new SpriteSheetLoader(truckSpriteSheet, 
					truckSpriteSheet.getWidth(), truckSpriteSheet.getHeight(), 1, 1).getSprites();
			truckSpriteSheet = null;
			
			logSmallSpriteSheet = ImageIO.read(new File("assets/tree_1.png"));
			logSmallSprites = new SpriteSheetLoader(logSmallSpriteSheet, 
					logSmallSpriteSheet.getWidth(), logSmallSpriteSheet.getHeight(), 1, 1).getSprites();
			logSmallSpriteSheet = null;
			
			logMedSpriteSheet = ImageIO.read(new File("assets/tree_3.png"));
			logMedSprites = new SpriteSheetLoader(logMedSpriteSheet, 
					logMedSpriteSheet.getWidth(), logMedSpriteSheet.getHeight(), 1, 1).getSprites();
			logMedSpriteSheet = null;
			
			logLargeSpriteSheet = ImageIO.read(new File("assets/tree_2.png"));
			logLargeSprites = new SpriteSheetLoader(logLargeSpriteSheet, 
					logLargeSpriteSheet.getWidth(), logLargeSpriteSheet.getHeight(), 1, 1).getSprites();
			logLargeSpriteSheet = null;
			
			turtle2SpriteSheet = ImageIO.read(new File("assets/turtle_2_sprites.png"));
			turtle2Sprites = new SpriteSheetLoader(turtle2SpriteSheet, 
					turtle2SpriteSheet.getWidth(), turtle2SpriteSheet.getHeight(), 1, 4).getSprites();
			turtle2SpriteSheet = null;
			turtle2Sprites[turtle2Sprites.length - 1] = null; //deletes empty space from sprite sheet
			
			turtle3SpriteSheet = ImageIO.read(new File("assets/turtle_3_sprites.png"));
			turtle3Sprites = new SpriteSheetLoader(turtle3SpriteSheet, 
					turtle3SpriteSheet.getWidth(), turtle3SpriteSheet.getHeight(), 1, 4).getSprites();
			turtle3SpriteSheet = null;
			turtle3Sprites[turtle3Sprites.length - 1] = null; //deletes empty space from sprite sheet
			
			frogSpriteSheet = ImageIO.read(new File("assets/frog_sprites.png"));
			frogSprites = new SpriteSheetLoader(frogSpriteSheet, 
					frogSpriteSheet.getWidth(), frogSpriteSheet.getHeight(), 1, 11).getSprites();
			frogSpriteSheet = null;
			
			bonusSpriteSheet = ImageIO.read(new File("assets/bonus_sprites.png"));
			bonusSprites = new SpriteSheetLoader(bonusSpriteSheet, 
					bonusSpriteSheet.getWidth(), bonusSpriteSheet.getHeight(), 1, 4).getSprites();
			bonusSpriteSheet = null;
			bonusSprites[bonusSprites.length - 1] = null; //deletes empty space from sprite sheet
			
			livesSpriteSheet = ImageIO.read(new File("assets/lives.png"));
			livesSprites = new SpriteSheetLoader(livesSpriteSheet, 
					livesSpriteSheet.getWidth(), livesSpriteSheet.getHeight(), 1, 1).getSprites();
			livesSpriteSheet = null;
			
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	public void setupStartingBoard(){
		
		frogStartingX = calculateCol(6); //sets the X position of 
		frogStartingY = calculateRow(14); //sets the Y position of the frog
		
		boolean createBigGap = false; //changes the size of the gap between GameObjects if true
		int bigGapMultipler = 1;
		
		movingGameObjects.add(new Frog(frogSprites[0], 3, "East", frogStartingY, frogStartingX));
		
		/* Setup the lives objects */
		for (int i = 0; i < lives; i++){
			idleGameObjects.add(new Life(livesSprites[0], 0, "North", livesY, livesX + i*livesSprites[0].getWidth()));
		}
		
		
		/* Initialize  all the moving game objects */
		int spacing = 0; // the spacing between each object
		for (int j = 1; j <= 6; j++){ // there is never more than 6 of the same object on the screen at a time
			
			// determine spacing between small logs
			if (numOfSmallLogs == j){
				for (int i = 0; i < j; i++){
					spacing = logSmallSprites[0].getWidth() + logSmallSprites[0].getWidth() * i + calculateCol(5) * i;
					if (i == 3){
						spacing = logSmallSprites[0].getWidth() + logSmallSprites[0].getWidth() * i + calculateCol(6) * i;
					}
					movingGameObjects.add(new Log(logSmallSprites[0], 2, "East", calculateRow(6), 
							spacing));
				}
				spacing = 0;
			}
			
			// determine spacing between medium logs
			if (numOfMedLogs == j){
				for (int i = 0; i < j; i++){
					spacing = (calculateCol(-20)) + logMedSprites[0].getWidth() + logMedSprites[0].getWidth() * i + calculateCol(2) * i;
					if (i == 3){
						spacing = (calculateCol(-20)) + logMedSprites[0].getWidth() + logSmallSprites[0].getWidth() * i + calculateCol(3) * i;
					}
					movingGameObjects.add(new Log(logMedSprites[0], 3, "East", calculateRow(3), 
							spacing));
				}
				spacing = 0;
			}
		
			// determine spacing between large logs
			if (numOfLargeLogs == j){
				for (int i = 0; i < j; i++){
					spacing = (calculateCol(-40)) +logLargeSprites[0].getWidth() + logLargeSprites[0].getWidth() * i + calculateCol(5) * i;
					movingGameObjects.add(new Log(logLargeSprites[0], 4, "East", calculateRow(5), 
							spacing));
				}
				spacing = 0;
			}
			
			//determine spacing between 2turtles
			if (numOf2Turtles == j){
				for (int i = 0; i < j; i++){
					spacing = (calculateCol(10)) + turtle2Sprites[0].getWidth() + turtle2Sprites[0].getWidth() * i + calculateCol(5) * i;
					movingGameObjects.add(new Log(turtle2Sprites[0], 3, "West", calculateRow(4), 
							spacing));
				}
				spacing = 0;
			}
			if (numOf3Turtles == j){ // consistant gaps
				for (int i = 0; i < j; i++){
					spacing = turtle3Sprites[0].getWidth() + turtle3Sprites[0].getWidth() * i + calculateCol(2) * i;
					movingGameObjects.add(new Log(turtle3Sprites[0], 3, "West", calculateRow(7), 
							spacing));
				}
				spacing = 0;
			}
			if (numOfBulldozer == j){  // big small big
				for (int i = 0; i < j; i++){
					spacing = carSprites[1].getWidth() + carSprites[1].getWidth() * i + calculateCol(4) * i;
					if (i == 2){
						spacing = carSprites[1].getWidth() + carSprites[1].getWidth() * i + calculateCol(8) * i;
					}
					movingGameObjects.add(new Log(carSprites[1], 2, "East", calculateRow(12), 
							spacing));
				}
				spacing = 0;
			}
			if (numOfYellowRaceCar == j){ //2 small, 1 big
				for (int i = 0; i < j; i++){
					spacing = carSprites[0].getWidth() + carSprites[0].getWidth() * i + calculateCol(4) * i;
					if (i == 2){
						spacing = carSprites[0].getWidth() + carSprites[0].getWidth() * i + calculateCol(6) * i;
					}
					movingGameObjects.add(new Log(carSprites[0], 4, "West", calculateRow(13), 
							spacing));
				}
				spacing = 0;
			}
			
			if (numOfTrucks == j){
				for (int i = 0; i < j; i++){
					spacing = truckSprites[0].getWidth() + truckSprites[0].getWidth() * i + calculateCol(8) * i;
					if (i == 1){
						spacing = truckSprites[0].getWidth() + truckSprites[0].getWidth() * i + calculateCol(9) * i;
					}
					movingGameObjects.add(new Log(truckSprites[0], 3, "West", calculateRow(9), 
							spacing));
				}
				spacing = 0;
			}
			if (numOfSedan == j){
				for (int i = 0; i < j; i++){
					spacing = carSprites[3].getWidth() + carSprites[3].getWidth() * i + calculateCol(2) * i;
					if (i == 2){
						spacing = carSprites[3].getWidth() + carSprites[3].getWidth() * i + calculateCol(3) * i;
					}
					movingGameObjects.add(new Log(carSprites[3], 5, "West", calculateRow(11), 
							spacing));
				}
				spacing = 0;
			}
			if (numOfGreenRaceCar == j){
				for (int i = 0; i < j; i++){
					spacing = carSprites[2].getWidth() + carSprites[2].getWidth() * i + calculateCol(2) * i;
					if (i == 2){
						spacing = carSprites[2].getWidth() + carSprites[2].getWidth() * i + calculateCol(3) * i;
					}
					movingGameObjects.add(new Log(carSprites[2], 6, "East", calculateRow(10), 
							spacing));
				}
				spacing = 0;
			}
		}
		
		
		//TODO add Score 
		//TODO add logs
		//TODO add turtles
		//TODO add cars
		//TODO add Frog
		//TODO add 3 Lives
		//TODO add Timer
		//TODO set gameTimerIterator to 
	}
	
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		
		g.setColor(backgroundColor); //sets background color to dark blue
		g.drawImage(background, 0, 0, screenWidth, backgroundHeight, null); //draws background
		
		drawScoreAndTimerPanel(g); //draws the score text and the timer rectangle and timer text
				
		//draws the idleGameObjects of the player
		for (int i = 0; i < idleGameObjects.size(); i++){
			g.drawImage(idleGameObjects.get(i).getImage(), idleGameObjects.get(i).getCol(), 
					idleGameObjects.get(i).getRow(), idleGameObjects.get(i).getImage().getWidth(),
					idleGameObjects.get(i).getImage().getHeight(), null);
		}
		
		//draws the movingGameObjects
		for (int i = 0; i < movingGameObjects.size(); i++){
			g.drawImage(movingGameObjects.get(i).getImage(), movingGameObjects.get(i).getCol(), 
					movingGameObjects.get(i).getRow(), movingGameObjects.get(i).getImage().getWidth(),
					movingGameObjects.get(i).getImage().getHeight(), null);
		}
	}
	
	public void drawScoreAndTimerPanel(Graphics g){
		g.fillRect(0, backgroundHeight, backgroundWidth, 40); //creates panel at bottom for lives/timer
		g.setColor(Color.WHITE);
		g.setFont(sizedFont);
		
		g.drawString(scoreString, 2, 30); //Draw "SCORE" text in upper left
		g.drawString(Integer.toString(score), 2, 59); //Draw initial 0 score under "SCORE"
		
		g.drawString(timerString, screenWidth - 105, backgroundHeight + 32); //Draws "TIMER" text in bottom right
		
		if (gameTimeIterator % frameRate == 0){ // only increments every second
			timerBarX += timerBarIncrementValue; // changes the X value of the timerBar
			timerBarWidth -= timerBarIncrementValue; // changes the width value of the timerBar
		}			
			g.fillRect(timerBarX, backgroundHeight + 6, 
					timerBarWidth, timerBarHeight);
		
	}
	
	/* For testing sprites */
	public void drawSprites(Graphics g, BufferedImage[] sprites, int x, int y){
		for (int j = 0; j < sprites.length; j++){
			g.drawImage(sprites[j], x, y + j * sprites[j].getHeight() + 30, sprites[j].getWidth(), sprites[j].getHeight(), null);
		}
	}
	
	public int calculateCol(int val){
		return val * 40;
	}
	
	public int calculateRow(int val){
		return val * 40;
	}
	
	/**
	 * Retrieve the timer.
	 * @return the timer
	 */
	public javax.swing.Timer getTimer() {
		return timer;
	}

	/**
	 * Change the timer.
	 * @param timer
	 *            the timer to set
	 */
	public void setTimer(javax.swing.Timer timer) {
		this.timer = timer;
	}
	
	
	
	
	/* -------- Timer Class -------- */

	private class TimerListener implements ActionListener{

		//TODO remove old items, but not frogs that have made it "home"
		//TODO see if collisions were made from last movement
		//TODO move each object
		//TODO if frog has died, remove a life object if they exist, reset gameTimer
		
		@Override
		public void actionPerformed(ActionEvent e) { //this happens every time the timer goes off
			if (gameTimeIterator == 0){ // timer has run out
				//TODO reset only the active frog piece
				resetTimeBar();
				if (lives == 0){
					System.out.println("Timer is over. Lives left: " + Integer.toString(lives));
					System.out.println("Game over");
					timer.stop();
					
				} else { // still have lives available
					//TODO blow up frog
					lives--;
					for (int i = idleGameObjects.size() - 1; i >= 0; i++){
						if (idleGameObjects.get(i) instanceof Life){ //if the game object is a life object
							idleGameObjects.remove(i); //remove life object from array
							break; //only want to remove one.
						}
					}
					System.out.println("Timer is over. Lives left: " + Integer.toString(lives));
					//TODO display game over and freeze movement, prompt restart with Y or N
					System.out.println("Game timer reset to " + Integer.toString(gameTimeSeconds));
					gameTimeIterator = gameTimeMilliseconds;
				}
			} else { // there is still time on the clock
				gameTimeIterator--; //decrements the gameTimerIterator.
				for(GameObject obj : movingGameObjects){
					if (obj.isMoveWithoutInput()){
						obj.move(); //will move all objects that are supposed to move without input
					}
				}
			}
			repaint();
			
		}
		
		public void resetTimeBar(){
			timerBarX = timerBarXStart;
			timerBarWidth = timerBarWidthStart;
		}
		
	}
	
	
	
	/* ------- KeyListener Section --------- */
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


	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		System.out.println("X " + e.getX() + ", Y" + e.getY());
		
		
	}


	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}


