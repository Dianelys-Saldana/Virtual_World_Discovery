package Image;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;


import Entities.Player;

/**
 * Manages and draws game graphics and images.
 * Carlos Rodriguez 3/6/2020
 */
public class GraphicsManager {
	private BufferedImage megaManLImg;
	private BufferedImage megaFallLImg;
	private BufferedImage megaFireLImg;
	private BufferedImage megaManImg;
	

	/**
	 * Creates a new graphics manager and loads the game images.
	 */
	public GraphicsManager(){
		// load images
		try {
			this.megaManLImg = ImageIO.read(getClass().getResource("../Image/megaMan3left.png"));
			this.megaFallLImg = ImageIO.read(getClass().getResource("../Image/megaFallLeft.png"));
			this.megaFireLImg = ImageIO.read(getClass().getResource("../Image/megaFireLeft.png"));
			this.megaManImg = ImageIO.read(getClass().getResource("../Image/megaMan3.png"));
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "The graphic files are either corrupt or missing.",
					"VoidSpace - Fatal Error", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
			System.exit(-1);
		}
	}

	/**
	 * Draws a MegaMan image to the specified graphics canvas.
	 * @param MegaMan the ship to draw
	 * @param g2d the graphics canvas
	 * @param observer object to be notified
	 */
	public void drawMegaManL (Player megaMan, Graphics2D g2d, ImageObserver observer){
		g2d.drawImage(megaManLImg, megaMan.x, megaMan.y, observer);	
	}

	public void drawMegaFallL (Player megaMan, Graphics2D g2d, ImageObserver observer){
		g2d.drawImage(megaFallLImg, megaMan.x, megaMan.y, observer);	
	}

	public void drawMegaFireL (Player megaMan, Graphics2D g2d, ImageObserver observer){
		g2d.drawImage(megaFireLImg, megaMan.x, megaMan.y, observer);	
	}

	public void drawMegaMan (Player megaMan, Graphics2D g2d, ImageObserver observer){
		g2d.drawImage(megaManImg, megaMan.x, megaMan.y, observer);	
	}


	

}