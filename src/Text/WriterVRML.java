package Text;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import Util.Pair;

/**Created by Dianelys Saldana 04/23/2020
 * Class created for writing VRML files
 */
public class WriterVRML {
	File file;
	FileWriter myWriter = null;
	String str;
	private boolean started = true;
	Writer writer = new Writer("");

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
	
	/**
	 * Encabezado del File de VRML
	 */
	public void writeFile() throws IOException {
		myWriter.write("#VRML V2.0 utf8"
				+ "\nNavigationInfo {\n"  
				+ "  headlight TRUE\n"  
				+ "  avatarSize [0.3 1.6 0.5]\n"  
				+ "}");
		this.writeFloor("MapDefault.png");
		myWriter.flush();
	}
	
	/**
	 * Metodo para dibujar paredes
	 * @param x = length, y = height, r = rotation angle, a = height translation
	 *  tx = x translation, tz = z translation, Image url
	 */
	public void writeWall(String x, String y, String r, String a, String tx, String tz, String url) throws IOException {
		myWriter.write("\n#Wall" + 
				"\nTransform {\n" + 
				"	translation " + tx + " " + a + " " + tz + "\n" + 
				"	rotation 0 1 0 " + r + "\n" + 
				"	children [\n" + 
				"		Shape {\n" + 
				"			appearance Appearance {\n" +
				"				texture ImageTexture {\n" + 
				"					url \"" + url + "\"\n" +  
				"				}\n" + 
				"			}\n" + 
				"			geometry Box {\n" + 
				"			  size " + x + " " + y + " 0.1\n" + 
				"			}\n" + 
				"		}\n" + 
				"	]\n" + 
				"}");
		myWriter.flush();
	}
	
	/**
	 * Metodo para dibujar arboles
	 */
	public void writeTree(String x, String y, int url) throws IOException {
		myWriter.write("\n#Tree\n" + 
				"Transform{\n" + 
				"	translation " + x + " 2 " + y + "\n" +
				"	children[\n" + 
				"		Billboard {\n" + 
				"			children [\n" + 
				"				Shape {\n" + 
				"					appearance Appearance {\n" + 
				"						texture ImageTexture {\n" + 
				"							url \"Tree" + url + ".png\"\n" + 
				"						}\n" + 
				"					}\n" + 
				"					geometry Box {\n" + 
				"						size 5 4 0.0001\n" +
				"					}\n" + 
				"				}\n" + 
				"			]\n" + 
				"		}	\n" + 
				"	]\n" + 
				"}");
		myWriter.flush();
	}
	
	/**
	 * Metodo para dibujar piso del mapa
	 */
	public void writeFloor(String url) throws IOException {
		myWriter.write("\n#Floor\n" + 
		"Transform {\n" + 
		"	children [\n" + 
		"		Shape {\n" + 
		"			appearance Appearance {\n" +
		"				texture ImageTexture {\n" + 
		"					url \"" + url + "\n" + 
		"				}\n" + 
		"			}\n" + 
		"			geometry Box {\n" + 
		"			  size 1024 0.1 735\n" +  
		"			}\n" + 
		"		}\n" + 
		"	]\n" + 
		"}");
		myWriter.flush();
	}
	
	// Dianelys Saldana 04/29/2020
	/**
	 * Metodo para hallar longitud de pared usando formula de distancia
	 */
	public double length(Pair start, Pair end) {
		double x1 = start.getX(), y1 = start.getY(), x2 = end.getX(), y2 = end.getY();
		return Math.sqrt(Math.pow((x2-x1), 2) + Math.pow((y2-y1), 2));
	}
	
	/**
	 * Metodo para encontrar angulo de rotacion
	 */
	public double angle(Pair start, Pair end) {
		double x1 = start.getX(), y1 = start.getY(), x2 = end.getX(), y2 = end.getY();
		return Math.atan((y2-y1)/(x2-x1));
	}
	
	/**
	 * Metodo para hallar traslacion en x
	 */
	public double xTrans(Pair start, Pair end) {
		double x1 = start.getX(), x2 = end.getX();
		double result = (x1 + x2)/2;
		return result - 512;
	}
	
	/**
	 * Metodo para hallar traslacion en z
	 */
	public double zTrans(Pair start, Pair end) {
		double y1 = start.getY(), y2 = end.getY();
		double result = (y1 + y2)/2;
		return result - 367.5;
	}
	
	// TODO
	public void findTree() { // Para eliminar arbol

	}

	public void findBuilding() { // Para eliminar edificios

	}
	
	public FileWriter getMyWriter() {
		return myWriter;
	}

	public void setMyWriter(FileWriter myWriter) {
		this.myWriter = myWriter;
	}

}

