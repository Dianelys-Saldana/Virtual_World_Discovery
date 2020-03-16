package Text;

import java.io.FileWriter;
import java.io.IOException;

public class Writer {
	public Writer() {
		
	}
	public void writer() throws IOException {
		FileWriter myWriter = new FileWriter("world1.txt");
	    myWriter.write("Files in Java might be tricky, but it is fun enough!");
	    myWriter.close();
	}
}
