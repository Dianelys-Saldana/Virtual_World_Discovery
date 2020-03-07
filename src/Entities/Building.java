package Entities;

import java.awt.Graphics;
import java.awt.Rectangle;

//Angel Hernandez 3/6/2020

public class Building extends EntityBase {
	
	private Rectangle interactionRectangle = new Rectangle();
	private int xPos;
	private int yPos;
	private int buildingHeight;
	private int buildingWidth;
	
	public Building(int xPos, int yPos, int buildingHeight, int buildingWidth){
		super(xPos, yPos, buildingHeight, buildingWidth);
		this.xPos = xPos;
		this.yPos = yPos;
		this.buildingHeight = buildingHeight;
		this.buildingWidth = buildingWidth;
	}

	public Building(float x, float y, int height, int width) {
		super(x, y, height, width);
		 bounds.x = 0;
	     bounds.y = 0;
	     bounds.width = 64;
	     bounds.height = 64;
	     interactionRectangle.width =  64;
	     interactionRectangle.height = 64;
	     interactionRectangle.x = xPos + 50; // location of the box
	     interactionRectangle.y = yPos + 50; // location of the box
	}
	
	public void checkForPlayer(Graphics g, Player p) {
        Rectangle playerRectangle = p.getCollisionBounds(0,0);
        
        if(interactionRectangle.contains(playerRectangle)) {
			//display a x and a message
        }
	}
	
	public void makeBuildingVisible(boolean buildingVisible, int count) {
		if(buildingVisible == false && (count == 3 || count == 4)) {
			buildingVisible = true;
			count = 0;
		}
	}
	
	public int getxPos() {
		return xPos;
	}

	public void setxPos(int xPos) {
		this.xPos = xPos;
	}

	public int getyPos() {
		return yPos;
	}

	public void setyPos(int yPos) {
		this.yPos = yPos;
	}

	public int getBuildingHeight() {
		return buildingHeight;
	}

	public void setBuildingHeight(int buildingHeight) {
		this.buildingHeight = buildingHeight;
	}

	public int getBuildingWidth() {
		return buildingWidth;
	}

	public void setBuildingWidth(int buildingWidth) {
		this.buildingWidth = buildingWidth;
	}
}
