package Frames;

import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class FrameManager {
	
	JFrame f = new JFrame();
	
	public JPanel getInitial() throws IOException {
		return new InitialMenu();
	}
	
	public JPanel getPlayer() {
		return new PlayerInterface();
	}

	public void frame() throws IOException {
		f.add(this.getPlayer());
		f.add(this.getInitial());
		f.setSize(1024,735);
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void hideInitialFrame() throws IOException {
		f.remove(this.getInitial()); // ?
	}
	
}
