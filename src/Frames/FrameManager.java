package Frames;

import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;

import Main.Questions;

/** Dianelys Saldana 03/08/2020
**	Class to manage frames 
*/
public class FrameManager extends JFrame{
	
	public JPanel getInitial() throws IOException {
		return new InitialMenu(this);
	}
	
	public JPanel getPlayer() {
		return new PlayerInterface();
	}

	public void frame() throws IOException {
		add(this.getInitial());
		setSize(1024,735);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void hideInitialFrame() throws IOException {
		
		this.setVisible(false);
		JFrame j = new JFrame();
		j.add(this.getPlayer());
		j.setSize(1024,735);
		j.setVisible(true);
		j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
}