/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TermProject.input;

import TermProject.userinterface.UIManager;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 *
 * @author Owner
 */
public class MouseManager implements MouseListener, MouseMotionListener {
    private boolean leftPressed, rightPressed;
    private int mouseX, mouseY;
    private UIManager uiManager;
    
    public MouseManager() {
        
    }
    
    public void setUIManager(UIManager uiManager) {
        this.uiManager = uiManager;
    }
    
    //Getters
    public boolean isLeftPressed() {
        return leftPressed;
    }
    
    public boolean isRightPressed() {
        return rightPressed;
    }

    public int getMouseX() {
        return mouseX;
    }

    public int getMouseY() {
        return mouseY;
    }
    
    
    
    // Implemented Abstract Methods

    @Override
    public void mouseClicked(MouseEvent e) {
        
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1)    //left mouse button
            leftPressed = true;
        if (e.getButton() == MouseEvent.BUTTON2)    //right mouse button
            rightPressed = true;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1)    //left mouse button
            leftPressed = false;
        else if (e.getButton() == MouseEvent.BUTTON2)    //right mouse button
            rightPressed = false;
        
        if(uiManager != null)
            uiManager.onMouseRelease(e);
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
        
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();
        
        if (uiManager != null)
            uiManager.onMouseMove(e);
    }
    
}
