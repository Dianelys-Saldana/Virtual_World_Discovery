package Entities;

import java.awt.Graphics;
import java.awt.Rectangle;

public class Building extends EntityBase {
	
	private Rectangle ir = new Rectangle();
	public Boolean buildingVisible = false;
	
	public Building(float x, float y) {
		super(y, y, height, height);
		 bounds.x = 0;
	     bounds.y = 0;
	     bounds.width = 64;
	     bounds.height = 64;
	     ir.width =  64;
	     ir.height = 64;
	     
	     int irx = 1147;
	     int iry = 1258;
	     ir.y = iry;
	     ir.x = irx;
	}
	
	@Override
	public void tick() {

	}
	
	@Override
    public void render(Graphics g) {
		
	}
	
	private void checkForPlayer(Graphics g, Player p) {
        Rectangle pr = p.getCollisionBounds(0,0);
        
        if(ir.contains(pr)) {
			
        }
	}
}
