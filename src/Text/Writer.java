package Text;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

import Util.Pair;

public class Writer {
	QuestionsReader questionReader= new QuestionsReader();
	File file;
	File questionFile;
	FileWriter myWriter=null;
	FileWriter questionWriter=null;
	String str;
	private boolean started=false;
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
	public void create() throws IOException  {
		if(!file.exists())file.createNewFile();
		if(!questionFile.exists())questionFile.createNewFile();
		}
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
//		Wall1: startsAt: (750,350), endsAt: (750,400), height:10, texture image:estefaniN.png
//		Wall2: startsAt: (750,400), endsAt: (800,400), height:10, texture image:estefaniS.png
//		Wall3: startsAt: (800,400), endsAt: (800,350), height:10, texture image:estefaniW.png
//		Wall4: startsAt: (800,350), endsAt: (750,350), height:10, texture image:estefaniE.png
//		end
	}
	public void end() throws IOException {
		myWriter.write("\n");
		myWriter.write("end");
		myWriter.flush();
	}
	
	public void writeWall(int index , Pair start, Pair end , int height) throws IOException {
		myWriter.write("\n");
		myWriter.write("Wall"+index+": ");
		myWriter.write("startsAt: ("+start.getX()+","+start.getY()+")");
		myWriter.write(", endsAt: ("+end.getX()+","+end.getY()+")");
		myWriter.write(", height:"+height);
		myWriter.write(", texture image:"+"ToDo");
		myWriter.flush();
		
		
	}
	public void writeTree(int x,int y,int var) throws IOException {
		if (started) myWriter.write("\n");
		myWriter.write("TreeType:"+var);
		myWriter.write(", location: ("+x+","+y+")");
		started=true;
		myWriter.flush();
	}
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
	public void questionFile() throws IOException {
		myWriter.write("QuestionsFile: "+str+"Questions.txt");
		myWriter.flush();
	}

}
