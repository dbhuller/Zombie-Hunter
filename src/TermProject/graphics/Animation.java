/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TermProject.graphics;

import java.awt.image.BufferedImage;

/**
 * Class responsible for setting up animations for moving game objects
 * 
 * @author Deep
 */
public class Animation {
    private int speed;
    private int index;
    private BufferedImage[] frames;
    private long lastTime, timer;
    
    public Animation(int speed, BufferedImage[] frames) {
        this.speed = speed;
        this.frames = frames;
        index = 0;
        lastTime = System.currentTimeMillis();
        timer = 0;
        
    }
    
    public void update() {
        timer += System.currentTimeMillis() - lastTime;
        lastTime = System.currentTimeMillis();
        
        if (timer > speed) {
            index++;
            timer = 0;
            if (index >= frames.length) 
                index = 0;
        }
    }
    
    public BufferedImage getCurrentFrame() {
        return frames[index];
    }
}
