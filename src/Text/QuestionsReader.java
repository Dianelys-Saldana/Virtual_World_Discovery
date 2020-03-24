package Text;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner; 
//Carlos Rodriguez 3/12/2020
public class QuestionsReader 
{ 
	ArrayList<String> questions = new ArrayList<>();
	ClassLoader cL = ClassLoader.getSystemClassLoader();
	ArrayList<ArrayList<String>>answer= new ArrayList<ArrayList<String>>();


	/** Carlos Rodriguez 03/13/2020
	 * method to scan the answer and questions on the file
	 */
	public void scan() throws IOException {

		@SuppressWarnings("resource")
		BufferedReader file = new BufferedReader(new FileReader(cL.getResource("Text/Questions.txt").getFile()));
		String line = null;
		Scanner scanner = null;
		int index = 0;
		int ans=0;

		while ((line = file.readLine()) != null) {
			scanner = new Scanner(line);
			answer.add(new ArrayList<String>());
			// we just need to use \\Z as delimiter 
			scanner.useDelimiter(","); 
			while (scanner.hasNext()) {
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

	public ArrayList<ArrayList<String>> getAnswer() {
		return answer;
	}

	public ArrayList<String> getQuestions() {
		return questions;
	} 
} 