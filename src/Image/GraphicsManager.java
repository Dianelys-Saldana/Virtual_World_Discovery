package Image;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

import Entities.Building;
import Entities.Player;
import Entities.Tree;

/**
 * Manages and draws game graphics and images.
 * Carlos Rodriguez 03/06/2020
 */
public class GraphicsManager {
	private BufferedImage avatarL1Img;
	private BufferedImage avatarL2Img;
	private BufferedImage avatarR1Img;
	private BufferedImage avatarR2Img;
	private BufferedImage avatarU1Img;
	private BufferedImage avatarU2Img;
	private BufferedImage avatarD1Img;
	private BufferedImage avatarD2Img;
	private BufferedImage avatarRestingImg;
	private BufferedImage tree3;
	private BufferedImage tree2;
	private BufferedImage tree1;
	private BufferedImage house;

	/**Carlos Rodriguez 03/06/2020
	 * Creates a new graphics manager and loads the game images.
	 */
	public GraphicsManager(){
		// load images
		try {
			this.house= ImageIO.read(getClass().getResource("../Image/casa.png"));
			this.avatarL1Img = ImageIO.read(getClass().getResource("../Image/Left1.png"));
			this.avatarL2Img = ImageIO.read(getClass().getResource("../Image/Left2.png"));
			this.avatarR1Img = ImageIO.read(getClass().getResource("../Image/Right1.png"));
			this.avatarR2Img = ImageIO.read(getClass().getResource("../Image/Right2.png"));
			this.avatarD1Img = ImageIO.read(getClass().getResource("../Image/Down1.png"));
			this.avatarD2Img = ImageIO.read(getClass().getResource("../Image/Down2.png"));
			this.avatarU1Img = ImageIO.read(getClass().getResource("../Image/Up1.png"));
			this.avatarU2Img = ImageIO.read(getClass().getResource("../Image/Up2.png"));
			this.avatarRestingImg = ImageIO.read(getClass().getResource("../Image/Resting.png"));
			this.tree1 = ImageIO.read(getClass().getResource("../Image/Tree1.png"));
			this.tree2 = ImageIO.read(getClass().getResource("../Image/Tree2.png"));
			this.tree3 = ImageIO.read(getClass().getResource("../Image/Tree3.png"));

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "The graphic files are either corrupt or missing.",
					"VoidSpace - Fatal Error", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
			System.exit(-1);
		}
	}

	/**Carlos Rodriguez 03/06/2020
	 * Draws a Avatar image to the specified graphics canvas.
	 * @param Avatar to draw
	 * @param g2d the graphics canvas
	 * @param observer object to be notified
	 */
	public void drawAvatarL1 (Player avatar, Graphics2D g2d, ImageObserver observer){
		g2d.drawImage(avatarL1Img, avatar.x, avatar.y, observer);	
	}

	public void drawAvatarL2 (Player avatar, Graphics2D g2d, ImageObserver observer){
		g2d.drawImage(avatarL2Img, avatar.x, avatar.y, observer);	
	}

	public void drawAvatarD1 (Player avatar, Graphics2D g2d, ImageObserver observer){
		g2d.drawImage(avatarD1Img, avatar.x, avatar.y, observer);	
	}

	public void drawAvatarD2 (Player avatar, Graphics2D g2d, ImageObserver observer){
		g2d.drawImage(avatarD2Img, avatar.x, avatar.y, observer);	
	}
	public void drawAvatarResting (Player player, Graphics2D g2d, ImageObserver observer){
		g2d.drawImage(avatarRestingImg, player.x, player.y, observer);	
	}
	public void drawAvatarR1 (Player player, Graphics2D g2d, ImageObserver observer){
		g2d.drawImage(avatarR1Img, player.x, player.y, observer);	
	}
	public void drawAvatarR2 (Player player, Graphics2D g2d, ImageObserver observer){
		g2d.drawImage(avatarR2Img, player.x, player.y, observer);	
	}
	public void drawAvatarU1 (Player player, Graphics2D g2d, ImageObserver observer){
		g2d.drawImage(avatarU1Img, player.x, player.y, observer);	
	}
	public void drawAvatarU2 (Player player, Graphics2D g2d, ImageObserver observer){
		g2d.drawImage(avatarU2Img, player.x, player.y, observer);	
	}
	public void drawHouse (Building build, Graphics2D g2d, ImageObserver observer){
		g2d.drawImage(house, (int) build.getX(), (int) build.getY(), observer);	
	}
	public void drawTree1 (Tree tree, Graphics2D g2d, ImageObserver observer){
		g2d.drawImage(tree1, (int) tree.getX(), (int) tree.getY(), observer);	
	}
	public void drawTree2 (Tree tree, Graphics2D g2d, ImageObserver observer){
		g2d.drawImage(tree2, (int) tree.getX(), (int) tree.getY(), observer);	
	}
	public void drawTree3 (Tree tree, Graphics2D g2d, ImageObserver observer){
		g2d.drawImage(tree3, (int) tree.getX(), (int) tree.getY(), observer);	
	}

}