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
import Text.WriterVRML;

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
	private Reader br= new Reader(); // instance of the reader class 
	private int sel;// selected world index 
	private JFrame frame; // Jframe used in this class
	private ArrayList<Tree> trees=new ArrayList<>(); // list of trees on the map 
	private boolean rec=false; // parameter to make rectangles on the screen when trees are going to be added 
	private boolean draw= false;//parameter to make circles on the screen when buildings are going to be added 
	private boolean nuevo=false; //true if new map is selected
	private boolean eliminate=false; // is true if you are going to eliminate a tree 
	private GraphicsManager gm = new GraphicsManager();
	private int mouseX;// position on X of the mouse 
	private int mouseY;// position on Y of the mouse 
	private Color neutralColor; // neutral color of the buttons 
	private int tree ; // posible variations of the trees 
	private JTextPane xCoor = new JTextPane();//coordinates showing 
	private JTextPane yCoor = new JTextPane();//coordinates showing 
	private JMenuItem tree1; // tree button 
	private JMenuItem tree2;// tree button 
	private JMenuItem tree3;// tree button 
	private JMenuItem building;// building button 
	private JMenuItem home;// home button 
	private JMenuItem bttEliminateTree;// Eliminate tree button 
	private JMenuItem bttEliminateBuilding;// Eliminate tree button 
	private BufferedImage background;//selected background
	private ArrayList<JMenuItem> items= new ArrayList<>();//list of buttons 
	private Questions question = new Questions(this,null);// instance of the Questions class 
	private FrameManager f; // instance of the FrameManagerclass 
	private Writer writer ;// instance of the Writer class 
	private WriterVRML writer3D ;// instance of the WriterVRML class 
	private int wallHeight;// height inputed of wall
	private ArrayList<ArrayList<Pair>>listPoints= new ArrayList<>(); //list of points of all the buildings 
	private int wallIndex=1;// index to know what wall is creating 
	private int worldIndex= 1;// parameter for read all the worlds 
	private int sele=0;// parameter to read the selected world 
	private String worldSelected="";// name of the selected world 
	private JButton bttEndBuilding;
	private String response=null;
	private boolean closed=false;
	private ArrayList<String> buildings= new ArrayList<>();
	private String worldName =null;
	ArrayList<String> worldArr= new ArrayList<>();
	/**
	 * Create the application.
	 *
	 */
	public MapDesigner(FrameManager f)  {
		this.f=f;
		
		try {
			background = ImageIO.read(getClass().getResource("../Image/MapDefault.png"));
			worldSelect();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		items.add(tree1);
		items.add(tree2);
		items.add(tree3);
		items.add(building);
		items.add(home);
		this.f = f;

		f.addWindowListener(new java.awt.event.WindowAdapter() {
			@Override
			public void windowClosing(java.awt.event.WindowEvent windowEvent) {
				//closed es el caso donde el usario nisiquiera cogio un mapa 
				if(!closed) {
					if(draw)
						try {
							writer.end();
						} catch (IOException e) {

							e.printStackTrace();
						}

					try {
						writer.questionFile();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
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
				//Used when a building's walls  is in process of creation recording all the data 
				//Carlos Rodriguez 4/10/2020
				if(draw) {
					listPoints.get(listPoints.size()-1).add(new Pair(mouseX,mouseY));
					if(listPoints.get(listPoints.size()-1).size()>1) {
						ArrayList<Pair>point=listPoints.get(listPoints.size()-1);
						int ans= question.wallHeight();
						double length = writer3D.length(point.get(point.size()-2), point.get(point.size()-1));
						double angle = writer3D.angle(point.get(point.size()-2), point.get(point.size()-1));
						double tx = writer3D.xTrans(point.get(point.size()-2), point.get(point.size()-1));
						double tz = writer3D.zTrans(point.get(point.size()-2), point.get(point.size()-1));
						//eliminate de building if you cancel 
						if(ans==-1) {
							draw=false;
							resetColor(4);
							bttEndBuilding.setVisible(false);
							buildings.remove(buildings.size()-1);
							try {
								writer.end();
							} catch (IOException e2) {
								// TODO Auto-generated catch block
								e2.printStackTrace();
							}
							try {
								findBuilding(response);
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							listPoints.clear();

							try {
								writer.getMyWriter().flush();
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}

							scan();

							return;
						}
						else {

							wallHeight=ans;

							while(true) {
								String[] imagesNames = {"Casa.png","Desea utilizar una imagen de su computadora?"};
								int imageName =question.arraySelection(imagesNames, "Que imagen desea utilizar en la textura de la pared");
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
											writer3D.writeWall(Double.toString(length), Double.toString(wallHeight), Double.toString(-angle), Double.toString(wallHeight/2), Double.toString(tx), Double.toString(tz), file.getName());
											break;
										} catch (IOException e1) {
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
										writer3D.writeWall(Double.toString(length), Double.toString(wallHeight), Double.toString(-angle), Double.toString(wallHeight/2), Double.toString(tx), Double.toString(tz), imagesNames[imageName]);
										writer.writeWall(wallIndex++, point.get(point.size()-2), point.get(point.size()-1), wallHeight,imagesNames[imageName]);
									} catch (IOException e1) {

										e1.printStackTrace();
									}
									break;
								}
							}
						}
					}
				}
				//used for create trees  
				else if(rec) {
					trees.add(new Tree(mouseX,mouseY,tree));
					try { 
						writer3D.writeTree(Double.toString(mouseX-512), Double.toString(mouseY-367.5), tree);
						writer.writeTree(mouseX, mouseY, tree);
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
				//used for eliminate trees
				else if(eliminate){
					try {
						findTree(new Pair(mouseX,mouseY));
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
		//Trees images 
		ImageIcon Tree1img= new ImageIcon(getClass().getResource("../Image/Tree1.png"));
		ImageIcon Tree2img= new ImageIcon(getClass().getResource("../Image/Tree2.png"));
		ImageIcon Tree3img= new ImageIcon(getClass().getResource("../Image/Tree3.png"));
		ImageIcon DeleteTree= new ImageIcon(getClass().getResource("../Image/Delete-Trees.png"));
		ImageIcon DeleteBuilding= new ImageIcon(getClass().getResource("../Image/Delete-buildings.png"));
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 1025, 54);
		add(menuBar);

		xCoor.setBounds(834, 594, 45, 32);
		add(xCoor);


		yCoor.setBounds(921, 594, 45, 32);
		add(yCoor);
		//end Button creation 
		bttEndBuilding = new JButton("End Building");
		bttEndBuilding.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					writer.end();
				} catch (IOException e) {

					e.printStackTrace();
				}
				rec=false;
				draw=false;
				resetColor(4);
				bttEndBuilding.setVisible(false);
			}
		});
		bttEndBuilding.setFont(new Font("Tahoma", Font.PLAIN, 13));
		bttEndBuilding.setBounds(0, 51, 141, 35);
		add(bttEndBuilding);
		bttEndBuilding.setVisible(false);



		//Eliminate Tree Button creation 
		bttEliminateTree = new JMenuItem("",DeleteTree);
		//		bttEliminateTree = new JMenuItem("Eliminate Tree");
		bttEliminateTree.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		bttEliminateTree.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				eliminate=true;
				rec=false; 
				draw=false;
				resetColor(4);
				bttEliminateTree.setBackground(Color.GRAY);
			}
		});

		menuBar.add(bttEliminateTree);


		//Tree Button creation
		tree1 = new JMenuItem("Tree 1",Tree1img);
		tree1.setFont(new Font("Lucida Grande", Font.PLAIN, 13));

		tree1.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if(draw) {
					draw=false;
					try {
						writer.end();
					} catch (IOException e1) {

						e1.printStackTrace();
					}
				}
				eliminate=false;
				rec=true;
				tree=1;
				repaint();
				resetColor(0);
				tree1.setBackground(Color.GRAY);
			}
		});

		menuBar.add(tree1);
		//Tree Button creation 
		tree2 = new JMenuItem("Tree 2",Tree2img);
		tree2.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
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
				eliminate=false;
				rec=true;
				tree=2;
				repaint();
				resetColor(1);
				tree2.setBackground(Color.GRAY);
			}
		});

		//Eliminate Building Button	
		bttEliminateBuilding = new JMenuItem("",DeleteBuilding);	
		//		bttEliminateBuilding = new JMenuItem("Eliminate Building");	
		bttEliminateBuilding.setFont(new Font("Lucida Grande", Font.PLAIN, 13));	
		bttEliminateBuilding.addActionListener(new ActionListener() {	
			public void actionPerformed(ActionEvent arg0) {
				int buildingIndex=question.arraySelection(buildings.toArray(), "Que edificio desea eliminar");
				if(buildingIndex==JOptionPane.CLOSED_OPTION) {
					return;
				}
				String buildingName = buildings.get(buildingIndex);
				
				try {
					if(findBuilding(buildingName)) {

						JOptionPane.showMessageDialog(f, "Construccion eliminada");
					}
					else JOptionPane.showMessageDialog(f, "El nombre de este edificio no aparece en los archivos ");


				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				buildings.remove(buildingIndex);
				listPoints.clear();
				try {
					writer.getMyWriter().flush();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				scan();

				repaint();		
				eliminate=false;
				rec=false;
				draw=false;
				resetColor(5);

			}
		});
		//Tree Button creation 	
		tree3 = new JMenuItem("Tree 3",Tree3img);	
		tree3.setFont(new Font("Lucida Grande", Font.PLAIN, 13));	
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
				eliminate=false;	
				rec=true;	
				tree=3;	
				repaint();	
				resetColor(2);	
				tree3.setBackground(Color.GRAY);	
			}	
		});	

		menuBar.add(bttEliminateBuilding);
		//Building Button creation 
		building = new JMenuItem("Building");
		building.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		building.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				bttEndBuilding.setVisible(true);
				eliminate=false;
				rec=false;
				neutralColor= building.getBackground();
				draw=true;
				listPoints.add(new ArrayList<Pair>());
				wallIndex=1;
				resetColor(3);

				repaint();
				//used for save all the data of the buildings 
				//Carlos Rodriguez 4/10/2020
				try {
					response =question.questionsString("Cual sera el nombre de esta construccion?");
					//Caso cuando no se responde adecuadamente
					if(response==null || response.equals("")) {
						draw=false;
						resetColor(5);
						bttEndBuilding.setVisible(false);
						listPoints.clear();

						writer.getMyWriter().flush();

						scan();

					}
					//Caso donde el edificio ya exista 
					else if(writer.lineExist("BuildingName: "+response)) {
						JOptionPane.showMessageDialog(f,"Ya existe un edificio con ese nombre");
						draw=false;
						resetColor(5);
						bttEndBuilding.setVisible(false);

					}
					else{


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
										buildings.add(response);
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
								buildings.add(response);
								break;
							}
						}

						//}
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
		home.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		home.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				if(draw) {
					draw=false;
					try {
						writer.end();
					} catch (IOException e1) {

						e1.printStackTrace();
					}
				}
				f.menu();

			}
		});
		menuBar.add(home);
		
		/**Autor: Angel D. Hernandez Denis
		 * Fecha: 04/23/2020
		 ** Boton para escoger el background
		 */
		JMenuItem backCombo = new JMenuItem();
		backCombo.setText("Background");
		menuBar.add(backCombo);
		backCombo.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		backCombo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				while(true) {
					//Panel que le pregunta al usuario si que escoja una foto
					String[] imagesNames = {"Escoger imagen"};
					int imageName = question.arraySelection(imagesNames, "Escoga una imagen de su computadora para el background");
					if(imageName==JOptionPane.CLOSED_OPTION) {
						draw=false;
						resetColor(4);
						break;
					}
					else if(imageName==imagesNames.length-1) {
						//File Chooser para buscar la el background en la computadora
						JFileChooser fileChooser = new JFileChooser();
						int option = fileChooser.showOpenDialog(frame);
						if(option == JFileChooser.APPROVE_OPTION){
							File file = fileChooser.getSelectedFile();
							String extension= file.getName().substring(file.getName().lastIndexOf(".") + 1);
							if(!extension.equals("png")) {
								JOptionPane.showMessageDialog(f, "Favor de utilizar un archivo png");
								continue;
							}
							//Escribe el nombre del background en el file y desplega un mensaje diciendo que fue modificado
							try {
								findMapAndDelete();
								writer.copy(file);
								writer.writeBackground(file.getName());
								writer3D.writeFloor(file.getName());
								String[] updatedImage = {"Ok"};
								int updatedImage1 = question.arraySelection(updatedImage, "        El background a sido actualizado");
								break;
							} catch (IOException e1) {
								e1.printStackTrace();
							}
						}
						else if(option == JFileChooser.CANCEL_OPTION) {
							draw=false;
							resetColor(4);
							break;
						}
					}
				}
			}
		});
	}

	/**Carlos Rodriguez 3/15/2020
	 * Paint the Jpanel
	 */
	public void paintComponent(Graphics g){
		super.paintComponent(g);  
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
			ArrayList<Pair>lastArray=null;
			if(!listPoints.isEmpty()) lastArray= listPoints.get(listPoints.size()-1);
			if(lastArray!=null) {
				if(!lastArray.isEmpty()) {
					int tempX=  lastArray.get(lastArray.size()-1).getX();
					int tempY =lastArray.get(lastArray.size()-1).getY();
					g2.drawLine(tempX, tempY, mouseX, mouseY);
				}
			}
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
			this.bttEliminateTree.setBackground(neutralColor);

		}
		if(x==1) {
			this.tree1.setBackground(neutralColor);
			this.tree2.setBackground(Color.GRAY);
			this.tree3.setBackground(neutralColor);
			this.building.setBackground(neutralColor);
			this.bttEliminateTree.setBackground(neutralColor);
		}
		if(x==2) {
			this.tree1.setBackground(neutralColor);
			this.tree2.setBackground(neutralColor);
			this.tree3.setBackground(Color.GRAY);
			this.building.setBackground(neutralColor);
			this.bttEliminateTree.setBackground(neutralColor);
		}
		if(x==3) {
			this.tree1.setBackground(neutralColor);
			this.tree2.setBackground(neutralColor);
			this.tree3.setBackground(neutralColor);
			this.building.setBackground(Color.GRAY);
			this.bttEliminateTree.setBackground(neutralColor);
		}
		else {
			this.tree1.setBackground(neutralColor);
			this.tree2.setBackground(neutralColor);
			this.tree3.setBackground(neutralColor);
			this.building.setBackground(neutralColor);
		}
	}
	/**Carlos Rodriguez 4/10/2020
	 * Make the option to use any world
	 * @throws IOException 
	 */
	public void worldSelect() throws IOException {
		int lastIndex=0;//last world in folder 
		if(sele==0) {
			
			ArrayList<String> arr3D = new ArrayList<>();

			File folder = new File("src/World");// folder of worlds 
			File[] listOfFiles = folder.listFiles();//list of worlds
			File folder3D = new File("src/VRML_Worlds"); // folder of 3D Worlds
			File[] list = folder3D.listFiles(); // list of 3D worlds
			for (int i = 0; i < listOfFiles.length; i++) {
				if (listOfFiles[i].isFile()) {
					String sub= listOfFiles[i].getName().replace(".txt", "");
					String sub3D = listOfFiles[i].getName().replace(".wrl", "");
					worldArr.add(sub);
					arr3D.add(sub3D);

				}
			}
			worldArr.add("New World");
			lastIndex=worldArr.size()-1;
			sel=question.arraySelection(worldArr.toArray(), "Que mundo desea utilizar");//selected world

			if(sel==JOptionPane.CLOSED_OPTION) {//closed option case 

				f.menu();
				closed=true;
				return;
			}
			worldSelected=worldArr.get(sel);
			if(sel==lastIndex) {// if new world is selected
				
				while(worldName==null||this.findInFile(worldName)||worldName.equals("")) {
					
					 worldName = question.questionsString("Como desea que se llame el mundo");
					 if(worldName==null||worldName.equals(""))JOptionPane.showMessageDialog(f, "Favor de utilizar un Nombre");
					 if(this.findInFile(worldName))JOptionPane.showMessageDialog(f, "Mundo ya existente");
				}
				
				writer= new Writer(worldName);
				writer3D= new WriterVRML(worldName); 
				nuevo=true;
				try {
					writer.create();
					writer3D.create();
					writer3D.writeFile();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				return;

			}
			worldName= worldSelected;
			writer= new Writer(worldSelected);
			writer3D= new WriterVRML(worldSelected);
			writer.deleteLine("QuestionsFile: "+worldSelected+"Questions.txt");
			sel++;
			sele=1;
		}
		if(sele==1) {
			scan();
			sele=2;
			for(int i=0;i<br.getBuildings().size();i++) {

				this.buildings.add(br.getBuildings().get(i).getName());

			}
		}
		else return;

	}
	/**Carlos Rodriguez 4/10/2020
	 * Used for scan all the buildings and trees of the world
	 */
	private void scan() {

		try {
			br.scan(worldName);
			background = ImageIO.read(getClass().getResource("../Image/"+br.getBackground()));
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
	/**Carlos Rodriguez 4/10/2020
	 * Used for elimination of trees , give the tree in the position touched
	 */
	private void findTree(Pair point) throws IOException {
		for(int i=0;i<this.trees.size();i++) {
			if(trees.get(i).getX()<=point.getX()
					&&point.getX()<=trees.get(i).getX()+trees.get(i).getWidth()
					&&trees.get(i).getY()<=point.getY()
					&&point.getY()<=trees.get(i).getY()+trees.get(i).getHeight()) {
				String target ="TreeType:"+trees.get(i).getVar()+", location: ("+(int)trees.get(i).getX()+","+(int)trees.get(i).getY()+")";
				writer.deleteLine(target);
				trees.remove(i);
				repaint();
				break;
			}
		}
	}
	/**Carlos Rodriguez 4/21/2020
	 * Used for elimination of Buildings , give the name of the building
	 */
	private boolean findBuilding(String s) throws IOException{
		if(!writer.lineExist("BuildingName: "+s))return false;
		writer.deleteLinesFromFile("BuildingName: "+s);
		writer.deleteLinesFromQuestionFile("Building: "+s);
		return true;
	}
	/**Autor: Angel D. Hernandez Denis
	 * Fecha: 4/24/2020
	 * Used for elimination of the background
	 */
	private void findMapAndDelete() throws IOException {
		writer.deleteLineifContains("Background");
	}
	/**Carlos Rodriguez 4/24/2020
	 * Busca una linea en un file especifico y devuelve cierto si la encontro
	 */
	private boolean findInFile(String s) {
		if(worldArr.contains(s))return true;
		return false;
		
	}
}
