package Entities;

import java.awt.Rectangle;

//Jose A Velazquez Torres 03/06/2020
public class Tree extends Base {
	private static final long serialVersionUID = 2L;
	
	public static final int Y_OFFSET = 5; 
	public static final int WIDTH = 42;
	public static final int HEIGHT = 45;
	
	
	public Tree(int xPos, int yPos){
		super(xPos, yPos, WIDTH, HEIGHT); 
	}
	
	public int gtInitialYOffset() {
		return Y_OFFSET;
	}

	public Rectangle getCollisionBounds(int i, int j) {

		return null;
	}
}
