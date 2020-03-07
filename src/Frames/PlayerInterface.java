package Frames;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.ImageObserver;
import java.io.IOException;

import Inputs.KeyInputs;
import javax.swing.JFrame;
import javax.swing.JPanel;

import Entities.Player;
import Image.GraphicsManager;

@SuppressWarnings("serial")
public class PlayerInterface extends JPanel {

	private long LastRefresh = System.currentTimeMillis();
	private KeyInputs ki = new KeyInputs();
	private String direction = "right";
	private GraphicsManager gm= new GraphicsManager();
	private Player player = new Player(500,500);
	/**
	 * Launch the application.
	 */
	public void paintComponent(Graphics g)
	{
		run();
		try {
			drawPlayer(g,this);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		
		repaint();
	}
	
	void run()
	{
		while(true)
		{
			double timeSLU = System.currentTimeMillis() - LastRefresh;
						
			if(timeSLU >= 1000.0/60.0)
			{
				
				LastRefresh = System.currentTimeMillis();
				break;
			}
		}
	}
	
	

	public void paint(Graphics g) {	
		// Circular Surface
		Graphics2D g2 = (Graphics2D) g;

		super.paint(g);
		this.gm.drawMegaMan(player, g2, this);
		try {
			this.drawPlayer(g2, this);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * Create the application.
	 */
	public PlayerInterface() {
		this.addKeyListener(ki);
		
		setFocusable(true);
	}
	private void drawPlayer(Graphics g, ImageObserver observer) throws IOException {
		//draw one of three possible MegaMan poses according to situation
		Graphics2D g2d = (Graphics2D) g;
		if (ki.isRightIsPressed()|| this.direction.equals("right")) {
			this.direction = "right";
			this.gm.drawMegaMan(player, g2d, this);
		}
		if (ki.isLeftIsPressed() || this.direction.equals("left")) {
			this.direction = "left";
			this.gm.drawMegaManL(player, g2d, this);
			repaint();
		}
		if (ki.isDownIsPressed() || this.direction.equals("up")) {
			this.direction = "up";
			this.gm.drawMegaFireL(player, g2d, this);
		}
		if (ki.isUpIsPressed() || this.direction.equals("down")) {
			this.direction = "down";
			this.gm.drawMegaFallL(player, g2d, this);
		}
	}

}
