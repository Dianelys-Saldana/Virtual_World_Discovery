package Frames;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.ImageObserver;
import java.io.IOException;
import java.util.ArrayList;

import Inputs.KeyInputs;
import Main.Questions;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

import Entities.Building;
import Entities.Player;
import Entities.Tree;
import Image.GraphicsManager;
import javax.swing.JTextPane;
import javax.swing.JList;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

@SuppressWarnings("serial")

//Created by Carlos Rodriguez 03/06/2020
public class PlayerInterface extends JPanel implements ActionListener  {
	private ArrayList<Building> buildings = new ArrayList<Building>();
	private boolean walking=false;
	private Questions questions = new Questions(this);
	private int walkingTimer = 10;
	private int numBuildings = 4;
	private KeyInputs ki = new KeyInputs();
	int direction = 0;
	private GraphicsManager gm = new GraphicsManager();
	private Player player ;
	private Color bColor = new Color (116, 174, 109);
	private boolean characterSelected = false;
	private boolean avatar1 = false;
	private boolean avatar2 = false;
	Timer t = new Timer(5,this);

	Building b1 = new Building(90,100,80,80);
	Building b2 = new Building(732,378,80,80);
	Building b3 = new Building(500,500,80,80);
	Building b4 = new Building(276,200,80,80);

	public PlayerInterface() {
		t.start();
		initialize();
		
		this.addKeyListener(ki);
		this.setFocusable(true);
		setLayout(null);

		player = new Player(0,620);
		//		setFocusable(true);
		buildings.add(b1);
		buildings.add(b2);
		buildings.add(b3);
		buildings.add(b4);
	}

	/**Created by Carlos Rodriguez 03/06/2020
	 * Draw all the components on the JPanel.
	 */
	public void paintComponent(Graphics g){
		super.paintComponent(g);  
		this.setBackground(bColor); 
		Graphics2D g2= (Graphics2D)g;
		for(int i=0;i<buildings.size();i++) {
			if(buildings.get(i).getVisible()==2) {

				this.gm.drawHouse(buildings.get(i), g2, this);
			}
			else if(buildings.get(i).getVisible()==1) {
				g2.drawRect((int)buildings.get(i).getX(), (int)buildings.get(i).getY(), 
						(int)buildings.get(i).getWidth(), (int)buildings.get(i).getHeight());
			}
		}

		try {
			if(avatar1) {
				drawPlayer(g2,this);
			}
			if(avatar2) {
				drawPlayer2(g2,this);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		g2.setColor(Color.black);
		g2.setFont(new Font("Arial", Font.BOLD, 30)); 
		g2.drawString("Buildings left to explore: "+numBuildings, 620, 30);
		repaint();
	}
	
	/**Created by Carlos Rodriguez 03/06/2020
	 **Draw the Avatar.
	 */
	private void drawPlayer(Graphics g, ImageObserver observer) throws IOException {
		//draw one of three possible poses according to situation
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
	
	/**Created by Carlos Rodriguez 03/06/2020
	 **Draw the Avatar.
	 */
	private void drawPlayer2(Graphics g, ImageObserver observer) throws IOException {
		//draw one of three possible poses according to situation
		Graphics2D g2d = (Graphics2D) g;
		if (this.direction==0) {
			if(walking) {
				this.gm.drawAvatar2R1(player, g2d, observer);
			}
			else {
				this.gm.drawAvatar2RR(player, g2d, observer);
			}
		}
		if (this.direction==1) {

			if(walking) {
				this.gm.drawAvatar2L1(player, g2d, observer);
			}
			else {
				this.gm.drawAvatar2LR(player, g2d, observer);
			}
		}
		if (this.direction==3) {

			if(walking) {
				this.gm.drawAvatar2U1(player, g2d, observer);
			}
			else{
				this.gm.drawAvatar2UR(player, g2d, observer);
			}
		}
		if (this.direction==2) {

			if(walking) {
				this.gm.drawAvatar2D1(player, g2d, observer);
			}
			else {
				this.gm.drawAvatar2DR(player, g2d, observer);
			}
		}
	}
	
	private void initialize() {
		
		this.setFocusable(true);
		setLayout(null);
		ImageIcon Character1img = new ImageIcon(getClass().getResource("../Image/Resting.png"));
		ImageIcon Character2img = new ImageIcon(getClass().getResource("../Image/angel resting.png"));
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 450, 54);
		add(menuBar);

		JMenuItem character1 = new JMenuItem("Link",Character1img);
		menuBar.add(character1);
		character1.addMouseListener(new MouseAdapter() {
			@Override
			 public void mouseClicked(MouseEvent arg0) {
				avatar1 = true;
				characterSelected = true;
			}
		});
		
		JMenuItem character2 = new JMenuItem("Israfil",Character2img);
		menuBar.add(character2);
		character2.addMouseListener(new MouseAdapter() {
			@Override
			 public void mouseClicked(MouseEvent arg0) {
				avatar2 = true;
				characterSelected = true;
			}
		});
	}

	/** Jose A Velazquez Torres 03/07/2020
	 ** In this method we check if the avatar makes contact with a building
	here we manage the state of visibility of the building 
	and the count of how many are left for discover
	*/
	public void checkBuildingColition() {
		for(int i=0; i<this.buildings.size(); i++){
			Building build = buildings.get(i);
			if(player.intersects(build) && build.getVisible()!=2){
				if(this.direction==0)this.moveMegaManLeft();
				if(this.direction==1)this.moveMegaManRight();
				if(this.direction==2)this.moveAvatarUp();
				if(this.direction==3)this.moveMegaManDown();
				if(build.getVisible()==0)numBuildings--;
				build.setVisible(1);
				if(questions.visible()){  
					build.setVisible(2);
				}  
				ki.reset();
			}
			else if(player.intersects(build) && build.getVisible()==2) {
				if(this.direction==0)this.moveMegaManLeft();
				if(this.direction==1)this.moveMegaManRight();
				if(this.direction==2)this.moveAvatarUp();
				if(this.direction==3)this.moveMegaManDown();
				ki.reset();
			}
		}
	}
	/**Created by Carlos Rodriguez 03/06/2020
	 **Make actions in specific times.
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		repaint();
		ki.tick();
		movePlayer();
		checkBuildingColition();
	}
	/**Created by Carlos Rodriguez 03/06/2020
	 * Move the Avatar up  
	 */
	public void moveAvatarUp(){
		if(player.getY() - player.getSpeed() >= 0){
			player.translate(0, -player.getSpeed());
		}
	}

	/**Created by Carlos Rodriguez 03/06/2020
	 * Move the Avatar down
	 */
	public void moveMegaManDown(){
		if(player.getY() + player.getSpeed() + player.height < getHeight() ){
			player.translate(0, player.getSpeed());

		}
	}

	/**Created by Carlos Rodriguez 03/06/2020
	 * Move the Avatar to the left
	 */
	public void moveMegaManLeft(){
		if(player.getX() - player.getSpeed() >= 0){
			player.translate(-player.getSpeed(), 0);
		}
	}
	
	/**Created by Carlos Rodriguez 03/06/2020
	 * Move the Avatar to the right
	 */
	public void moveMegaManRight(){
		if(player.getX() + player.getSpeed() + player.width < getWidth()){
			player.translate(player.getSpeed(), 0);
		}
	}
	
	/**Created by Carlos Rodriguez 03/06/2020
	 * Make the Avatar move pressing arrow
	 */
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
			this.direction = 1;
			this.moveMegaManLeft();
			walkingTimer--;
			if(walkingTimer == 0) {
				walking=!walking;
				walkingTimer = 10;
			}
		}
		
		if (this.ki.isUpIsPressed()) {
			this.direction = 3;
			this.moveAvatarUp();
			walkingTimer--;
			if(walkingTimer == 0) {
				walking=!walking;
				walkingTimer = 10;
			}
		}
		
		if (this.ki.isDownIsPressed()) {
			this.direction = 2;
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
