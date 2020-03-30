package Main;

import java.awt.Component;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import javax.swing.JOptionPane;

import Entities.Building;

/** Carlos Rodriguez 3/8/2020
 ** Class for make the questions on the PlayerInterface
 */
public class Questions {
	private Component c;
	private ArrayList<String> questions;
	private List<String> answers;
	private int correctnum;
	private Building build;
	
	public Questions(Component C, Building build)  {
		this.c=C;
		this.build=build;
		
	}
	
	
	/**Carlos Rodriguez 03/08/2020
	 * Method to answer yes or no questions
	 * @param s - question
	 */
	public int basic( String s) {
		int a=JOptionPane.showConfirmDialog(c,s);
		return a;
	}
	
	/** Carlos Rodriguez 03/08/2020
	 * method to create a random question
	 */
	public int question1() {
		questions= build.getQuestions();
		Random ran= new Random();
		int num= ran.nextInt(build.getAnswer().size());
		answers= build.getAnswer().get(num);
		String correct = answers.get(0);
		String[] options = this.shuffle(answers.toArray());
		correctnum=this.correctAnswer(options, correct);
		int response = JOptionPane.showOptionDialog(c, questions.get(num), "Question",
				JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
				null, options, options[0]);
		return response;
	}
	
	

	/** Carlos Rodriguez 03/08/2020
	 * method to answer a specific questions
	 * @return True if there are more than 3 correct answers
	 */
	public boolean visible() {
		int x=0;
		if(this.basic("Would you like to answer some questions?")==JOptionPane.YES_OPTION) {
			if(question1()==correctnum)  x++;
			if(question1()==correctnum)  x++;
			if(question1()==correctnum)  x++;
			if(question1()==correctnum)  x++;
			if(x>=3)return true;
		}
		return false;
	}
	/** Carlos Rodriguez 03/13/2020
	 * method to shuffle possible answer
	 * @return String of shuffle answer
	 */
	public String[] shuffle(Object[] str) {
		List<Object> strList = Arrays.asList(str);
		Collections.shuffle(strList);
		return strList.toArray(new String[strList.size()]);
	}
	/** Carlos Rodriguez 03/08/2020
	 * search on a string the correct answer
	 * @return Int of the position of the correct answer
	 */
	public int correctAnswer(String[] arr,String str) {
		for (int i = 0; i < arr.length; i++) {
			if(str.equals(arr[i]))return i;
		}	
		return 0;
	}
	
	
	/** Jose Velazquez 03/28/2020
	 * helper method to validate if the value 
	 * enter contains numbers  
	 */
	private boolean hasNum(String s) {
		if(s==null)return false;
		for(char c: s.toCharArray()) {
			if(!Character.isDigit(c)) {
				return false;
			}
		}
		return true;
		
	}
	
	/** Jose Velazquez 03/21/2020
	 * creates panel to input the height of the wall to be
	 * created 
	 */
	public int wallHeight() {
		String wallHeight;
		 wallHeight = JOptionPane.showInputDialog(c,"Wall height in meters");
		int height = -1;
		boolean done = false;;
		while(!done && wallHeight != null) {
				if(wallHeight.equals("-1"))return -1;
				if(!this.hasNum(wallHeight) || wallHeight.equals("")) { 
					 wallHeight = JOptionPane.showInputDialog(c,"Re enter the "
					 		+ "wall height in meters");
				}
				else {
					height = Integer.parseInt(wallHeight);
					done = true;  
				}			
		}
		return height;
	}
	/** Carlos Rodriguez 03/08/2020
	 * Create panel to choose an answer
	 * @return Int of the position of the  answer
	 */
	public int arraySelection(Object[] arr, String s) {
		int response = JOptionPane.showOptionDialog(c, s, "Question",
				JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
				null, arr, arr[0]);
		return response;
	}
	/** Carlos Rodriguez 03/08/2020
	 * Create panel with a Specific questions
	 * @param question the question on the panel
	 * @return String the answer
	 */
	public String questionsString(String question) {
		 return JOptionPane.showInputDialog(c,question);
		
	}
	public void setBuild(Building build) {
		this.build = build;
	}
	
}
