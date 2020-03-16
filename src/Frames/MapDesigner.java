package Frames;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JPanel;
import javax.swing.Timer;

import Entities.Tree;
import Image.GraphicsManager;


import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseAdapter;
import javax.swing.JTextField;
import javax.swing.JTextPane;

public class MapDesigner extends JPanel   {

	private JFrame frame;
	private ArrayList<Tree> trees=new ArrayList<>();
	private boolean rec=false;
	private GraphicsManager gm = new GraphicsManager();
	private int mouseX;
	private int mouseY;

	/**
	 * Create the application.
	 */
	public MapDesigner() {
		initialize();
	}
	public void paintComponent(Graphics g){
		super.paintComponent(g);  
		this.setBackground(Color.CYAN); 
		Graphics2D g2= (Graphics2D)g;
		g2.setColor(Color.BLACK);
		if(rec)g2.drawRect(mouseX, mouseY, 50, 50);
		for(int i=0;i<trees.size();i++) {
			int var=trees.get(i).getVar();
			if(var==1)this.gm.drawTree1(trees.get(i), g2, this);
			else if(var==2)this.gm.drawTree2(trees.get(i), g2, this);
			else if(var==3)this.gm.drawTree3(trees.get(i), g2, this);
		}
	}

	/**Carlos Rodriguez 3/15/2020
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		this.setFocusable(true);
		setLayout(null);
		ImageIcon Tree1img= new ImageIcon(getClass().getResource("../Image/Tree1.png"));
		ImageIcon Tree2img= new ImageIcon(getClass().getResource("../Image/Tree2.png"));
		ImageIcon Tree3img= new ImageIcon(getClass().getResource("../Image/Tree3.png"));
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 450, 54);
		add(menuBar);
	
		JTextPane xCoor = new JTextPane();
		xCoor.setBounds(834, 594, 45, 32);
		add(xCoor);
		
		JTextPane yCoor = new JTextPane();
		yCoor.setBounds(921, 594, 45, 32);
		add(yCoor);

		JMenuItem tree1 = new JMenuItem("Tree 1",Tree1img);
		tree1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				rec=false;
				repaint();
				trees.add(new Tree(arg0.getXOnScreen(),arg0.getYOnScreen(),1));
			}
		});
		tree1.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent arg0) {
				rec=true;
				mouseX=arg0.getXOnScreen();
				mouseY=arg0.getYOnScreen();
				xCoor.setText(""+mouseX);
				yCoor.setText(""+mouseY);
				repaint();
			}
		});
		menuBar.add(tree1);
		
		JMenuItem tree2 = new JMenuItem("Tree 2",Tree2img);
		menuBar.add(tree2);
		tree2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				rec=false;
				repaint();
				trees.add(new Tree(arg0.getXOnScreen(),arg0.getYOnScreen(),2));
			}
		});
		tree2.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent arg0) {
				rec=true;
				mouseX=arg0.getXOnScreen();
				mouseY=arg0.getYOnScreen();
				xCoor.setText(""+mouseX);
				yCoor.setText(""+mouseY);
				repaint();
			}
		});
		
		JMenuItem tree3 = new JMenuItem("Tree 3",Tree3img);
		menuBar.add(tree3);
		tree3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				rec=false;
				repaint();
				trees.add(new Tree(arg0.getXOnScreen(),arg0.getYOnScreen(),3));
			}
		});
		tree3.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent arg0) {
				rec=true;
				mouseX=arg0.getXOnScreen();
				mouseY=arg0.getYOnScreen();
				xCoor.setText(""+mouseX);
				yCoor.setText(""+mouseY);
				repaint();
			}
		});
		
		JMenuItem building = new JMenuItem("Building");
		menuBar.add(building);
		
		
        
        
      
	}
}
