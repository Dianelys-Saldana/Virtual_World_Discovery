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
import java.util.ArrayList;
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
	private boolean started=true;
	
	/**Created by Carlos Rodriguez 03/23/2020
	 * Contructor for the Writer class
	 * @param Str- the name of the file who is going to be write.
	 */
	public Writer(String str) {
		this.str=str;
		try {
			questionReader.scan();
		} catch (IOException e1) {
			 
			e1.printStackTrace();
		}
		file = new File("src/World/"+str+".txt");
		questionFile=new File("src/Questions/"+str+"Questions.txt");
		 try {
			myWriter = new FileWriter(file,true);
			questionWriter = new FileWriter(questionFile,true);
		} catch (IOException e) {			
			e.printStackTrace();
		}
	}
	
	/**Created by Carlos Rodriguez 03/23/2020
	 * Crea el File si no existe 
	 */
	public void create() throws IOException  {
		if(!file.exists()) {
			file.createNewFile();
			started=false;
		}
		if(!questionFile.exists())questionFile.createNewFile();
		}
	
	/**Created by Carlos Rodriguez 03/27/2020
	 * Write the name of the building on the file
	 * @param name the name of the building
	 */
	public void writeBuilding(String name,String image) throws IOException {
		if (started) {
			myWriter.write("\n");
			questionWriter.write("\n");
			questionWriter.write("\n");
		}
		myWriter.write("BuildingName: "+name);
		questionWriter.write("Building: "+name);
		questionWriter.flush();
		this.writeQuestions();
		myWriter.write("\nBuildingImage: "+image);
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
	public void writeWall(int index , Pair start, Pair end , int height,String wallImage) throws IOException {
		myWriter.write("\n");
		myWriter.write("Wall"+index+": ");
		myWriter.write("startsAt: ("+start.getX()+","+start.getY()+")");
		myWriter.write(", endsAt: ("+end.getX()+","+end.getY()+")");
		myWriter.write(", height:"+height);
		myWriter.write(", texture image:"+wallImage);
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
		ArrayList<Integer> noPosibles = new ArrayList<>();
		noPosibles.add(100);
		for(int i=0;i<7;i++) {
			Random ran= new Random();
			
			int num= ran.nextInt(questionReader.getAnswer().size());
			while(noPosibles.contains(num)) {
				 num= ran.nextInt(questionReader.getAnswer().size());
			}
			noPosibles.add(num);
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
		myWriter.write("\n");
		myWriter.write("QuestionsFile: "+str+"Questions.txt");
		myWriter.flush();
	}
	
	public void writeBackground(String background) throws IOException {
		myWriter.write("\n");
		myWriter.write(background);
		myWriter.flush();
		started = true;
	}
	//Used for copy one file to other, create a new copy
	//Carlos Rodriguez 04/10/2020
	public void copy(File input) throws IOException {
		  InputStream is = null;
	      OutputStream os = null;
	        try {
	        	
	            is = new FileInputStream(input);
	            os = new FileOutputStream(new File("src/Image/"+input.getName()));
	            byte[] buffer = new byte[1024];
	            int length;
	            while ((length = is.read(buffer)) > 0) {
	                os.write(buffer, 0, length);
	            }
	        } finally {
	            is.close();
	            os.close();
	        }
	}
	//Used for delete lines from one file
	//Carlos Rodriguez 04/10/2020
	public void deleteLine(String s) throws IOException {
		File inputFile = file;
		File tempFile = new File("Copy.txt");
		
		BufferedReader reader = new BufferedReader(new FileReader(inputFile));
		BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
		String lineToRemove = s;
		String currentLine;

		while((currentLine = reader.readLine()) != null) {
		    // trim newline when comparing with lineToRemove
		    String trimmedLine = currentLine.trim();
		    if(trimmedLine.equals(lineToRemove)) {
		    	continue;
		    }
		    writer.write(currentLine + System.getProperty("line.separator"));
		}
		writer.flush();
		this.myWriter= new FileWriter(file, false); //overwrites file
		reader=new BufferedReader(new FileReader(tempFile));
		while((currentLine = reader.readLine()) != null) {
		    myWriter.write(currentLine + System.getProperty("line.separator"));
		}
		myWriter.flush();
		reader.close();
		writer.close();
		tempFile.delete();
	    
	    
		
	}
	//Used for delete lines from one file if contains the String
	//Carlos Rodriguez 04/22/2020
		public void deleteLineifContains(String s) throws IOException {
			File inputFile = file;
			File tempFile = new File("Copy.txt");
			
			BufferedReader reader = new BufferedReader(new FileReader(inputFile));
			BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
			String lineToRemove = s;
			String currentLine;

			while((currentLine = reader.readLine()) != null) {
			    // trim newline when comparing with lineToRemove
			    String trimmedLine = currentLine.trim();
			    if(trimmedLine.contains(lineToRemove)) {
			    	continue;
			    }
			    writer.write(currentLine + System.getProperty("line.separator"));
			}
			writer.flush();
			this.myWriter= new FileWriter(file, false); //overwrites file
			reader=new BufferedReader(new FileReader(tempFile));
			while((currentLine = reader.readLine()) != null) {
			    myWriter.write(currentLine + System.getProperty("line.separator"));
			}
			myWriter.flush();
			reader.close();
			writer.close();
			tempFile.delete();
		    
		    
			
		}
	//Used for delete many lines on a File
	//Carlos Rodriguez 4/21/2020
	public void deleteLinesFromFile(String s) throws IOException {
		File inputFile = file;
		File tempFile = new File("Copy.txt");
		boolean found =false;
		BufferedReader reader = new BufferedReader(new FileReader(inputFile));
		BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
		String lineToRemove = s;
		String currentLine;

		while((currentLine = reader.readLine()) != null) {
		    // trim newline when comparing with lineToRemove
		    String trimmedLine = currentLine.trim();
		    if(trimmedLine.equals(lineToRemove)) {
		    	found=true;
		    }
		    else if(found) {
		    	if(trimmedLine.equals("end"))found= false;
		    	continue;
		    }
		    else writer.write(currentLine + System.getProperty("line.separator"));
		}
		writer.flush();
		this.myWriter= new FileWriter(file, false); //overwrites file
		reader=new BufferedReader(new FileReader(tempFile));
		while((currentLine = reader.readLine()) != null) {
		    myWriter.write(currentLine + System.getProperty("line.separator"));
		}
		myWriter.flush();
		reader.close();
		writer.close();
		tempFile.delete();
		
		
	}
	
	//Used for delete many lines on a Question File
	//Carlos Rodriguez 4/21/2020
	public void deleteLinesFromQuestionFile(String s) throws IOException {
		File inputFile = questionFile;
		File tempFile = new File("Copy.txt");
		boolean found =false;
		BufferedReader reader = new BufferedReader(new FileReader(inputFile));
		BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
		String lineToRemove = s;
		String currentLine;

		while((currentLine = reader.readLine()) != null) {
		    // trim newline when comparing with lineToRemove
		    String trimmedLine = currentLine.trim();
		    if(trimmedLine.equals(lineToRemove)) {
		    	found=true;
		    }
		    else if(found) {
		    	if(trimmedLine.equals(""))found= false;
		    	continue;
		    }
		    else writer.write(currentLine + System.getProperty("line.separator"));
		}
		writer.flush();
		this.questionWriter= new FileWriter(questionFile, false); //overwrites file
		reader=new BufferedReader(new FileReader(tempFile));
		while((currentLine = reader.readLine()) != null) {
		    questionWriter.write(currentLine + System.getProperty("line.separator"));
		}
		questionWriter.flush();
		reader.close();
		writer.close();
		tempFile.delete();
		
		
	}
	//Used for search if a line exist in a file
	//Carlos Rodriguez 4/21/2020
	public boolean lineExist(String s) throws IOException {
		File inputFile = file;
		
		BufferedReader reader = new BufferedReader(new FileReader(inputFile));
		String lineToSearch = s;
		String currentLine;

		while((currentLine = reader.readLine()) != null) {
		    // trim newline when comparing with lineToRemove
		    String trimmedLine = currentLine.trim();
		    if(trimmedLine.equals(lineToSearch)) return true;
		    
		}
		return false;
		
	}

	public FileWriter getMyWriter() {
		return myWriter;
	}

	public void setMyWriter(FileWriter myWriter) {
		this.myWriter = myWriter;
	}
	


}
