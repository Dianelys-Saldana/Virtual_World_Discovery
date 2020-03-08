package Main;

import java.awt.Component;

import javax.swing.JOptionPane;
/** Carlos Rodriguez 3/8/2020
 * Class for make the questions on the PlayerInterface
 */
public class Questions {
	private Component c;

	public Questions(Component C) {
		this.c=C;
	}
	/** Carlos Rodriguez 3/8/2020
	 * method to answer yes or no questions
	 * @param s - question
	 */
	public int basic( String s) {
		int a=JOptionPane.showConfirmDialog(c,s);
		return a;
	}
	/** Carlos Rodriguez 3/8/2020
	 * method to answer a specific question
	 */
	public int question1() {
		String[] options = new String[] {"2020", "1212", "33", "200"};
		int response = JOptionPane.showOptionDialog(c, "2021-1", "Title",
				JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
				null, options, options[0]);
		return response;
	}
	/** Carlos Rodriguez 3/8/2020
	 * method to answer a specific questions
	 * @return True if there are more than 3 correct answers
	 */
	public boolean visible() {
		int x=0;
		if(this.basic("Quieres contestar unas preguntas")==JOptionPane.YES_OPTION) {
			if(question1()==0)  x++;
			if(question1()==0)  x++;
			if(question1()==0)  x++;
			if(question1()==0)  x++;
			if(x>=3)return true;
		}
		return false;
	}
}
