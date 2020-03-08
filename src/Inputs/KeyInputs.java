package Inputs;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
//Carlos Rodriguez 3/6/2020
public class KeyInputs implements KeyListener {

	private boolean[] keys,justPressed,cantPress;
	private boolean leftIsPressed;
	

	private boolean rightIsPressed;
	private boolean downIsPressed;
	private boolean upIsPressed;
	


	public KeyInputs(){

		keys = new boolean[256];
		justPressed = new boolean[keys.length];
		cantPress = new boolean[keys.length];

	}
	public boolean isLeftIsPressed() {
		return leftIsPressed;
	}

	public boolean isRightIsPressed() {
		return rightIsPressed;
	}

	public boolean isDownIsPressed() {
		return downIsPressed;
	}

	public boolean isUpIsPressed() {
		return upIsPressed;
	}

	public void tick(){
		for(int i =0; i < keys.length;i++){
			if(cantPress[i] && !keys[i]){
				cantPress[i]=false;

			}else if(justPressed[i]){
				cantPress[i]=true;
				justPressed[i] =false;
			}
			if(!cantPress[i] && keys[i]){
				justPressed[i]=true;
			}
		}

		this.upIsPressed = keys[KeyEvent.VK_UP];
		this.downIsPressed = keys[KeyEvent.VK_DOWN];
		this.leftIsPressed= keys[KeyEvent.VK_LEFT];
		this.rightIsPressed = keys[KeyEvent.VK_RIGHT];
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() < 0 || e.getKeyCode() >= keys.length)
			return;
		keys[e.getKeyCode()] = true;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() < 0 || e.getKeyCode() >= keys.length)
			return;
		keys[e.getKeyCode()] = false;
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	public boolean keyJustPressed(int keyCode){
		if(keyCode < 0 || keyCode >= keys.length)
			return false;
		return justPressed[keyCode];
	}
	public boolean nothingPressed() {
		if(!this.downIsPressed&&!this.leftIsPressed&&!this.rightIsPressed&&!this.upIsPressed)return true;
		return false;
	}

}