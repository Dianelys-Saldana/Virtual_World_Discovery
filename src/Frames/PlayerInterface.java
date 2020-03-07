package Frames;

import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.ImageObserver;
import java.io.IOException;

import Inputs.KeyInputs;
import javax.swing.JFrame;

import Entities.Player;
import Image.GraphicsManager;

@SuppressWarnings("serial")
public class PlayerInterface extends JFrame {


	private KeyInputs ki = new KeyInputs();
	private String direction = "right";
	private GraphicsManager gm= new GraphicsManager();
	private Player player = new Player(500,500);
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PlayerInterface window = new PlayerInterface();
					window.setVisible(true);
					window.setResizable(false);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
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
		initialize();
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
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(170, 0, 1024, 722);
	}

}
