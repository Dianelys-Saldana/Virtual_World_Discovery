package Text;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import Util.Pair;

public class Writer {
	File file;
	FileWriter myWriter=null;
	private boolean started=false;
	public Writer(String str) {
		file = new File("src/World/"+str+".txt");
		 try {
			myWriter = new FileWriter(file);
		} catch (IOException e) {			
			e.printStackTrace();
		}
	}
	public void create() throws IOException  {
		if(!file.exists())file.createNewFile();
		}
	public void writeBuilding(String name) throws IOException {
		if (started) myWriter.write("\n");
		myWriter.write("BuildingName: "+name);
		myWriter.write("\nBuildingImage: ");
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
		myWriter.write("startsAt: ("+start.getX()+","+start.getX()+")");
		myWriter.write(", endsAt: ("+end.getX()+","+end.getX()+")");
		myWriter.write(" height:"+height);
		myWriter.write(" texture image:");
		myWriter.flush();
		
		
	}
	public void writeTree(int x,int y,int var) throws IOException {
		if (started) myWriter.write("\n");
		myWriter.write("TreeType:"+var);
		myWriter.write(", location: ("+x+","+y+")");
		started=true;
		myWriter.flush();
	}

}
