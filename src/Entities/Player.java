package Entities;

import java.awt.Rectangle;
import java.awt.event.KeyEvent;

import Frames.PlayerInterface;
import Inputs.KeyInputs;
import Main.Launch;

// Carlos Rodriguez 03/06/2020 
public class Player extends Base {
	private static final long serialVersionUID = 1L;
	
	public static final int DEFAULT_SPEED = 2;
	public int direction=0;
	public static final int WIDTH = 42;
	public static final int HEIGHT = 45;
	
	public Player(int xPos, int yPos){
		super(xPos, yPos, WIDTH, HEIGHT);
		this.setSpeed(DEFAULT_SPEED);	
	}
	
	/**Carlos Rodriguez 03/06/20
	 * Returns the default player speed.
	 * @return the default player speed
	 */
	public int getDefaultSpeed(){
		return DEFAULT_SPEED;
	}
	
}