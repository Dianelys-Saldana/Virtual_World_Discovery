package Text;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Writer {
	File file;
	public Writer(String str) {
		file = new File("src/World/"+str+".txt");
	}
	public void create() throws IOException  {
		if(!file.exists())file.createNewFile();
		FileWriter myWriter = new FileWriter(file);
		myWriter.write("Juan");
		myWriter.close();
		





	}
}
