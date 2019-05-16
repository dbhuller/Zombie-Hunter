/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TermProject.entities;

import TermProject.Game;
import TermProject.Handler;
import java.awt.Graphics;
import java.awt.Rectangle;

/**
 * Abstact class for Entity game objects 
 *
 * @author Deep
 */
public abstract class Entity {
    
    protected Handler handler;
    protected float x, y;
    protected int width, height;
    
    protected Rectangle bounds;
    protected int health;
    protected boolean active = true;


    
    public static final int DEFAULT_HEALTH = 10;
    
    
    public Entity(Handler handler, float x, float y, int width, int height) {
        health = DEFAULT_HEALTH;
        this.handler = handler;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        bounds = new Rectangle(0, 0, width, height);
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
    
        public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
    
    public boolean checkEntityCollision(float xOffset, float yOffset) {
        for (Entity e : handler.getWorld().geteManager().getEntities()) {
            if (e.equals(this)) {
                continue;
            }
            if (e.getCollisionBounds(0f, 0f).intersects(getCollisionBounds(xOffset, yOffset))) {
                return true;
            }
        }
        return false;
    }
    
    public Rectangle getCollisionBounds(float xOffset, float yOffset) {
        return new Rectangle((int) (x + bounds.x + xOffset), (int) (y + bounds.y + yOffset), bounds.width, bounds.height);
    }
    
    public abstract void update();
    
    public abstract void render(Graphics g);     
    
    public abstract void die();
    
    public void hurt(int amt) {
        health -= amt;
        if (health <= 0)
            active = false;
            die();
    }
    
}
