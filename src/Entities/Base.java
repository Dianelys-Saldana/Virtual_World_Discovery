package Entities;

import java.awt.Rectangle;
//Carlos Rodriguez 3/6/2020
public abstract class Base extends Rectangle{

	private static final long serialVersionUID = 1L;
	
	private int speed;
	private int direction;
	
	public Base() {}
	
	public Base(int xPos, int yPos, int width, int height) {
		super(xPos, yPos,width, height);
	}
	
	public int getPixelsWide() {
		return (int) getWidth();
	}
	
	public int getPixelsTall() {
		return (int) getHeight();
	}
	
	public int getSpeed() {
		return speed;
	}
	
	public int getDirection() {
		return direction;
	}
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	
	public void setDirection(int direction) {
		this.direction = direction;
	}
}