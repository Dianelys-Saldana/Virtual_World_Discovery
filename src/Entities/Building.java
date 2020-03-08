package Entities;

import java.awt.Graphics;
import java.awt.Rectangle;

//Angel Hernandez 3/6/2020

public class Building extends Rectangle {
	private int x;
	private int y;
	private int width;
	private int height;
	private boolean colition;
	
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
	}
	
	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public void makeBuildingVisible(boolean buildingVisible, int count) {
		if(buildingVisible == false && (count == 3 || count == 4)) {
			buildingVisible = true;
			count = 0;
		}
	}
	
	
}
