/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TermProject;

import TermProject.graphics.GameCamera;
import TermProject.input.KeyManager;
import TermProject.input.MouseManager;
import TermProject.world.World;

/**
 *
 * @author Deep
 */
public class Handler {
    private Game game;
      private World world;
      
    public Handler(Game game) {
        this.game = game;
    }
    
    public KeyManager getKeyManager() {
        return game.getKeyManager();
    }
    
    public MouseManager getMouseManager() {
        return game.getMouseManager();
    }
    
    public GameCamera getCamera() {
        return game.getCamera();
    }
    
    public int getWidth() {
        return game.getWidth();
    }
    
    public int getHeight() {
        return game.getHeight();
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public World getWorld() {
        return world;
    }

    public void setWorld(World world) {
        this.world = world;
    }
}
