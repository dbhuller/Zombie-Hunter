/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TermProject.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author Owner
 */
public class KeyManager implements KeyListener {

    private boolean[] keys, justPressed, cantPress;
    public boolean up, down, left, right;
    public boolean attack;
    //public boolean upAttack, downAttack, leftAttack, rightAttack;
    
    public KeyManager() {
        keys = new boolean[256];
        justPressed = new boolean[keys.length];
        cantPress = new boolean[keys.length];
    }
    
    public void update() {
        for (int i = 0; i < keys.length; i++) {
            if (cantPress[i] && !keys[i]) {
                cantPress[i] = false;
            } else if (justPressed[i]) {
                cantPress[i] = true;
                justPressed[i] = false;
            }
            if (!cantPress[i] && keys[i]) {
                justPressed[i] = true;
            }

        }
        
        if (keyJustPressed(KeyEvent.VK_I)) {
            System.out.println("I pressed");
        }
        
        up = keys[KeyEvent.VK_UP];
        down = keys[KeyEvent.VK_DOWN];
        left = keys[KeyEvent.VK_LEFT];
        right = keys[KeyEvent.VK_RIGHT];
        
        attack = keys[KeyEvent.VK_SPACE];
        
//       upAttack = keys[KeyEvent.VK_W];
//       downAttack = keys[KeyEvent.VK_S];
//       leftAttack = keys[KeyEvent.VK_A];
//       rightAttack = keys[KeyEvent.VK_D];
        
    }
    
    @Override
    public void keyTyped(KeyEvent e) {
        //not used (for now)
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() < 0 || e.getKeyCode() >= keys.length)
            return;
        
        keys[e.getKeyCode()] = true;
        System.out.println("Pressed");
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() < 0 || e.getKeyCode() >= keys.length)
            return;
        
        keys[e.getKeyCode()] = false;
    }

    public boolean keyJustPressed(int keyCode) {
        if (keyCode < 0 || keyCode >= keys.length)
            return false;
        return justPressed[keyCode];
    }
    
}
