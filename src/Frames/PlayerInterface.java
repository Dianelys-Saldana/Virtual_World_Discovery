package Frames;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import Inputs.KeyInputs;
import Main.Questions;
import Text.QuestionsReader;
import Text.Reader;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
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
import javax.swing.JMenu;
import javax.swing.JButton;

@SuppressWarnings("serial")

//Created by Carlos Rodriguez 03/06/2020
public class PlayerInterface extends JPanel implements ActionListener  {
	private Reader br= new Reader();
	private ArrayList<Building> buildings = new ArrayList<>() ;
	private ArrayList<Tree> trees = new ArrayList<>() ;
	private boolean walking=false;
	private Questions questions = new Questions(this,null);
	private int walkingTimer = 10;
	private int numBuildings=0 ;
	private int sel;
	private KeyInputs ki = new KeyInputs();
	int direction = 0;
	private GraphicsManager gm = new GraphicsManager();
	private Player player ;
	private QuestionsReader qr= new QuestionsReader();
	private Color bColor = new Color (116, 174, 109);
	private String worldSelected;
	private int sele=0;
	private boolean avatar1 = false;
	private boolean avatar2 = false;
	private int worldSize ;
	Timer t = new Timer(5,this);
	FrameManager f;

	public PlayerInterface(FrameManager f) {
		t.start();
		//worldSize= new File(getClass().getResource("../World").getFile()).listFiles().length;
		worldSize= new File("src/World").listFiles().length;
		this.f = f;
		initialize();

		this.addKeyListener(ki);
		this.setFocusable(true);
		setLayout(null);

		JButton back = new JButton("Home");
		back.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				f.menu();
			}
		});
		back.setBounds(847, 72, 141, 35);
		add(back);

		//		worldScan();




		player = new Player(0,620);

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
				for(int j=0;j<buildings.get(i).getLines().size();j++) {
					g2.drawLine((int)buildings.get(i).getLines().get(j).getX1(),(int) buildings.get(i).getLines().get(j).getY1(),
							(int)buildings.get(i).getLines().get(j).getX2(),(int) buildings.get(i).getLines().get(j).getY2());
				}
			}
		}
		for(int i=0;i<trees.size();i++) {
			int var=trees.get(i).getVar();
			if(var==1)this.gm.drawTree1(trees.get(i), g2, this);
			else if(var==2)this.gm.drawTree2(trees.get(i), g2, this);
			else if(var==3)this.gm.drawTree3(trees.get(i), g2, this);
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
		if(sele==2)g2.drawString("Buildings left to explore: "+numBuildings, 620, 30);
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

	/**Created by Angel Hernandez 03/16/2020
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
	private void worldScan() {
		if(sele==0) {
			ArrayList<String> arr= new ArrayList<>();
			for(int i=1;i<this.worldSize+1;i++) {
				arr.add("world"+i);
			}
			sel=questions.arraySelection(arr.toArray(), "Que mundo desea utilizar");
			if(sel==JOptionPane.CLOSED_OPTION) {
				f.menu();
				return;
			}
			sel++;
			sele=1;
		}
		if(sele==1) {
			scan();
			sele=2;
		}
		else return;
	}
	private void scan() {

		try {
			br.scan("world"+sel);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		trees=br.getTrees();
		for(int i=0;i<br.getBuildings().size();i++) {
			Building build =  new Building(br.getBuildings().get(i).getPoint());

			build.setAnswer(br.getBuildings().get(i).getAnswer());
			build.setQuestions(br.getBuildings().get(i).getQuestions());
			build.createLine();
			buildings.add(build);
		}
		try {
			qr.worldScan("world"+sel, buildings);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		numBuildings = buildings.size();

	}

	//Created by Angel Hernandez 03/16/2020
	private void initialize() {

		this.setFocusable(true);
		setLayout(null);
		JLabel lblNewLabel = new JLabel("Select a character");
		lblNewLabel.setForeground(Color.BLACK);
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 30));
		lblNewLabel.setBounds(370, 0, 272, 25);
		add(lblNewLabel);
		ImageIcon Character1img = new ImageIcon(getClass().getResource("../Image/Resting.png"));
		ImageIcon Character2img = new ImageIcon(getClass().getResource("../Image/avatar2D1.png"));
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(370, 30, 260, 54);
		add(menuBar);

		JMenuItem character1 = new JMenuItem("Link",Character1img);
		menuBar.add(character1);

		JMenuItem character2 = new JMenuItem("Marie",Character2img);
		menuBar.add(character2);
		character2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				avatar2 = true;
				menuBar.setVisible(false);
				lblNewLabel.setVisible(false);
				worldScan();
			}
		});
		character1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				avatar1 = true;
				menuBar.setVisible(false);
				lblNewLabel.setVisible(false);
				worldScan();
			}
		});

	}

	/** Jose A Velazquez Torres 03/07/2020
	 ** In this method we check if the avatar makes contact with a building
	here we manage the state of visibility of the building 
	and the count of how many are left for discover
	 */
	public void checkBuildingColition() {
		for(int i=0; i<buildings.size(); i++){
			Building build = buildings.get(i);
			if(build.intersects(player) && build.getVisible()!=2){
				if(this.direction==0)this.moveMegaManLeft();
				if(this.direction==1)this.moveMegaManRight();
				if(this.direction==2)this.moveAvatarUp();
				if(this.direction==3)this.moveMegaManDown();
				if(build.getVisible()==0)numBuildings--;
				build.setVisible(1);
				questions.setBuild(build);
				if(questions.visible()){  
					build.setVisible(2);
				}  
				ki.reset();
			}
			else if(build.intersects(player) && build.getVisible()==2) {
				if(this.direction==0)this.moveMegaManLeft();
				if(this.direction==1)this.moveMegaManRight();
				if(this.direction==2)this.moveAvatarUp();
				if(this.direction==3)this.moveMegaManDown();
				ki.reset();
			}

		}
	}

	/** Jose A Velazquez Torres 03/24/2020
	 ** In this method we check if the avatar makes contact with a tree
	and makes sure the avatar don't walk over the tree.
	 */
	public void checkTreeColition() {
		for(int i=0; i<trees.size(); i++){
			Tree t1 = trees.get(i);
			if(t1.intersects(player)){
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
		checkTreeColition();
		//		if(sele==0) {
		//			t.stop();
		//			worldScan();
		//			t.restart();
		//		}
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
				//ki.onlyPress(KeyEvent.VK_RIGHT);

				walking=!walking;
				walkingTimer = 10;
			}

		}

		if (this.ki.isLeftIsPressed()) {
			this.direction = 1;

			this.moveMegaManLeft();
			walkingTimer--;
			if(walkingTimer == 0) {
				//ki.onlyPress(KeyEvent.VK_LEFT);
				walking=!walking;
				walkingTimer = 10;
			}
		}

		if (this.ki.isUpIsPressed()) {
			this.direction = 3;

			this.moveAvatarUp();
			walkingTimer--;
			if(walkingTimer == 0) {
				//ki.onlyPress(KeyEvent.VK_UP);
				walking=!walking;
				walkingTimer = 10;
			}
		}

		if (this.ki.isDownIsPressed()) {
			this.direction = 2;

			this.moveMegaManDown();
			walkingTimer--;
			if(walkingTimer == 0) {
				//ki.onlyPress(KeyEvent.VK_DOWN);
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
