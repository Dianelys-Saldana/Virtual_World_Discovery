package Text;

import java.io.File;  // Import the File class
import java.io.IOException;  // Import the IOException class to handle errors

public class CreateFile {
	public CreateFile() {
		
	}
	public void create2() throws IOException {
		int i= 1;
		File myObj = new File("world"+i+".txt");
		while(!myObj.createNewFile()) {
			i++;
			 myObj = new File("world"+i+".txt");
		}


	}
}