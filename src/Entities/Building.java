package Entities;

import java.awt.Rectangle;
import java.awt.geom.Line2D;
import java.util.ArrayList;

import Util.Pair;

/**Carlos Rodriguez 03/24/2020
** Here we manage the building entities
*/
public class Building  {
	private int visible;
	private ArrayList<Pair> point;
	private ArrayList<Line2D> lines= new ArrayList<>();
	private ArrayList<String> questions= new ArrayList<>();
	private int height;
	private ArrayList<String> wallsImage =new ArrayList<>();
	private ArrayList<ArrayList<String>>answer= new ArrayList<ArrayList<String>>();
	private String image;
	private String name;
	
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

	public void setHeight(int height) {
		this.height = height;
	}
	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Building(ArrayList<Pair> point) {
	     this.visible=0;
	     this.point=point;
	     createLine();
	     
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

	public void createLine() {
		for(int i= point.size()-1;i>0;i--) {
			lines.add(new Line2D.Double(point.get(i - 1).getX(), point.get(i - 1).getY(),point.get(i).getX(),point.get(i).getY()));
		}
	}
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


}
