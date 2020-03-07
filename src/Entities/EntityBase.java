package Entities;

import java.awt.*;

//Angel Hernandez 3/6/2020


public class EntityBase {
	private int x;
	private int y;
	private int width;
	private int height;
	protected Rectangle bounds;
    
    public EntityBase(int x, int y, int height, int width){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        bounds = new Rectangle(0, 0, width,height);
    }
    
    public void tick() {
	}

    public void render(Graphics g) {
	}
    
    public boolean checkEntityCollisions(float xOffset, float yOffset){
    	if(this.getCollisionBounds(0f, 0f).intersects(getCollisionBounds(xOffset, yOffset))) {
    		return true;
        }
        return false;
    }

    public Rectangle getCollisionBounds(float xOffset, float yOffset){
        return new Rectangle((int) (x + bounds.x + xOffset), (int) (y + bounds.y + yOffset), bounds.width, bounds.height);
    }
    
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
