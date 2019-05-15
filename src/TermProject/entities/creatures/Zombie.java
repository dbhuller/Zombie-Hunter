/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TermProject.entities.creatures;

import TermProject.Handler;
import TermProject.entities.Entity;
import TermProject.gFX.Animation;
import TermProject.gFX.Assets;
import TermProject.items.Item;
import TermProject.tiles.Tile;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * 
 * @author Owner
 */
public class Zombie extends Creature {
    
    //attack Timer
    private long lastAttackTimer;
    private long attackCoolDown = 800; //800 millis between attacks 
    private long attackTimer = attackCoolDown;
    
    public boolean inRange;
    private Animation walkUp, walkDown, walkLeft, walkRight;
    private Player player;
    
    Random r = new Random();
    int choose = 0;
    
    public Zombie(Handler handler, float x, float y) {
        super(handler, x, y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT);
        
        
        
        bounds.x = 8;
        bounds.y = 32;
        bounds.width = 16;
        bounds.height = 16;
        
        walkUp = new Animation(100, Assets.zUp);
        walkDown = new Animation(100, Assets.zUp);
        walkLeft = new Animation(100, Assets.zUp);
        walkRight = new Animation(100, Assets.zUp);
        
//        xMove = (int) (Math.random() * 3) - 1;
//        yMove = (int) (Math.random() * 2) - 1;
        
    }
    
    @Override
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
    
    @Override
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
    
    @Override
    public void move() {
        
        moveX();
        moveY();
    }

    @Override
    public void update() {
        
//        for (int i = 0; i < handler.getWorld(). )
        choose = r.nextInt(10);
        if (choose == 0) {
            xMove = (r.nextInt(2 - -4) + -4);
            yMove = (r.nextInt(4 - -4) + -4);
        } 
        
        
        
        move();

        walkUp.update();
        walkDown.update();
        walkLeft.update();
        walkRight.update();
        
        checkAttack();
        attack();
    }
    
    public BufferedImage getCurrentAnimation() {
        if (xMove >= 0)
            return walkRight.getCurrentFrame();
        if (xMove <= 0) 
            return walkLeft.getCurrentFrame();
        if (yMove >= 0)
            return walkDown.getCurrentFrame();
        if (yMove <= 0)
            return walkUp.getCurrentFrame();
        else 
            return this.getCurrentAnimation();
    } 
    
    

    @Override
    public void render(Graphics g) {
        g.drawImage(getCurrentAnimation(), (int) (x - handler.getCamera().getxOffset()), (int) (y - handler.getCamera().getyOffset()), width, height, null);
        
        // draw rectangle
        g.setColor(Color.red);        
        g.drawRect((int) (x - handler.getCamera().getxOffset()), (int) (y - handler.getCamera().getyOffset()), width, height);
    }
    
    @Override
    public void die() {
       if (this.health <= 0) { 
           handler.getWorld().getiManager().addItem(Item.silverCoin.createNew((int) x, (int) y));
           handler.getWorld().getiManager().addItem(Item.silverCoin.createNew((int) x  + 5, (int) y));
           handler.getWorld().getiManager().addItem(Item.silverCoin.createNew((int) x  + 10, (int) y));
           System.out.println("Zombie Defeated");
       }
    }

    private void attack() {
     if (inRange) {
         player.hurt(1);
     } 
        
    }
    
    private boolean inRange() {
        if (this.checkEntityCollision(0f, 0f) == player.checkEntityCollision(0f, 0f)) {
            return true;
        } else {
            return false;
        }
    }
    
    private void checkAttack() {
        attackTimer += System.currentTimeMillis() - lastAttackTimer;
        lastAttackTimer = System.currentTimeMillis();
        if (attackTimer < attackCoolDown) {
            return;
        }
        
        Rectangle collisionBounds = getCollisionBounds(0, 0);
        Rectangle attackRect = new Rectangle();
        int attackRectSize = 20;
        attackRect.width = attackRectSize;
        attackRect.height = attackRectSize;
        
        if (inRange()) {
            if (this.yMove < 0) {
                attackRect.x = collisionBounds.x + collisionBounds.width / 2 - attackRectSize / 2;
                attackRect.y = collisionBounds.y - attackRectSize;
            } else if (this.yMove > 0) {
                attackRect.x = collisionBounds.x + collisionBounds.width / 2 - attackRectSize / 2;
                attackRect.y = collisionBounds.y + collisionBounds.height;
            } else if (this.xMove < 0) {
                attackRect.x = collisionBounds.x - attackRectSize;
                attackRect.y = collisionBounds.y + collisionBounds.height / 2 - attackRectSize / 2;
            } else if (this.xMove > 0) {
                attackRect.x = collisionBounds.x + collisionBounds.width;
                attackRect.y = collisionBounds.y + collisionBounds.height / 2 - attackRectSize / 2;      
            } else {                
                return;
            }        
        
        attackTimer = 0;
        
        for (Entity e : handler.getWorld().geteManager().getEntities()) {
            if (e.equals(this))
                continue;
            if (e.getCollisionBounds(0, 0).intersects(attackRect)) {
                e.hurt(1);
                return;
            }
        }
    }
        
}



   
    
    
    
}
