package Entities;

import java.awt.Rectangle;

//Jose A Velazquez Torres 03/06/2020
public class Tree extends Rectangle {
	private static int width=50;
	private static int height=50 ;
	private int var;
	private int x;
	private int y;
	
	public Tree(int xPos, int yPos,int var){
		super(xPos, yPos,width,height); 
		this.var=var;
		x=xPos;
		y=yPos;
	}
	

	public double getX() {
		return x;
	}


	public double getY() {
		return y;
	}


	public void setX(int x) {
		this.x = x;
	}


	public void setY(int y) {
		this.y = y;
	}


	public double getWidth() {
		return width;
	}


	public double getHeight() {
		return height;
	}



	public int getVar() {
		return var;
	}


	public void setVar(int var) {
		this.var = var;
	}
}
