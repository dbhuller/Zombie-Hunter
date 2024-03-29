/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TermProject.entities.creatures;

import TermProject.Game;
import TermProject.Handler;
import TermProject.entities.Entity;
import TermProject.tiles.Tile;
import java.awt.Graphics;

/**
 *  Basis for creature class, subclass of entity
 *
 * @author Deep
 */
public abstract class Creature extends Entity {
    
    
    public static final float DEFAULT_SPEED = 3.0f;
    public static final int DEFAULT_CREATURE_WIDTH = 32, DEFAULT_CREATURE_HEIGHT = 64;
    
    
    protected float speed;
    protected float xMove, yMove;

    public Creature(Handler handler, float x, float y, int width, int height) {
        super(handler, x, y, width, height);
        
        speed = DEFAULT_SPEED;
        
        xMove = 0;
        yMove = 0;
    }

    public void move() {
        if (!checkEntityCollision(xMove, 0f))
            moveX();
        if (!checkEntityCollision(0f, yMove))
            moveY();
        
//        moveX();
//        moveY();
        
    }
    
    public void moveX() {
        if (xMove > 0) {    // moving right
            int tx = (int) (x + xMove + bounds.x + bounds.width) / Tile.TILE_WIDTH;
            
            if (!collisionWithTile(tx, (int) (y + bounds.y) /Tile.TILE_HEIGHT) && !collisionWithTile(tx, (int) (y + bounds.y) / Tile.TILE_HEIGHT)) {
                x += xMove;
            } else {
                x = tx * Tile.TILE_WIDTH - bounds.x - bounds.width - 1;
            }
        } else if (xMove < 0) {     //moving left
            int tx = (int) (x + xMove + bounds.x) / Tile.TILE_WIDTH;
            
            if (!collisionWithTile(tx, (int) (y + bounds.y) /Tile.TILE_HEIGHT) && !collisionWithTile(tx, (int) (y + bounds.y + bounds.height) / Tile.TILE_HEIGHT)) {
                x += xMove;
            } else {
                x = tx * Tile.TILE_WIDTH + Tile.TILE_WIDTH - bounds.x;
                
            }
        }
    }
    
    public void moveY() {
        if (yMove < 0) {    //up
            int ty = (int) (y + yMove + bounds.y) / Tile.TILE_HEIGHT;
            
            if (!collisionWithTile((int) (x + bounds.x) / Tile.TILE_WIDTH, ty) && !collisionWithTile((int) (x + bounds.x + bounds.width) / Tile.TILE_WIDTH, ty)) {
                y += yMove;
            } else {
                y = ty * Tile.TILE_HEIGHT + Tile.TILE_HEIGHT - bounds.y;
            }
        } else if (yMove > 0) { //down
            int ty = (int) (y + yMove + bounds.y + bounds.height) / Tile.TILE_HEIGHT;
            
            if (!collisionWithTile((int) (x + bounds.x) / Tile.TILE_WIDTH, ty) && !collisionWithTile((int) (x + bounds.x + bounds.width) / Tile.TILE_WIDTH, ty)) {
                y += yMove;
            } else {
                y = ty * Tile.TILE_HEIGHT - bounds.y - bounds.height - 1;
            }
        }        
    }
    
    protected boolean collisionWithTile(int x, int y) {
        return handler.getWorld().getTile(x, y).isSolid();
    }
    
    
    //GETTERS AND SETTERS
    public float getxMove() {
        return xMove;
    }

    public void setxMove(float xMove) {
        this.xMove = xMove;
    }

    public float getyMove() {
        return yMove;
    }

    public void setyMove(float yMove) {
        this.yMove = yMove;
    }

    @Override
    public void setHealth(int health) {
        this.health = health;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    @Override
    public int getHealth() {
        return health;
    }

    public float getSpeed() {
        return speed;
    }

    
}
