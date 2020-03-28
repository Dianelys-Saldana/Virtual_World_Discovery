package Frames;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import Entities.Tree;
import Image.GraphicsManager;
import Util.Pair;
import Main.Questions;
import Text.Writer;

import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.event.MouseEvent;
import java.awt.geom.Line2D;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import javax.swing.JTextPane;
import javax.swing.JButton;
import java.awt.Font;

public class MapDesigner extends JPanel   {

	private JFrame frame;
	private ArrayList<Tree> trees=new ArrayList<>();
	private boolean rec=false;
	private boolean draw= false;
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
	private ArrayList<JMenuItem> items= new ArrayList<>();
	private Questions question = new Questions(this,null);
	private FrameManager f;
	private Writer writer ;
	private int wallHeight;
	private String buildingName;
	private ArrayList<ArrayList<Pair>>listPoints= new ArrayList<>();
	private int wallIndex=1;
	private int worldSize= new File(getClass().getResource("../World").getFile()).listFiles().length+1;
	/**
	 * Create the application.
	 */
	public MapDesigner(FrameManager f) {
		writer= new Writer("world"+worldSize);
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
							// TODO Auto-generated catch block
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
		try {
			writer.create();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(draw) {
					listPoints.get(listPoints.size()-1).add(new Pair(mouseX,mouseY));
					if(listPoints.get(listPoints.size()-1).size()%2==0) {
						try {
							ArrayList<Pair>point=listPoints.get(listPoints.size()-1);
							writer.writeWall(wallIndex++, point.get(point.size()-2), point.get(point.size()-1), wallHeight);
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
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
				mouseX=e.getXOnScreen();
				mouseY=e.getYOnScreen();
			}
		});
		this.setFocusable(true);
		setLayout(null);
		ImageIcon Tree1img= new ImageIcon(getClass().getResource("../Image/Tree1.png"));
		ImageIcon Tree2img= new ImageIcon(getClass().getResource("../Image/Tree2.png"));
		ImageIcon Tree3img= new ImageIcon(getClass().getResource("../Image/Tree3.png"));
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 450, 54);
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
					writer.writeBuilding(question.questionsString("Cual sera el nombre de esta construccion?"));
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				int wallHeight=question.wallHeight();
			}
		});
		menuBar.add(building);

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

	}

	public void paintComponent(Graphics g){
		super.paintComponent(g);  
		this.setBackground(Color.CYAN); 
		Graphics2D g2= (Graphics2D)g;
		g2.setColor(Color.BLACK);
		if(rec) {
			g2.drawRect(mouseX, mouseY, 50, 50);

			xCoor.setText(""+mouseX);
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
}
