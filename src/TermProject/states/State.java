/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TermProject.states;

import TermProject.Game;
import TermProject.Handler;
import java.awt.Graphics;

/**
 *
 * @author Deep
 */
public abstract class State {
    //can be stored in another class
    private static State currentState = null;
    
    public static void setState(State state) {
        currentState = state;
    }
    
    public static State getState() {
        return currentState;
    }
    //________________________________________
    
    
    
    
    //Class
    protected Handler handler;

    public State(Handler handler) {
        this.handler = handler;
 
    }
    public abstract void update();
    
    public abstract void render(Graphics g);
    

    

    
    
}
