package Text;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

import Util.Pair;
/**Created by Carlos Rodriguez 03/23/2020
 * Class created for write files
 */
public class Writer {
	QuestionsReader questionReader= new QuestionsReader();
	File file;
	File questionFile;
	FileWriter myWriter=null;
	FileWriter questionWriter=null;
	String str;
	private boolean started=false;
	
	/**Created by Carlos Rodriguez 03/23/2020
	 * Contructor for the Writer class
	 * @param Str- the name of the file who is going to be write.
	 */
	public Writer(String str) {
		this.str=str;
		try {
			questionReader.scan();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		file = new File("src/World/"+str+".txt");
		questionFile=new File("src/Questions/"+str+"Questions.txt");
		 try {
			myWriter = new FileWriter(file);
			questionWriter = new FileWriter(questionFile);
		} catch (IOException e) {			
			e.printStackTrace();
		}
	}
	
	/**Created by Carlos Rodriguez 03/23/2020
	 * Crea el File si no existe 
	 */
	public void create() throws IOException  {
		if(!file.exists())file.createNewFile();
		if(!questionFile.exists())questionFile.createNewFile();
		}
	
	/**Created by Carlos Rodriguez 03/27/2020
	 * Write the name of the building on the file
	 * @param name the name of the building
	 */
	public void writeBuilding(String name) throws IOException {
		if (started) {
			myWriter.write("\n");
			questionWriter.write("\n");
			questionWriter.write("\n");
		}
		myWriter.write("BuildingName: "+name);
		questionWriter.write("Building: "+name);
		questionWriter.flush();
		this.writeQuestions();
		myWriter.write("\nBuildingImage: "+"ToDo");
		myWriter.flush();
		started=true;
	}
	/**Created by Carlos Rodriguez 03/27/2020
	 * Write the word end on the file
	 */
	public void end() throws IOException {
		myWriter.write("\n");
		myWriter.write("end");
		myWriter.flush();
	}
	/**Created by Carlos Rodriguez 03/27/2020
	 * Write the wall with the atributes in the file 
	 * @param index what wall is  
	 * @param start position of the first point  
	 * @param end position of the last point 
	 * @param height the height of the wall
	 */
	public void writeWall(int index , Pair start, Pair end , int height) throws IOException {
		myWriter.write("\n");
		myWriter.write("Wall"+index+": ");
		myWriter.write("startsAt: ("+start.getX()+","+start.getY()+")");
		myWriter.write(", endsAt: ("+end.getX()+","+end.getY()+")");
		myWriter.write(", height:"+height);
		myWriter.write(", texture image:"+"ToDo");
		myWriter.flush();
		
		
	}
	/**Created by Carlos Rodriguez 03/27/2020
	 * Write the tree with the atributes in the file 
	 * @param x position in x  
	 * @param y position in y
	 * @param var what type of tree is
	 */
	public void writeTree(int x,int y,int var) throws IOException {
		if (started) myWriter.write("\n");
		myWriter.write("TreeType:"+var);
		myWriter.write(", location: ("+x+","+y+")");
		started=true;
		myWriter.flush();
	}
	
	/**Created by Carlos Rodriguez 03/27/2020
	 * Write questions for buildings
	 */
	public void writeQuestions() throws IOException {
		for(int i=0;i<7;i++) {
			Random ran= new Random();
			int num= ran.nextInt(questionReader.getAnswer().size());
			questionWriter.write("\n");
			questionWriter.write("Question: "+questionReader.getQuestions().get(num));
			for(int j=0;j<questionReader.getAnswer().get(num).size();j++) {
				questionWriter.write("\n");
				if(j==0) {
					questionWriter.write("Right: "+questionReader.getAnswer().get(num).get(j));
				}
				else {
					questionWriter.write("Wrong: "+questionReader.getAnswer().get(num).get(j));
				}
			}
			questionWriter.flush();
			
		}
	}
	/**Created by Carlos Rodriguez 03/27/2020
	 * Write questionsFile on the file
	 */
	public void questionFile() throws IOException {
		myWriter.write("QuestionsFile: "+str+"Questions.txt");
		myWriter.flush();
	}
	
	public void writeBackground(String background) throws IOException {
		if (started) myWriter.write("\n");
		myWriter.write(background);
		myWriter.flush();
		started = true;
	}


}
