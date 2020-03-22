package Frames;

import javax.swing.JFrame;
import java.awt.CardLayout;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Window;

public class FrameManager extends JFrame{
	JLayeredPane layeredPane = new JLayeredPane();
	private InitialMenu menu = new InitialMenu(this);
	private PlayerInterface player = new PlayerInterface(this);
	private MapDesigner map = new MapDesigner(this);
	
	public FrameManager() {
		getContentPane().setLayout(null);
		getContentPane().setBounds(0, 0, 1024,735);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		layeredPane.setBounds(0, 0, 1024,735);
		getContentPane().add(layeredPane);
		layeredPane.setLayout(new CardLayout(0, 0));
		
		
		layeredPane.add(menu, "name_597514722666874");
		menu.setBounds(0, 0, 1024,735);
		menu.setLayout(null);
		
		
		layeredPane.add(player, "name_597269377797559");
		player.setBounds(0, 0, 1024,735);
		player.setLayout(null);
		
		
		layeredPane.add(map, "name_597355690056223");
		map.setBounds(0, 0, 1024,735);
		map.setLayout(null);
		
	}
	
	public void menu() {
		layeredPane.removeAll();
		layeredPane.add(menu);
		layeredPane.repaint();
		layeredPane.revalidate();
		this.setVisible(true);
	}
	
	public void player() {
		layeredPane.removeAll();
		layeredPane.add(player);
		layeredPane.repaint();
		layeredPane.revalidate();
		this.setVisible(true);
	}
	
	public void map() {
		layeredPane.removeAll();
		layeredPane.add(map);
		layeredPane.repaint();
		layeredPane.revalidate();
		this.setVisible(true);
	}
}
