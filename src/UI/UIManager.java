package UI;

import java.awt.event.MouseEvent;
import java.util.ArrayList;

//Jose A Velazquez Torres 03/06/2020
public class UIManager {
	
	 private ArrayList<UIObject> objects;
	
	 public void onMousePressed(MouseEvent e) {
	        for(UIObject o: objects){
	            o.onMousePressed(e);
	        }
	    }

	        public void onMouseMove(MouseEvent e){
	        for(UIObject o: objects){
	            o.onMouseMove(e);
	        }
	    }

	    public void onMouseRelease(MouseEvent e){
	        for(UIObject o: objects){
	            o.onMouseRelease(e);
	        }
	    }

}
