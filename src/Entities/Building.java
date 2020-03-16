package Entities;

import java.awt.Rectangle;
import java.util.ArrayList;

/**Jose A Velazquez Torres 03/07/2020
** Here we manage the building entities
*/
public class Building extends Rectangle {
	private int x;
	private int y;
	private int width;
	private int height;
	private boolean colition;
	private int visible;
	private ArrayList<String> questions;
	ArrayList<ArrayList<String>>answer= new ArrayList<ArrayList<String>>();
	private String image;
	
	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public boolean getColition() {
		return colition;
	}

	public void setColition(boolean colition) {
		this.colition = colition;
	}

	public Building(int x, int y, int height, int width) {
		super(x,y,height,width);
		 this.x = x;
	     this.y = y;
	     this.width = width;
	     this.height = height;	     
	     this.colition =false ;
	     this.visible=0;
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

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public Building() {
		
	}

	public double getX() {
		return x;
	}

	public double getWidth() {
		return width;
	}

	public double getHeight() {
		return height;
	}

	public int getVisible() {
		return visible;
	}

	public void setVisible(int visible) {
		this.visible = visible;
	}

	public double getY() {
		return y;
	}

}
