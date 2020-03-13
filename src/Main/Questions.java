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

import Text.Readers;

/** Carlos Rodriguez 3/8/2020
 ** Class for make the questions on the PlayerInterface
 */
public class Questions {
	private Component c;
	private ArrayList<String> questions;
	private List<String> answer;
	private Readers r= new Readers();
	private int correctnum;
	public Questions(Component C)  {
		this.c=C;
		try {
			r.scan();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		questions= r.getQuestions();
		
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
		Random ran= new Random();
		int num= ran.nextInt(32);
		answer= r.getAnswer().get(num);
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
	
}