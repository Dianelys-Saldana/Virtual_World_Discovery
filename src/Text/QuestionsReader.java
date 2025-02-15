package Text;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import javax.swing.JOptionPane;

import Entities.Building; 
//Carlos Rodriguez 3/12/2020
public class QuestionsReader 
{ 
	ArrayList<String> questions = new ArrayList<>();
	ClassLoader cL = ClassLoader.getSystemClassLoader();
	ArrayList<ArrayList<String>>answer= new ArrayList<ArrayList<String>>();


	/** Carlos Rodriguez 03/13/2020
	 * Method to scan the answer and questions on the file of posible questions
	 */
	public void scan() throws IOException {

		@SuppressWarnings("resource")
		BufferedReader file = new BufferedReader(new FileReader(cL.getResource("Text/Questions.txt").getFile()));//file with the data
		String line = null;
		Scanner scanner = null;//instance of Scanner class
		int index = 0;//index of data in one line 
		int ans=0;// index of answers

		while ((line = file.readLine()) != null) {//stop when is nothing more on the file
			scanner = new Scanner(line);
			answer.add(new ArrayList<String>());
			scanner.useDelimiter(","); // when the scanner stop in a single line
			while (scanner.hasNext()) {// used to read a single line
				String data = scanner.next();
				if (index == 0)
					questions.add(data);
				else {
					answer.get(ans).add(data);
				}
				index++;
			}
			ans++;
			index = 0;

		}
	}
	/** Carlos Rodriguez 03/28/2020
	 * Method to put the questions on buildings from the file 
	 * @param s the name of the file
	 * @param arr the buildings
	 */
	public void worldScan(String s, ArrayList<Building> arr) throws IOException {

		@SuppressWarnings("resource")
		BufferedReader file = new BufferedReader(new FileReader("src/Questions/"+s+"Questions.txt"));
		String line = null;
		Scanner scanner = null;
		int index = 0;
		int buildingIndex=-1;
		while ((line = file.readLine()) != null) {
			scanner = new Scanner(line);
			answer.add(new ArrayList<String>());
			scanner.useDelimiter(":"); 
			boolean isBuilding=false;
			boolean isQuestion=false;


			while (scanner.hasNext()) {

				String data = scanner.next();
				if(data.equals("Building")) {
					buildingIndex++;
					isBuilding=true;
				}
				else if(data.equals("Question")) {
					isQuestion=true;
				}

				else if(isBuilding) {
					isBuilding=false;
				}
				else if(isQuestion) {

					arr.get(buildingIndex).getQuestions().add(data);
					arr.get(buildingIndex).getAnswer().add(new ArrayList<String>() );
					isQuestion=false;

				}

				else {
					if(index==1) {
						
						arr.get(buildingIndex).getAnswer().get(arr.get(buildingIndex).getAnswer().size()-1).add(data);
					}
					index++;
				}

			}
			index = 0;

		}
	}
	

	public ArrayList<ArrayList<String>> getAnswer() {
		return answer;
	}

	public ArrayList<String> getQuestions() {
		return questions;
	} 
} 