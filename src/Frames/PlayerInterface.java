package Frames;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.ImageObserver;
import java.io.IOException;
import java.util.ArrayList;

import Inputs.KeyInputs;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

import Entities.Building;
import Entities.Player;
import Image.GraphicsManager;

@SuppressWarnings("serial")
public class PlayerInterface extends JPanel implements ActionListener {
	//private ArrayList<Building> buildings = new ArrayList<Building>();
	private boolean walking=false;
	private int walkingTimer = 10;
	private int buildings=4;
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
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		this.setBackground(Color.GREEN);
		Graphics2D g2= (Graphics2D)g;
		
			
		this.gm.drawHouse(new Building(90,100,80,80), g2, this);
		this.gm.drawHouse(new Building(732,378,80,80), g2, this);
		this.gm.drawHouse(new Building(500,500,80,80), g2, this);
		this.gm.drawHouse(new Building(276,200,80,80), g2, this);
		try {
			drawPlayer(g2,this);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 g2.setColor(Color.black);
		 g2.setFont(new Font("TimesRoman", Font.PLAIN, 30)); 
		g2.drawString("Buildings left to explore: "+buildings, 500, 100);
		repaint();
	}
	
	
	
	

	/**
	 * Create the application.
	 */
	
	private void drawPlayer(Graphics g, ImageObserver observer) throws IOException {
		//draw one of three possible MegaMan poses according to situation
		Graphics2D g2d = (Graphics2D) g;
		if (this.direction==0) {
			
			if(walking) {
				this.gm.drawAvatarR2(player, g2d, observer);
				
			}
			else {
				this.gm.drawAvatarR1(player, g2d, observer);
			
			}
			
		}
		if (this.direction==1) {
		
			if(walking) {
				this.gm.drawAvatarL1(player, g2d, observer);
				
			}
			else {
				this.gm.drawAvatarL2(player, g2d, observer);
			
			}
			
		}
		if (this.direction==3) {
		
			if(walking) {
				this.gm.drawAvatarU1(player, g2d, observer);
				
			}
			else{
				this.gm.drawAvatarU2(player, g2d, observer);
			
			}

		}
		if (this.direction==2) {
			
			if(walking) {
				this.gm.drawAvatarD1(player, g2d, observer);
				
			}
			else {
				this.gm.drawAvatarD2(player, g2d, observer);
				
			}
		
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
			walkingTimer--;
			if(walkingTimer == 0) {
				walking=!walking;
				walkingTimer = 10;
			}
			
		}
		if (this.ki.isLeftIsPressed()) {
			this.direction=1;
			this.moveMegaManLeft();
			walkingTimer--;
			if(walkingTimer == 0) {
				walking=!walking;
				walkingTimer = 10;
			}
		}
		if (this.ki.isUpIsPressed()) {
			this.direction=3;
		
			this.moveMegaManUp();
			walkingTimer--;
			if(walkingTimer == 0) {
				walking=!walking;
				walkingTimer = 10;
			}
		}
		if (this.ki.isDownIsPressed()) {
			this.direction=2;
			this.moveMegaManDown();
			walkingTimer--;
			if(walkingTimer == 0) {
				walking=!walking;
				walkingTimer = 10;
			}
		}
		if(ki.nothingPressed()) {
			walking=false;
			walkingTimer = 10;
		}
	}
	
	
	

}
