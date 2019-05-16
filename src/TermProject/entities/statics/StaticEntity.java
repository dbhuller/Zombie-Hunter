/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TermProject.entities.statics;

import TermProject.Handler;
import TermProject.entities.Entity;
import java.awt.Graphics;

/**
 * UNMOVING ENTITIES
 * @author Deep
 */
public abstract class StaticEntity extends Entity {

    public StaticEntity(Handler handler, float x, float y, int width, int height) {
        super(handler, x, y, width, height);
    }
    
    @Override
    public void update() {
       
    }

    @Override
    public void render(Graphics g) {
        
    }

//    @Override
//    public void die() {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
    
}
