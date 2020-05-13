
package Text;



import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import Entities.Building;
import Entities.Tree;
import Util.Pair; 
//Carlos Rodriguez 3/12/2020
public class Reader 
{ 
	ArrayList<String> questions = new ArrayList<>();//Lists of Questions
	ArrayList<ArrayList<String>>answer= new ArrayList<ArrayList<String>>();// Lists of Answers
	ArrayList<Building> builds = new ArrayList<>();//Lists of Buildings
	ArrayList<Tree>trees= new ArrayList<>();// Lists of Trees
	ArrayList<Pair>points= new ArrayList<>();// Lists of points 
	private String questionsFile;// reference of the question file
	String background = "MapDefault.png";//Background Choose

	/** Carlos Rodriguez 03/27/2020
	 * Method to scan the world on a file 
	 * @param s the name of the file
	 */
	@SuppressWarnings("unchecked")
	public void scan(String s) throws IOException {

		@SuppressWarnings("resource")
		BufferedReader file = new BufferedReader(new FileReader("src/World/"+s+".txt"));//file to read
		points.clear();
		String line = null;
		Scanner scanner = null;
		int index = 0;//used to move horizontally 
		boolean isTree=false;// used for know if the target is a tree
		boolean qF=false;// used for know if the target is a tree
		int pos = 0;// used to move vertically
		int xPoint=0;// reference of the x position form a point
		Tree tempTree=new Tree();
		boolean isBackground=false;
		while ((line = file.readLine()) != null) {

			scanner = new Scanner(line);
			scanner.useDelimiter(",|:"); //delimiter used
			while (scanner.hasNext()) {
				String data = scanner.next();
				if(data.equals("QuestionsFile")) {
					qF=true;
					continue;
				}
				if(data.equals("TreeType"))isTree=true;
				if(qF)questionsFile=data;
				//Work With Trees
				else if(isTree) {
					if(index==0) {


					}
					else if(index == 1) {
						tempTree.setVar(Integer.parseInt(data));


					}
					else if(index==3) {
						String replace=data.substring(2);
						tempTree.setX(Integer.parseInt(replace));

					}
					else if(index==4) {
						String replace=data.substring(0, data.length() - 1);
						tempTree.setY(Integer.parseInt(replace));
						trees.add(new Tree((int)tempTree.getX(),(int)tempTree.getY(),tempTree.getVar()));
						isTree=false;
					}
					index++;
				}

				// Angel Hernandez 04/24/2020
				// Reads the selected background from the file
				else if(data.equals("Background")) {
					isBackground=true;
					
				}
				else if(isBackground) {
					String replace=data.substring(1);
					background = replace;
					isBackground=false;
				}


				//Work with Buildings
				else if(pos==0) {

					if(index==0) {
						builds.add(new Building(new ArrayList<Pair>()));
						index++;
					}
					else if(index==1) {
						String replace=data.substring(1, data.length());
						builds.get(builds.size()-1).setName(replace);
						pos++;
						index=0;
					}

				}
				else if(pos==1) {
					if (index==0) {
						index++;
					}
					else if (index == 1) {
						String replace=data.substring(1, data.length());
						builds.get(builds.size()-1).setImage(replace);
						pos++;
						index=0;
					}
				}
				else if(pos==2) {
					if(data.equals("end")) {
						builds.get(builds.size()-1).setPoint((ArrayList<Pair>) points.clone());
						points.clear();
						pos=0;
					}
					else if(index==2) {

						String replace=data.substring(2);

						xPoint= Integer.parseInt(replace);

					}
					else if(index==3) {
						String replace=data.substring(0, data.length() - 1);
						points.add(new Pair(xPoint,Integer.parseInt(replace)));
					}
					else if(index==5) {
						String replace=data.substring(2);

						xPoint= Integer.parseInt(replace);
					}
					else if(index==6) {
						String replace=data.substring(0, data.length() - 1);
						points.add(new Pair(xPoint,Integer.parseInt(replace)));
					}
					else if(index==8) {

						builds.get(builds.size()-1).setHeight(Integer.parseInt(data));
					}
					else if(index==10){
						String replace=data.substring(1, data.length() - 1);
						builds.get(builds.size()-1).getWallsImage().add(replace);

					}
					index++;
				}


			}

			index = 0;

		}

	}

	public ArrayList<Tree> getTrees() {
		return trees;
	}

	public ArrayList<ArrayList<String>> getAnswer() {
		return answer;
	}

	public ArrayList<Building> getBuildings() {
		return builds;
	}

	public String getBackground() {
		return background;
	}

}