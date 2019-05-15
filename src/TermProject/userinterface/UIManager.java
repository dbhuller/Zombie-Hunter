/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TermProject.userinterface;

import TermProject.Handler;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

/**
 *
 * @author Owner
 */
public class UIManager {
    private Handler handler;
    private ArrayList<UIObject> objects;
    
    public UIManager(Handler handler) {
        this.handler = handler;
        objects = new ArrayList<UIObject>();
    }
    
    public void addObject(UIObject obj) {
        objects.add(obj);
    }
    
    public void removeObject(UIObject obj) {
        objects.remove(obj);
    }
    
    public void update() {
        for (UIObject obj : objects)
            obj.update();
    }
    
    public void render(Graphics g) {
        for (UIObject obj : objects)
            obj.render(g);
    }
    
    public void onMouseMove(MouseEvent e) {
        for (UIObject obj : objects)
            obj.onMouseMove(e);
    }
    
    public void onMouseRelease(MouseEvent e) {
        for (UIObject obj : objects)
            obj.onMouseRelese(e);
    }

    public Handler getHandler() {
        return handler;
    }

    public ArrayList<UIObject> getObjects() {
        return objects;
    }
    
    
}
