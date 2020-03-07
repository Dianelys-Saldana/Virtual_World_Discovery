package Entities;

import java.awt.Rectangle;

//Carlos Rodriguez 3/6/2020
public class Player extends Base {
	private static final long serialVersionUID = 1L;
	
	public static final int DEFAULT_SPEED = 5;
	public static final int Y_OFFSET = 5; // initial y distance of the ship from the bottom of the screen 
	
	public static final int WIDTH = 42;
	public static final int HEIGHT = 45;
	//public static final int speed = DEFAULT_SPEED;
	
	public Player(int xPos, int yPos){
		super(xPos, yPos, WIDTH, HEIGHT);
		this.setSpeed(DEFAULT_SPEED);
	}
	
	public int gtInitialYOffset() {
		return Y_OFFSET;
	}
	
	
	/**
	 * Returns the default ship speed.
	 * @return the default ship speed
	 */
	public int getDefaultSpeed(){
		return DEFAULT_SPEED;
	}

	public Rectangle getCollisionBounds(int i, int j) {

		return null;
	}
	
}