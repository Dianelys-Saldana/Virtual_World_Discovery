package Entities;

import java.awt.Rectangle;
import java.awt.geom.Line2D;
import java.util.ArrayList;

import Util.Pair;

/**Carlos Rodriguez 03/24/2020
** Here we manage the building entities
*/
public class Building  {
	private int visible; // variable to decide if the building is visible, 0 - not founded, 1 - founded but not question answer, 2- full visible
	private ArrayList<Pair> point; //list of points of buildings
	private ArrayList<Line2D> lines= new ArrayList<>();//list of lines of buildings
	private ArrayList<String> questions= new ArrayList<>(); // questions of the buildings
	private int height;// height of the building
	private ArrayList<String> wallsImage =new ArrayList<>(); // image of the walls
	private ArrayList<ArrayList<String>>answer= new ArrayList<ArrayList<String>>(); // list of answers of the buildings
	private String image;// image of the building
	private String name; // name of building
	
	

	public Building(ArrayList<Pair> point) {
	     this.visible=0;
	     this.point=point;
	     createLine();
	     
	}
	
	
	/**Created by Carlos Rodriguez 03/24/2020
	 * Create lines between the points
	 */
	public void createLine() {
		for(int i= point.size()-1;i>0;i--) {
			lines.add(new Line2D.Double(point.get(i - 1).getX(), point.get(i - 1).getY(),point.get(i).getX(),point.get(i).getY()));
		}
	}
	
	/**Created by Carlos Rodriguez 03/24/2020
	 * Check collisions with rectangles
	 * @param r rectangle collisioned
	 */
	public boolean intersects(Rectangle r) {
		if(lines.size()==0)return false;
		for(int i=0;i<lines.size();i++) {
			if(lines.get(i).intersects(r))return true;
		}
		return false;
	}
	
	
	
	public ArrayList<String> getQuestions() {
		return questions;
	}

	public void setQuestions(ArrayList<String> questions) {
		this.questions = questions;
	}

	public ArrayList<ArrayList<String>> getAnswer() {
		return answer;
	}

	public void setAnswer(ArrayList<ArrayList<String>> answer) {
		this.answer = answer;
	}



	public int getVisible() {
		return visible;
	}

	public void setVisible(int visible) {
		this.visible = visible;
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<String> getWallsImage() {
		return wallsImage;
	}

	public int getHeight() {
		return height;
	}

	public ArrayList<Pair> getPoint() {
		return point;
	}



	public void setPoint(ArrayList<Pair> point) {
		this.point = point;
	}



	public ArrayList<Line2D> getLines() {
		return lines;
	}



	public void setLines(ArrayList<Line2D> lines) {
		this.lines = lines;
	}



	public void setWallsImage(ArrayList<String> wallsImage) {
		this.wallsImage = wallsImage;
	}



	public void setHeight(int height) {
		this.height = height;
	}
	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}


}
