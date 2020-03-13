package Main;

import java.io.IOException;
import Frames.FrameManager;
/**Dianelys Saldana 03/07/2020
** Class for launching the program
*/
public class Launch {
	public static void main(String[] args) throws IOException
	{
//		JFrame F = new JFrame();
//		F.add(new InitialMenu());
//		F.setSize(1024,735);
//		F.setVisible(true);
//		F.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		FrameManager f = new FrameManager();
		f.frame();
		
	}
}
