package Entities;

import java.awt.Graphics;

public class Building1 extends Building{
	
	private Boolean buildingVisible = false;
	
	public Building1(int xPos, int yPos, int buildingHeight, int buildingWidth) {
		super(xPos, yPos, buildingHeight, buildingWidth);
		this.setxPos(100);
		this.setyPos(100);
	}
	
	@Override
	public void tick() {

	}
	
	@Override
    public void render(Graphics g) {
		if(buildingVisible == false) {
			//checkForPlayer(g, p);
		}
		
		if(buildingVisible == false) {
			//makeBuildingVisible(buildingVisible, count);
		}
	}
}
