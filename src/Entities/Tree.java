package Entities;

import java.awt.Rectangle;

//Jose A Velazquez Torres 03/06/2020
public class Tree extends Rectangle {
	private static int width = 50;
	private static int height = 50;
	private int var;
	private int xPos;
	private int yPos;

	public Tree(int x, int y, int var) {
		super(x, y, width, height);
		this.var = var;
		this.xPos = x;
		this.yPos = y;
	}

	public Tree() {

	}

	public double getX() {
		return xPos;
	}

	public double getY() {
		return yPos;
	}

	public void setX(int x) {
		this.xPos = x;
	}

	public void setY(int y) {
		this.yPos = y;
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
