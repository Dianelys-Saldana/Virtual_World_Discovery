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
	ArrayList<String> questions = new ArrayList<>();
	ClassLoader cL = ClassLoader.getSystemClassLoader();
	ArrayList<ArrayList<String>>answer= new ArrayList<ArrayList<String>>();
	ArrayList<Building> builds = new ArrayList<>();
	ArrayList<Tree>trees= new ArrayList<>();
	ArrayList<Pair>points= new ArrayList<>();

	/** Carlos Rodriguez 03/13/2020
	 * method to scan the answer and questions on the file
	 */
	public void scan(String s) throws IOException {

		@SuppressWarnings("resource")
		BufferedReader file = new BufferedReader(new FileReader("src/World/"+s+".txt"));
		String line = null;
		Scanner scanner = null;
		int index = 0;
		boolean isTree=false;
		int pos = 0;
		int xpos=0;
		Tree tempTree=new Tree();
		while ((line = file.readLine()) != null) {
			
			scanner = new Scanner(line);
			scanner.useDelimiter(","); 
			while (scanner.hasNext()) {
				String data = scanner.next();
				if(data.equals("Tree"))isTree=true;
				if(pos==0) {
					if(isTree) {
						trees.add(new Tree());
						pos++;
					}
					else {
					builds.add(new Building(new ArrayList<Pair>()));
					pos++;
					}
				}
				else if(pos==1) {
					if (index==0) {

					}
					else if (index == 1) {
						if(isTree)tempTree.setX(Integer.parseInt(data));
						else if(data.equals("end")) pos++;	
						else xpos=Integer.parseInt(data);
						
					}
					else {
						if(isTree) {
							tempTree.setY(Integer.parseInt(data));
							pos++;
						}
						else if(data.equals("end")) pos++;
						else {
							points.add(new Pair(xpos,Integer.parseInt(data)));
							index=0;
						}
					}
					index++;
				}
				else if(pos==2) {
					if (index==0) {

					}
					else if (index == 1) {
						if(isTree)tempTree.setVar(Integer.parseInt(data));
						else builds.get(builds.size()-1).setImage(data);;
						pos++;
					}
					index++;
				}
				else {
					if(!data.equals("end")) {
						if (index == 0) {
							questions.add(data);
							answer.add(new ArrayList<String>());
						}
						else {
							answer.get(answer.size()-1).add(data);
							
						}
						index++;
					}
					else {
						if(isTree) {
							trees.add(new Tree((int)tempTree.getX(),(int)tempTree.getY(),tempTree.getVar()));
							pos=0;
							isTree=false;
						}
						else {
						builds.get(builds.size()-1).setPoint((ArrayList<Pair>) points.clone());
						builds.get(builds.size()-1).setAnswer((ArrayList<ArrayList<String>>) answer.clone());
						builds.get(builds.size()-1).setQuestions((ArrayList<String>) questions.clone());;
						points.clear();
						answer.clear();
						questions.clear();
						pos=0;
						}
					}
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
}