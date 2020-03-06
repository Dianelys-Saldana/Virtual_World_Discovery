//package Inputs;
//
//import UI;
//
//import java.awt.event.MouseEvent;
//import java.awt.event.MouseListener;
//import java.awt.event.MouseMotionListener;
//
//
//public class MouseInputs implements MouseListener,MouseMotionListener{
//
//    private boolean leftPressed,rightPressed;
//    private int mouseX,mouseY;
//    private UIManager uimanager;
//
//    public MouseManager(){
//
//    }
//
//    public void setUimanager(UIManager uimanager){
//        this.uimanager=uimanager;
//    }
//
//    public boolean isLeftPressed() {
//        return leftPressed;
//    }
//
//    public boolean isRightPressed() {
//        return rightPressed;
//    }
//
//    public int getMouseX() {
//        return mouseX;
//    }
//
//    public int getMouseY() {
//        return mouseY;
//    }
//
//    @Override
//    public void mouseClicked(MouseEvent e) {
//
//    }
//
//    @Override
//    public void mousePressed(MouseEvent e) {
//        if(e.getButton()==MouseEvent.BUTTON1){
//            leftPressed=true;
//        }
//        else if(e.getButton()==MouseEvent.BUTTON3){
//            rightPressed=true;
//        }
//        if(uimanager !=null){
//            uimanager.onMousePressed(e);
//        }
//
//    }
//
//    @Override
//    public void mouseReleased(MouseEvent e) {
//        if(e.getButton()==MouseEvent.BUTTON1){
//            leftPressed=false;
//        }
//        else if(e.getButton()==MouseEvent.BUTTON3){
//            rightPressed=false;
//        }
//        if(uimanager !=null){
//            uimanager.onMouseRelease(e);
//        }
//    }
//
//    @Override
//    public void mouseEntered(MouseEvent e) {
//
//    }
//
//    @Override
//    public void mouseExited(MouseEvent e) {
//
//    }
//
//    @Override
//    public void mouseDragged(MouseEvent e) {
//
//    }
//
//    @Override
//    public void mouseMoved(MouseEvent e) {
//        mouseX = e.getX();
//        mouseY = e.getY();
//        if(uimanager !=null){
//            uimanager.onMouseMove(e);
//        }
//
//    }
//}