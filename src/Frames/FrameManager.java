package Frames;

import javax.swing.JFrame;
import java.awt.CardLayout;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Window;


// Dianelys Saldana 03/07/2020
public class FrameManager extends JFrame{
	JLayeredPane layeredPane = new JLayeredPane();
	String songPath = "worry -trash.wav";
	BackgroudMusic song = new BackgroudMusic();
	
	
	public FrameManager() {
		getContentPane().setLayout(null);
		getContentPane().setBounds(0, 0, 1024,735);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		layeredPane.setBounds(0, 0, 1024,735);
		getContentPane().add(layeredPane);
		layeredPane.setLayout(new CardLayout(0, 0));
		
	}
	
	public void menu() {
		BackgroudMusic song = new BackgroudMusic();
		layeredPane.removeAll();
		layeredPane.add(new InitialMenu(this));
		layeredPane.repaint();
		layeredPane.revalidate();
		this.setVisible(true);
		song.music(songPath);
	}
	
	public void player() {
		layeredPane.removeAll();
		layeredPane.add(new PlayerInterface(this));
		layeredPane.repaint();
		layeredPane.revalidate();
		this.setVisible(true);
	}
	
	public void map() {
		layeredPane.removeAll();
		layeredPane.add(new MapDesigner(this));
		layeredPane.repaint();
		layeredPane.revalidate();
		this.setVisible(true);
	}
	
	
}


