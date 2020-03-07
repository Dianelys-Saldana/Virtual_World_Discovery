package Main;


import javax.swing.JFrame;

import Frames.InitialMenu;
import Frames.PlayerInterface;

public class Launch {
	public static void main(String[] args)
	{
		JFrame F = new JFrame();
		F.add(new InitialMenu());
		F.setSize(1460,735);
		 
		F.setVisible(true);
		F.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
