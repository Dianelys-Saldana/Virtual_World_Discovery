package Main;

import java.io.IOException;
//import Frames.FrameManager;
import Frames.FrameManager;

/**Dianelys Saldana 03/07/2020
** Class for launching the program
*/
public class Launch {
	public static void main(String[] args) throws IOException
	{
		FrameManager f = new FrameManager();
		f.menu();
		f.setVisible(true);
		f.setBounds(0, 0, 1024,735);
		
	}
}
