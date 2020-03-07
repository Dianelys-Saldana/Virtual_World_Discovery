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
	int direction=0;
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
		player = new Player(0,620);
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
		if (this.direction==0) {
			this.gm.drawMegaMan(player, g2d, this);
			
		}
		if (this.direction==1) {
		
			this.gm.drawMegaManL(player, g2d, this);
			
		}
		if (this.direction==3) {
		
			this.gm.drawMegaFireL(player, g2d, this);

		}
		if (this.direction==2) {
			
			this.gm.drawMegaFallL(player, g2d, this);
		
		}
	} 

	@Override
	public void actionPerformed(ActionEvent arg0) {
		repaint();
		ki.tick();
		movePlayer();
		
	}
	public void moveMegaManUp(){
		if(player.getY() - player.getSpeed() >= 0){
			player.translate(0, -player.getSpeed());
		}
	}

	/**
	 * Move the megaMan down
	 * @param megaMan the megaMan
	 */
	public void moveMegaManDown(){
			if(player.getY() + player.getSpeed() + player.height < getHeight() ){
				player.translate(0, player.getSpeed());

		}
	}

	/**
	 * Move the megaMan left
	 * @param megaMan the megaMan
	 */
	public void moveMegaManLeft(){
		if(player.getX() - player.getSpeed() >= 0){
			player.translate(-player.getSpeed(), 0);
		}
	}
	public void moveMegaManRight(){
		if(player.getX() + player.getSpeed() + player.width < getWidth()){
			player.translate(player.getSpeed(), 0);
		}
	}
	private void movePlayer() {
		if (this.ki.isRightIsPressed()) {
			this.moveMegaManRight();
			this.direction=0;
			
		}
		if (this.ki.isLeftIsPressed()) {
			this.direction=1;
			this.moveMegaManLeft();
			
			System.out.println("l");
		}
		if (this.ki.isUpIsPressed()) {
			this.direction=3;
		
			this.moveMegaManUp();
			System.out.println("u");
		}
		if (this.ki.isDownIsPressed()) {
			this.direction=2;
			this.moveMegaManDown();
			System.out.println("d");
		}
	}
	
	
	

}
