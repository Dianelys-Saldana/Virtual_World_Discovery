package Entities;

import java.awt.Rectangle;
import java.awt.event.KeyEvent;

import javax.net.ssl.KeyManager;

import Frames.PlayerInterface;
import Inputs.KeyInputs;
import Main.Launch;

// Carlos Rodriguez 03/06/2020 
public class Player extends Base {
	private static final long serialVersionUID = 1L;
	
	public static final int DEFAULT_SPEED = 2; // the speed of the avatar
	public static final int WIDTH = 42; // the width of the avatar 
	public static final int HEIGHT = 45;// the height of the avatar
	private int barWidth=60; // the standard width of the bar on the game 
	private int rightLimit=20; // the standard limit to the right side 
	private int maxX; // the initial position in x plus the width 
	private int maxY; // the initial position in y plus the height
	
	public Player(int xPos, int yPos,int maxX,int maxY){
		super(xPos, yPos, WIDTH, HEIGHT);
		this.setSpeed(DEFAULT_SPEED);	
		this.maxX=maxX;
		this.maxY=maxY;
	}
	
	/**Carlos Rodriguez 03/06/20
	 * Returns the default player speed.
	 * @return the default player speed
	 */
	public int getDefaultSpeed(){
		return DEFAULT_SPEED;
	}
	
	/**Created by Carlos Rodriguez 03/06/2020
	 * Move the Avatar up  
	 */
	public void moveAvatarUp(){
		if(getY() - getSpeed() >= 0){
			translate(0, -getSpeed());
		}
	}

	/**Created by Carlos Rodriguez 03/06/2020
	 * Move the Avatar down
	 */
	public void moveMegaManDown(){
		if(getY() + getSpeed() + height < maxY-this.barWidth ){
			translate(0, getSpeed());

		}
	}

	/**Created by Carlos Rodriguez 03/06/2020
	 * Move the Avatar to the left
	 */
	public void moveMegaManLeft(){
		if(getX() - getSpeed() >= 0){
			translate(-getSpeed(), 0);
		}
	}

	/**Created by Carlos Rodriguez 03/06/2020
	 * Move the Avatar to the right
	 */
	public void moveMegaManRight(){
		if(getX() + getSpeed() + width < maxX-this.rightLimit){
			translate(getSpeed(), 0);
		}
	}
	
}