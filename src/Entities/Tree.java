package Entities;

import java.awt.Rectangle;

//Jose A Velazquez Torres 03/06/2020
public class Tree extends Rectangle {
	private static int width=50;
	private static int height=50 ;
	private int var;
	
	
	public Tree(int xPos, int yPos,int var){
		super(xPos, yPos,width,height); 
		this.var=var;
	}
	

	public double getWidth() {
		return width;
	}


	public double getHeight() {
		return height;
	}


	public Rectangle getCollisionBounds(int i, int j) {

		return null;
	}


	public int getVar() {
		return var;
	}


	public void setVar(int var) {
		this.var = var;
	}
}
