package Frames;

import java.awt.Color;
import java.awt.Desktop;
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
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import Inputs.KeyInputs;
import Main.Questions;
import Text.QuestionsReader;
import Text.Reader;

import javax.imageio.ImageIO;
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
	private Reader br= new Reader(); //instance of Reader Class
	private ArrayList<Building> buildings = new ArrayList<>() ;//list of buildings on the world
	private ArrayList<Tree> trees = new ArrayList<>() ;// list of trees on the world
	private boolean walking=false;// parameter to know if the player is walking
	private Questions questions = new Questions(this,null);//instance of Questions Class
	private int walkingTimer = 10;//timer used for change image
	private int numBuildings=0 ;// counter of buildings
	private int sel;//selected world
	private KeyInputs ki = new KeyInputs();//instance of KeyInputs Class
	int direction = 0;// used for know what direction the avatar is moving
	private GraphicsManager gm = new GraphicsManager();//instance of GraphicManager Class
	private Player player ;//instance of Player Class
	private QuestionsReader qr= new QuestionsReader();//instance of QuestionReader Class
	private String worldSelected;//name of the world selected
	private int sele=0;// used for create world
	private boolean avatar1 = false;// used for know what avatar is selected
	private boolean avatar2 = false;// used for know what avatar is selected
	private int worldSize ;//length of the folder of worlds
	Timer t = new Timer(5,this);// make the ticks on this class
	FrameManager f;//instance of FrameManager Class
	private BufferedImage background;//background selected

	public PlayerInterface(FrameManager f) {
		t.start();//tick start to running
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

		player = new Player(0,620,f.getWidth(),f.getHeight());

	}

	/**Created by Carlos Rodriguez 03/06/2020
	 * Draw all the components on the JPanel.
	 */
	public void paintComponent(Graphics g){

		super.paintComponent(g);  
		//		this.setBackground(bColor);

		/** Dianelys Saldana 03/29/2020
		 ** Painting background chosen in MapDesigner
		 */

		try {
			background = ImageIO.read(getClass().getResource("../Image/" + br.getBackground()));
			g.drawImage(background, 0, 0, 1024, 735, this);
		} catch (IOException e) {
			e.printStackTrace();
		}

		Graphics2D g2= (Graphics2D)g;
		//Used for make the avatar 
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
		//Used for draw the buildings 
		//Carlos Rodriguez 03/06/2020
		for(int i=0;i<buildings.size();i++) {
			if(buildings.get(i).getVisible()==2) {
				g2.setColor(Color.RED);
				//this.gm.drawHouse(buildings.get(i), g2, this);
				BufferedImage img=null;
				try {

					img = ImageIO.read(getClass().getResource("../Image/"+buildings.get(i).getImage()));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				g2.drawImage(img, buildings.get(i).midX(buildings.get(i).upperLine()),buildings.get(i).midY(buildings.get(i).upperLine()),this);
				for(int j=0;j<buildings.get(i).getLines().size();j++) {
					//					g2.drawLine((int)buildings.get(i).getLines().get(j).getX1(),(int) buildings.get(i).getLines().get(j).getY1(),
					//							(int)buildings.get(i).getLines().get(j).getX2(),(int) buildings.get(i).getLines().get(j).getY2());
				}
				g2.setColor(Color.BLACK);
			}
			else if(buildings.get(i).getVisible()==1) {
				for(int j=0;j<buildings.get(i).getLines().size();j++) {
					g2.drawLine((int)buildings.get(i).getLines().get(j).getX1(),(int) buildings.get(i).getLines().get(j).getY1(),
							(int)buildings.get(i).getLines().get(j).getX2(),(int) buildings.get(i).getLines().get(j).getY2());
				}
			}
		}
		//Used for draw the trees
		//Carlos Rodriguez 03/06/2020
		for(int i=0;i<trees.size();i++) {
			int var=trees.get(i).getVar();
			if(var==1)this.gm.drawTree1(trees.get(i), g2, this);
			else if(var==2)this.gm.drawTree2(trees.get(i), g2, this);
			else if(var==3)this.gm.drawTree3(trees.get(i), g2, this);
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
	/**Carlos Rodriguez 3/28/2020
	 * Scan the names of worlds on a specific folder
	 */
	private void worldScan() {
		if(sele==0) {
			ArrayList<String> arr= new ArrayList<>();
			//			for(int i=1;i<this.worldSize+1;i++) {
			//				arr.add("world"+i);
			//			}
			File folder = new File("src/World");
			File[] listOfFiles = folder.listFiles();
			for (int i = 0; i < listOfFiles.length; i++) {
				if (listOfFiles[i].isFile()) {
					String sub= listOfFiles[i].getName().replace(".txt", "");
					arr.add(sub);
				}
			}
			sel=questions.arraySelection(arr.toArray(), "Que mundo desea utilizar");

			if(sel==JOptionPane.CLOSED_OPTION) {
				f.menu();
				return;
			}
			worldSelected=arr.get(sel);
			sel++;
			sele=1;
		}
		if(sele==1) {
			scan();
			sele=2;
		}
		else return;
	}
	/**Carlos Rodriguez 3/24/2020
	 * Scan the world on a specific file
	 */
	private void scan() {

		try {
			br.scan(worldSelected);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		trees=br.getTrees();
		for(int i=0;i<br.getBuildings().size();i++) {
			if(br.getBuildings().get(i).getPoint().size()<2)continue;
			Building build =  new Building(br.getBuildings().get(i).getPoint());

			build.setAnswer(br.getBuildings().get(i).getAnswer());
			build.setImage(br.getBuildings().get(i).getImage());
			build.setQuestions(br.getBuildings().get(i).getQuestions());
			build.setName(br.getBuildings().get(i).getName());
			build.createLine();
			buildings.add(build);
		}
		if(buildings.size()>0) {
			try {
				qr.worldScan("world"+sel, buildings);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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
				if(this.direction==0)player.moveMegaManLeft();
				if(this.direction==1)player.moveMegaManRight();
				if(this.direction==2)player.moveAvatarUp();
				if(this.direction==3)player.moveMegaManDown();
				if(build.getVisible()==0)numBuildings--;
				build.setVisible(1);
				questions.setBuild(build);
				if(questions.visible()){  
					build.setVisible(2);
				}  
				ki.reset();
			}
			else if(build.intersects(player) && build.getVisible()==2) {
				if(this.direction==0)player.moveMegaManLeft();
				if(this.direction==1)player.moveMegaManRight();
				if(this.direction==2)player.moveAvatarUp();
				if(this.direction==3)player.moveMegaManDown();
				ki.reset();
			}

			// Dianelys Saldana 04/11/2020
			if(buildings.get(i).getVisible() == 2 && numBuildings == 0) {
				// JOption Pane
				this.display3D();
			}

		}
	}

	// Dianelys Saldana 04/11/2020
	public void display3D() {
		int reply = JOptionPane.showConfirmDialog(null, "¡Has desbloqueado todos los edificios!"
				+ " ¿Deseas ver el mapa en 3D?", "3D Map", JOptionPane.YES_NO_OPTION);
		if (reply == JOptionPane.YES_OPTION) {
			Desktop file = Desktop.getDesktop();
			try {
				file.open(new File("src/VRML_Worlds/house3.wrl"));
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, 
						e.getMessage(), 
						"Error", 
						JOptionPane.ERROR_MESSAGE);
			}
		} else {
			JOptionPane.showMessageDialog(null, "Salir");
			System.exit(0);
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
				if(this.direction==0)player.moveMegaManLeft();
				if(this.direction==1)player.moveMegaManRight();
				if(this.direction==2)player.moveAvatarUp();
				if(this.direction==3)player.moveMegaManDown();
				ki.reset();
			}  

		}
	}

	/**Created by Carlos Rodriguez 03/06/2020
	 * Make the Avatar move pressing arrow
	 */
	private void movePlayer() {
		if (this.ki.isRightIsPressed() && !this.ki.isDownIsPressed() &&
				!this.ki.isLeftIsPressed() && !this.ki.isUpIsPressed()) {
			this.direction=0;
			player.moveMegaManRight();
			walkingTimer--;
			if(walkingTimer == 0) {
				walking=!walking;
				walkingTimer = 10;
			}
		}

		if (!this.ki.isRightIsPressed() && !this.ki.isDownIsPressed() &&
				this.ki.isLeftIsPressed() && !this.ki.isUpIsPressed()) {
			this.direction = 1;
			player.moveMegaManLeft();
			walkingTimer--;
			if(walkingTimer == 0) {
				walking=!walking;
				walkingTimer = 10;
			}
		}

		if (!this.ki.isRightIsPressed() && !this.ki.isDownIsPressed() &&
				!this.ki.isLeftIsPressed() && this.ki.isUpIsPressed()) {
			this.direction = 3;
			player.moveAvatarUp();
			walkingTimer--;
			if(walkingTimer == 0) {
				walking=!walking;
				walkingTimer = 10;
			}
		}

		if (!this.ki.isRightIsPressed() && this.ki.isDownIsPressed() &&
				!this.ki.isLeftIsPressed() && !this.ki.isUpIsPressed()) {
			this.direction = 2;
			player.moveMegaManDown();
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
	}




}
