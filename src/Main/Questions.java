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
	private List<String> answer;
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
		int num= ran.nextInt(4);
		answer= build.getAnswer().get(num);
		String correct = answer.get(0);
		String[] options = this.shuffle(answer.toArray());
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
	public void setBuild(Building build) {
		this.build = build;
	}
	
	/** Jose Velazquez 03/21/2020
	 * creates panel to input the height of the wall to be
	 * created 
	 */
	public int wallHeight() {
		String wall_height;
		 wall_height = JOptionPane.showInputDialog("Wall height in meters");
		int height = Integer.parseInt(wall_height);
		return height;
	}
	
	public int arraySelection(Object[] arr, String s) {
		int response = JOptionPane.showOptionDialog(c, s, "Question",
				JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
				null, arr, arr[0]);
		return response;
	}
	
}
