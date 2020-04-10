package Frames;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Entities.Building;
import Entities.Tree;
import Image.GraphicsManager;
import Util.Pair;
import Main.Questions;
import Text.Reader;
import Text.Writer;

import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.event.MouseEvent;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import javax.swing.JTextPane;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;

import java.awt.Font;


public class MapDesigner extends JPanel   {
	private Reader br= new Reader();
	private Questions questions = new Questions(this,null);
	private int sel;
	private JFrame frame;
	private ArrayList<Tree> trees=new ArrayList<>();
	private boolean rec=false;
	private boolean draw= false;
	private boolean nuevo=false;
	private GraphicsManager gm = new GraphicsManager();
	private int mouseX;
	private int mouseY;
	private Color neutralColor;
	private int tree ;
	private JTextPane xCoor = new JTextPane();
	private JTextPane yCoor = new JTextPane();
	private JMenuItem tree1;
	private JMenuItem tree2;
	private JMenuItem tree3;
	private JMenuItem building;
	private JMenuItem home;
	private String[] backgrounds = {"Background", "Original", "Beach"};
	private BufferedImage background;
	private ArrayList<JMenuItem> items= new ArrayList<>();
	private Questions question = new Questions(this,null);
	private FrameManager f;
	private Writer writer ;
	private int wallHeight;
	private String buildingName;
	private ArrayList<ArrayList<Pair>>listPoints= new ArrayList<>();
	private int wallIndex=1;
	private int worldIndex= 1;
	private int sele=0;
	private String worldSelected="";

	/**
	 * Create the application.
	 */
	public MapDesigner(FrameManager f) {
		this.f=f;
		File folder = new File("src/World");
		File[] listOfFiles = folder.listFiles();
		for (int i = 0; i < listOfFiles.length; i++) {
			if (listOfFiles[i].isFile()) {
				String sub= listOfFiles[i].getName().replace("world", "");
				String sub2= sub.replace(".txt", "");
				if(Integer.parseInt(sub2)==worldIndex) {
					worldIndex++;
				}
				else {
					break;
				}

			}
		}
		worldSelect();
		items.add(tree1);
		items.add(tree2);
		items.add(tree3);
		items.add(building);
		items.add(home);
		this.f = f;
		f.addWindowListener(new java.awt.event.WindowAdapter() {
			@Override
			public void windowClosing(java.awt.event.WindowEvent windowEvent) {

				if(draw)
					try {
						writer.end();
					} catch (IOException e) {

						e.printStackTrace();
					}
				if(nuevo)
					try {
						writer.questionFile();
					} catch (IOException e) {

						e.printStackTrace();
					}
				System.exit(0);

			}
		});
		initialize();
	}


	/**Carlos Rodriguez 3/15/2020
	 * Initialize the contents of the frame.
	 * @throws IOException 
	 */
	private void initialize() {
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(draw) {
					listPoints.get(listPoints.size()-1).add(new Pair(mouseX,mouseY));
					if(listPoints.get(listPoints.size()-1).size()>1) {
						ArrayList<Pair>point=listPoints.get(listPoints.size()-1);
						while(true) {
							String[] imagesNames = {"Casa.png","Desea utilizar una imagen de su computadora?"};
							int imageName =question.arraySelection(imagesNames, "Que imagen desea utilizar en el edificio");
							if(imageName==JOptionPane.CLOSED_OPTION) {
								JOptionPane.showMessageDialog(f, "Favor de escoger una imagen");
								continue;
							}
							else if(imageName==imagesNames.length-1) {
								JFileChooser fileChooser = new JFileChooser();

								int option = fileChooser.showOpenDialog(frame);
								if(option == JFileChooser.APPROVE_OPTION){
									File file = fileChooser.getSelectedFile();
									String extension= file.getName().substring(file.getName().lastIndexOf(".") + 1);
									if(!extension.equals("png")) {
										JOptionPane.showMessageDialog(f, "Favor de utilizar un archivo png");
										continue;
									}

									try {
										writer.copy(file);
										writer.writeWall(wallIndex++, point.get(point.size()-2), point.get(point.size()-1), wallHeight,file.getName());
										break;
									} catch (IOException e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}
								}
								else {
									JOptionPane.showMessageDialog(f, "Favor de utilizar un archivo png");
									continue;

								}


							}
							else {
								try {
									writer.writeWall(wallIndex++, point.get(point.size()-2), point.get(point.size()-1), wallHeight,imagesNames[imageName]);
								} catch (IOException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
								break;
							}
						}
					}
				}
				else if(rec) {
					trees.add(new Tree(mouseX,mouseY,tree));
					try {
						writer.writeTree(mouseX, mouseY, tree);
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				mouseX=e.getX();
				mouseY=e.getY();
			}
		});
		this.setFocusable(true);
		setLayout(null);
		ImageIcon Tree1img= new ImageIcon(getClass().getResource("../Image/Tree1.png"));
		ImageIcon Tree2img= new ImageIcon(getClass().getResource("../Image/Tree2.png"));
		ImageIcon Tree3img= new ImageIcon(getClass().getResource("../Image/Tree3.png"));
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 756, 54);
		add(menuBar);

		xCoor.setBounds(834, 594, 45, 32);
		add(xCoor);


		yCoor.setBounds(921, 594, 45, 32);
		add(yCoor);

		JButton bttEndBuilding = new JButton("End Building");
		bttEndBuilding.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					writer.end();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				rec=false;
				draw=false;
				resetColor(4);
				bttEndBuilding.setVisible(false);
			}
		});
		bttEndBuilding.setFont(new Font("Tahoma", Font.PLAIN, 17));
		bttEndBuilding.setBounds(0, 51, 141, 35);
		add(bttEndBuilding);
		bttEndBuilding.setVisible(false);
		tree1 = new JMenuItem("Tree 1",Tree1img);
		tree1.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if(draw) {
					draw=false;
					try {
						writer.end();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				rec=true;
				tree=1;
				repaint();
				resetColor(0);
				tree1.setBackground(Color.GRAY);
			}
		});

		menuBar.add(tree1);

		tree2 = new JMenuItem("Tree 2",Tree2img);
		menuBar.add(tree2);
		tree2.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if(draw) {
					draw=false;
					try {
						writer.end();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}

				rec=true;
				tree=2;
				repaint();
				resetColor(1);
				tree2.setBackground(Color.GRAY);
			}
		});

		tree3 = new JMenuItem("Tree 3",Tree3img);
		menuBar.add(tree3);
		tree3.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if(draw) {
					draw=false;
					try {
						writer.end();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				rec=true;
				tree=3;
				repaint();
				resetColor(2);
				tree3.setBackground(Color.GRAY);
			}
		});

		building = new JMenuItem("Building");
		building.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				bttEndBuilding.setVisible(true);
				rec=false;
				neutralColor= building.getBackground();
				draw=true;
				listPoints.add(new ArrayList<Pair>());
				wallIndex=1;
				resetColor(3);

				repaint();
				try {
					String response =question.questionsString("Cual sera el nombre de esta construccion?");
					if(response==null || response.equals("")) {
						draw=false;
						resetColor(4);
						bttEndBuilding.setVisible(false);

					}
					else{

						int ans= question.wallHeight();
						if(ans==-1) {
							draw=false;
							resetColor(4);
							bttEndBuilding.setVisible(false);	
						}
						else {

							wallHeight=ans;

							while(true) {
								String[] imagesNames = {"Casa.png","Desea utilizar una imagen de su computadora?"};
								int imageName =question.arraySelection(imagesNames, "Que imagen desea utilizar en el edificio");
								if(imageName==JOptionPane.CLOSED_OPTION) {
									draw=false;
									resetColor(4);
									bttEndBuilding.setVisible(false);
									break;
								}
								else if(imageName==imagesNames.length-1) {
									JFileChooser fileChooser = new JFileChooser();

									int option = fileChooser.showOpenDialog(frame);
									if(option == JFileChooser.APPROVE_OPTION){
										File file = fileChooser.getSelectedFile();
										String extension= file.getName().substring(file.getName().lastIndexOf(".") + 1);
										if(!extension.equals("png")) {
											JOptionPane.showMessageDialog(f, "Favor de utilizar un archivo png");
											continue;
										}

										try {
											writer.copy(file);
											writer.writeBuilding(response,file.getName());
											break;
										} catch (IOException e1) {
											// TODO Auto-generated catch block
											e1.printStackTrace();
										}
									}
									else {
										JOptionPane.showMessageDialog(f, "Favor de utilizar un archivo png");
										continue;

									}


								}
								else {
									writer.writeBuilding(response,imagesNames[imageName]);
									break;
								}
							}

						}
					}
				} catch (IOException e1) {
					e1.printStackTrace();
				}

			}
		});
		menuBar.add(building);

		/** Dianelys Saldana 03/22/2020
		 ** Button for returning to home
		 */
		home = new JMenuItem("Home");
		home.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				if(draw) {
					draw=false;
					try {
						writer.end();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				f.menu();
			}
		});
		menuBar.add(home);

		/** Dianelys Saldana 03/29/2020
		 ** ComboBox for choosing background
		 */
		JComboBox backCombo = new JComboBox(backgrounds);
		backCombo.setBounds(756, 0, 150, 54);
		ActionListener bc = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				String s = (String) backCombo.getSelectedItem();

				switch (s) {
				case "Original":
					try {
						background = ImageIO.read(getClass().getResource("../Image/Map1.png"));
						writer.writeBackground("Map1.png");
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					break;
				case "Beach":
					try {
						background = ImageIO.read(getClass().getResource("../Image/Map2.png"));
						writer.writeBackground("Map2.png");
					} catch (IOException e1) {
						e1.printStackTrace();
					}	
					break;
				default:
					try {
						background = ImageIO.read(getClass().getResource("../Image/Default.png"));
						writer.writeBackground("Default.png");
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					break;
				}
			}
		};
		backCombo.addActionListener(bc);
		add(backCombo);
		try {
			background = ImageIO.read(getClass().getResource("../Image/Default.png"));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}


	/**Carlos Rodriguez 3/15/2020
	 * Paint the Jpanel
	 */
	public void paintComponent(Graphics g){
		super.paintComponent(g);  
		//		this.setBackground(Color.CYAN);
		g.drawImage(background, 0, 0, 1024, 735, this);

		Graphics2D g2= (Graphics2D)g;
		g2.setColor(Color.BLACK);
		if(rec) {
			g2.drawRect(mouseX, mouseY, 50, 50);

			xCoor.setText(""+(mouseX));
			yCoor.setText(""+mouseY);
			repaint();
		}
		if(draw) {
			g2.drawOval(mouseX, mouseY, 10, 10);
			xCoor.setText(""+mouseX);
			yCoor.setText(""+mouseY);
			repaint();
		}
		for(int i=0;i<trees.size();i++) {
			int var=trees.get(i).getVar();
			if(var==1)this.gm.drawTree1(trees.get(i), g2, this);
			else if(var==2)this.gm.drawTree2(trees.get(i), g2, this);
			else if(var==3)this.gm.drawTree3(trees.get(i), g2, this);
		}
		for(int i=0;i<listPoints.size();i++) {
			ArrayList<Pair> point= listPoints.get(i);
			for(int j=0;j<point.size();j++) {
				g2.drawOval(point.get(j).getX(), point.get(j).getY(), 10, 10);
			}


		}

		for(int i=0;i<listPoints.size();i++) {
			ArrayList<Pair> point= listPoints.get(i);
			for(int j= point.size()-1;j>0;j--) {
				g2.draw(new Line2D.Double(point.get(j - 1).getX(), point.get(j - 1).getY(),point.get(j).getX(),point.get(j).getY()));
			}
		}

	}

	/**Carlos Rodriguez 3/23/2020
	 * Set all the Jpanel with neutral color except the position given.
	 * @param x the position of Jbutton on the JPanel
	 */
	public void resetColor(int x) {
		if(x==0) {
			this.tree1.setBackground(Color.GRAY);
			this.tree2.setBackground(neutralColor);
			this.tree3.setBackground(neutralColor);
			this.building.setBackground(neutralColor);
		}
		if(x==1) {
			this.tree1.setBackground(neutralColor);
			this.tree2.setBackground(Color.GRAY);
			this.tree3.setBackground(neutralColor);
			this.building.setBackground(neutralColor);
		}
		if(x==2) {
			this.tree1.setBackground(neutralColor);
			this.tree2.setBackground(neutralColor);
			this.tree3.setBackground(Color.GRAY);
			this.building.setBackground(neutralColor);
		}
		if(x==3) {
			this.tree1.setBackground(neutralColor);
			this.tree2.setBackground(neutralColor);
			this.tree3.setBackground(neutralColor);
			this.building.setBackground(Color.GRAY);
		}
		else {
			this.tree1.setBackground(neutralColor);
			this.tree2.setBackground(neutralColor);
			this.tree3.setBackground(neutralColor);
			this.building.setBackground(neutralColor);
		}
	}
	public void worldSelect() {
		int lastIndex=0;
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
			arr.add("New World");
			lastIndex=arr.size()-1;
			sel=questions.arraySelection(arr.toArray(), "Que mundo desea utilizar");

			if(sel==JOptionPane.CLOSED_OPTION) {
				System.out.println("entre");
				f.menu();
				return;
			}
			worldSelected=arr.get(sel);
			if(sel==lastIndex) {
				writer= new Writer("world"+worldIndex);
				nuevo=true;
				try {
					writer.create();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				return;

			}
			writer= new Writer(worldSelected);

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
			br.scan(worldSelected);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		trees=br.getTrees();
		for(int i=0;i<br.getBuildings().size();i++) {
			if(br.getBuildings().get(i).getPoint().size()<2)continue;
			this.listPoints.add(br.getBuildings().get(i).getPoint());

		}

	}


}
