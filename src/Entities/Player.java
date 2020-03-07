package Entities;

import java.awt.Rectangle;
import java.awt.event.KeyEvent;

import Frames.PlayerInterface;
import Inputs.KeyInputs;
import Main.Launch;

//Carlos Rodriguez 3/6/2020
public class Player extends Base {
	private static final long serialVersionUID = 1L;
	
	public static final int DEFAULT_SPEED = 2;
	public int direction=0;
	public static final int WIDTH = 42;
	public static final int HEIGHT = 45;
	
	//public static final int speed = DEFAULT_SPEED;
	
	public Player(int xPos, int yPos){
		super(xPos, yPos, WIDTH, HEIGHT);
		this.setSpeed(DEFAULT_SPEED);

		
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