package Text;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Writer {
	File file;
	FileWriter myWriter=null;
	private boolean started=false;
	public Writer(String str) {
		file = new File("src/World/"+str+".txt");
		 try {
			myWriter = new FileWriter(file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void create() throws IOException  {
		if(!file.exists())file.createNewFile();
		
		
	}
	public void writeTree(int x,int y,int var) throws IOException {
		if (started) myWriter.write("\n");
		myWriter.write("TreeType:"+var);
		myWriter.write(", location: ("+x+","+y+")");
		started=true;
		myWriter.flush();
	}

}
