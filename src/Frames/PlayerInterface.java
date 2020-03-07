package Frames;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.ImageObserver;
import java.io.IOException;

import Inputs.KeyInputs;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

import Entities.Player;
import Image.GraphicsManager;

@SuppressWarnings("serial")
public class PlayerInterface extends JPanel implements ActionListener {

	private long LastRefresh = System.currentTimeMillis();
	private KeyInputs ki = new KeyInputs();
	private GraphicsManager gm= new GraphicsManager();
	private Player player ;
	Timer t = new Timer(5,this);
	/**
	 * Launch the application.
	 */
	public PlayerInterface() {
		t.start();
		
		this.addKeyListener(ki);
		 this.setFocusable(true);
		player = new Player(500,500);
//		setFocusable(true);

	}
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2= (Graphics2D)g;
		try {
			drawPlayer(g2,this);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		
		repaint();
	}
	
	
	
	

	/**
	 * Create the application.
	 */
	
	private void drawPlayer(Graphics g, ImageObserver observer) throws IOException {
		//draw one of three possible MegaMan poses according to situation
		Graphics2D g2d = (Graphics2D) g;
		if (this.ki.isRightIsPressed()) {
			this.gm.drawMegaMan(player, g2d, this);
			System.out.println("r");
		}
		if (this.ki.isLeftIsPressed()) {
		
			this.gm.drawMegaManL(player, g2d, this);
			System.out.println("l");
		}
		if (this.ki.isUpIsPressed()) {
			
			this.gm.drawMegaFireL(player, g2d, this);
			System.out.println("u");
		}
		if (this.ki.isDownIsPressed()) {
			
			this.gm.drawMegaFallL(player, g2d, this);
			System.out.println("d");
		}
		
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		repaint();
		ki.tick();
		
		
	}
	

}
