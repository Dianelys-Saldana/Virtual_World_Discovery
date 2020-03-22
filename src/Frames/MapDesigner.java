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

import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import java.awt.event.MouseEvent;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseAdapter;
import javax.swing.JTextPane;

public class MapDesigner extends JPanel   {

	private JFrame frame;
	private ArrayList<Tree> trees=new ArrayList<>();
	private boolean rec=false;
	private boolean draw= false;
	private GraphicsManager gm = new GraphicsManager();
	private int mouseX;
	private int mouseY;
	private ArrayList<Pair> point = new ArrayList<>();
	private Color neutralColor;
	private int tree ;
	private JTextPane xCoor = new JTextPane();
	private JTextPane yCoor = new JTextPane();
	private JMenuItem tree1;
	private JMenuItem tree2;
	private JMenuItem tree3;
	private JMenuItem building;
	private ArrayList<JMenuItem> items= new ArrayList<>();
	private Questions question = new Questions(this,null);

	/**
	 * Create the application.
	 */
	public MapDesigner() {
		items.add(tree1);
		items.add(tree2);
		items.add(tree3);
		items.add(building);

		initialize();
	}


	/**Carlos Rodriguez 3/15/2020
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(draw) {
					point.add(new Pair(mouseX,mouseY));
				}
				else if(rec) {
					trees.add(new Tree(mouseX,mouseY,tree));
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

		tree1 = new JMenuItem("Tree 1",Tree1img);
		tree1.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				draw=false;
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
				draw=false;
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
				draw=false;
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
				rec=false;
				neutralColor= building.getBackground();
				draw=true;

				resetColor(3);

				repaint();
				question.wallHeight();
			}
		});


		menuBar.add(building);





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
		for(int i=0;i<point.size();i++) {
			g2.drawOval(point.get(i).getX(), point.get(i).getY(), 10, 10);
		}
		for(int i= point.size()-1;i>0;i--) {
			g2.draw(new Line2D.Double(point.get(i - 1).getX(), point.get(i - 1).getY(),point.get(i).getX(),point.get(i).getY()));
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
	}
}
