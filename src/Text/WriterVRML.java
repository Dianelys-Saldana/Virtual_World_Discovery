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
	
	// Metodo que comienza el file
	public void writeFile() throws IOException {
		myWriter.write("#VRML V2.0 utf8"
				+ "\nNavigationInfo {\n"  // TODO: Check
				+ "  headlight TRUE\n"  
				+ "  avatarSize [0.3 1.6 0.5]\n"  
				+ "}");
		myWriter.write("\nTransform {\n" + 
				"	translation 0 0 0\n" + 
				"	children [");
		this.writeFloor();
		myWriter.flush();
	}
	
	public void writeWall(String x, String y, String url) throws IOException {
		myWriter.write("\n#Wall" + 
				"\nTransform {\n" + 
				"	translation 0 0 0\n" + // TODO: Change params
				"	#rotation 0 1 0 1.57\n" + 
				"	children [\n" + 
				"		Shape {\n" + 
				"			appearance Appearance {\n" + 
				"				material Material {\n" + 
				"					diffuseColor 1 0 0\n" + 
				"				}\n" + 
				"				texture ImageTexture {\n" + 
				"					url \"" + url + "\"\n" +  
				"				}\n" + 
				"			}\n" + 
				"			geometry Box {\n" + 
				"			  size " + x + " " + y + " 0.1\n" + 
				"			}\n" + // TODO: x = largo, y = height
				"		}\n" + 
				"	]\n" + 
				"}");
		myWriter.flush();
	}
	
	public void writeTree(String x, String y, String url) throws IOException {
		myWriter.write("\n#Tree\n" + 
				"Transform{\n" + 
				"	translation -5 2 5\n" + // TODO: Add params
				"	children[\n" + 
				"		Billboard {\n" + 
				"			children [\n" + 
				"				Shape {\n" + 
				"					appearance Appearance {\n" + 
				"						texture ImageTexture {\n" + 
				"							url \"" + url + ".png\"\n" + 
				"						}\n" + 
				"					}\n" + 
				"					geometry Box {\n" + 
				"						size " + x + " " + y + " 0.0001\n" + 
				"					}\n" + // TODO: x = largo, y = height
				"				}\n" + 
				"			]\n" + 
				"		}	\n" + 
				"	]\n" + 
				"}");
		myWriter.flush();
	}
	
	// TODO
	public void findTree() { // Para eliminar arbol
		
	}
	
	public void findBuilding() { // Para eliminar edificios
		
	}
	
	public void writeFloor() throws IOException {
		myWriter.write("\n#Floor\n" + 
		"Transform {\n" + 
		"	translation 0 0 0\n" + 
		"	children [\n" + 
		"		Shape {\n" + 
		"			appearance Appearance {\n" + 
		"				material Material {\n" + 
		"					diffuseColor 0 0 0\n" + 
		"					emissiveColor  0 0 0\n" + 
		"				}\n" + 
		"				texture ImageTexture {\n" + 
		"					url \"clearGrassTexture.png\"\n" + // TODO: Change Image
		"				}\n" + 
		"			}\n" + 
		"			geometry Box {\n" + 
		"			  size 100 0.1 100\n" +  // TODO: Change size of map
		"			}\n" + 
		"		}\n" + 
		"	]\n" + 
		"}");
		myWriter.flush();
	}
	
	public double length(Pair start, Pair end) {
		int x1 = start.getX(), y1 = start.getY(), x2 = end.getX(), y2 = end.getY();
		double result = Math.sqrt(Math.pow((x2-x1), 2) + Math.pow((y2-y1), 2));
		return result;
	}
	
	public void end() throws IOException {
		myWriter.write("	\n]" + 
				"\n}");
		myWriter.flush();
	}
	
	public FileWriter getMyWriter() {
		return myWriter;
	}

	public void setMyWriter(FileWriter myWriter) {
		this.myWriter = myWriter;
	}

}

