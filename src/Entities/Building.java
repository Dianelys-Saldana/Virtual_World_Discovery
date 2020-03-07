package Entities;

import java.awt.Graphics;
import java.awt.Rectangle;

//Angel Hernandez 3/6/2020

public class Building extends EntityBase {
	
	private Rectangle interactionRectangle = new Rectangle();

	public Building(int x, int y, int height, int width) {
		super(x, y, height, width);
		 bounds.x = 0;
	     bounds.y = 0;
	     bounds.width = 64;
	     bounds.height = 64;
	     interactionRectangle.width =  64;
	     interactionRectangle.height = 64;
	     interactionRectangle.x = 50; // location of the box
	     interactionRectangle.y = 50; // location of the box
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
}
