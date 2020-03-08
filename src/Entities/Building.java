package Entities;

import java.awt.Rectangle;

/**Jose A Velazquez Torres 03/07/2020
** Here we manage the building entities
*/
public class Building extends Rectangle {
	private int x;
	private int y;
	private int width;
	private int height;
	private boolean colition;
	private int visible;
	
	public boolean getColition() {
		return colition;
	}

	public void setColition(boolean colition) {
		this.colition = colition;
	}

	public Building(int x, int y, int height, int width) {
		super(x,y,height,width);
		 this.x = x;
	     this.y = y;
	     this.width = width;
	     this.height = height;	     
	     this.colition =false ;
	     this.visible=0;
	}
	
	public double getX() {
		return x;
	}

	public double getWidth() {
		return width;
	}

	public double getHeight() {
		return height;
	}

	public int getVisible() {
		return visible;
	}

	public void setVisible(int visible) {
		this.visible = visible;
	}

	public double getY() {
		return y;
	}

}
