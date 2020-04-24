package Text;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import Util.Pair;

/**Created by Dianelys Saldana 04/23/2020
 * Class created for writing VRML files
 */
public class WriterVRML {
	File file;
	FileWriter myWriter = null;
	String str;
	private boolean started = true;

	/**
	 * Contructor for the Writer class
	 * @param Str- the name of the file who is going to be write.
	 */
	public WriterVRML(String str) {
		this.str = str;
		file = new File("src/VRML_Worlds/" + str + ".wrl");
		try {
			myWriter = new FileWriter(file,true);
		} catch (IOException e) {			
			e.printStackTrace();
		}
	}

	/**
	 * Crea el File si no existe uno ya
	 */
	public void create() throws IOException  {
		if(!file.exists()) {
			file.createNewFile();
			started = false;
		}
	}
	
	public FileWriter getMyWriter() {
		return myWriter;
	}

	public void setMyWriter(FileWriter myWriter) {
		this.myWriter = myWriter;
	}

}

