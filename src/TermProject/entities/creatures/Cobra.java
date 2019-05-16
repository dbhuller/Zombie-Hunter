/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TermProject.entities.creatures;

import TermProject.Handler;
import TermProject.entities.Entity;
import TermProject.graphics.Animation;
import TermProject.graphics.Assets;
import TermProject.items.Item;
import TermProject.tiles.Tile;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * Cobra enemy class 
 * 
 * @author Deep
 */
public class Cobra extends Creature {
    
    Random r = new Random();
    int choose = 0;
    Player player;
    
    //attack Timer
    private long lastAttackTimer;
    private long attackCoolDown = 1200; //800 millis between attacks 
    private long attackTimer = attackCoolDown;
    
    Animation cobraUp, cobraDown, cobraRight, cobraLeft;

    public Cobra(Handler handler, float x, float y) {
        super(handler, x, y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT);
        
        bounds.x = 8;
        bounds.y = 32;
        bounds.width = 16;
        bounds.height = 16;
        
        cobraUp = new Animation(100, Assets.cUp);
        cobraDown = new Animation(100, Assets.cDown);
        cobraLeft = new Animation(100, Assets.cLeft);
        cobraRight = new Animation(100, Assets.cRight);
    }

    @Override
    public void update() {
//        choose = r.nextInt(10);
//        if (choose == 0) {
//            xMove = (r.nextInt(4 - -4) + -4);
//            yMove = (r.nextInt(4 - -4) + -4);
//        }
        move();
        cobraUp.update();
        cobraDown.update();
        cobraLeft.update();
        cobraRight.update();
        
        checkAttack();
    }
    
    public void checkAttack() {
        attackTimer += System.currentTimeMillis() - lastAttackTimer;
        lastAttackTimer = System.currentTimeMillis();
        if (attackTimer < attackCoolDown) {
            return;
        }
        
        Rectangle attackRect = new Rectangle();
        Rectangle cb = getCollisionBounds(0f, 0f);
        int arSize = 40;
        attackRect.width = arSize;
        attackRect.height = arSize;
        
        if (xMove >= 0) {    //right
            attackRect.x = cb.x + cb.width;
            attackRect.y = cb.y + cb.height / 2 - arSize / 2;
        }
        if (xMove <= 0) {    //left
            attackRect.x = cb.x - arSize;
            attackRect.y = cb.y + cb.height / 2 - arSize / 2;
        }
        if (yMove >= 0) {    //down
            attackRect.x = cb.x + cb.width / 2 - arSize / 2;
        }
        if (yMove <= 0) {    //up
            attackRect.x = cb.x + cb.width / 2 - arSize / 2;
        } else {
            return;
        }
        
        attackTimer = 0;
        
        for (Entity e : handler.getWorld().geteManager().getEntities()) {
            if (e.equals(this))
                continue;
            if (e.getCollisionBounds(0, 0).intersects(attackRect)) {
                e.hurt(2);
                return;
            }
        }
        
        
    }
    
   
    
    public BufferedImage getCurrentAnimation() {
        if (xMove > 0)
            return cobraRight.getCurrentFrame();
        if (xMove < 0) 
            return cobraLeft.getCurrentFrame();
        if (yMove > 0)
            return cobraDown.getCurrentFrame();
        if (yMove < 0)
            return cobraUp.getCurrentFrame();
        else 
            return Assets.cobra;
    } 
        
    
   

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.cobra, (int) (x - handler.getCamera().getxOffset()), (int) (y - handler.getCamera().getyOffset()), width, height, null);
        
        
        // Reused from tank game
        int cobraHealthBar = this.getHealth() * 3 + 2;
        int cobraHBarX = (int) this.getX();
        int cobraHBarY = (int) this.getY();
        int cobraHBarWidth = (int) this.getWidth();
        int cobraHBarHeight = 8;
        int cobraHBarPadding = 2;
        int offset = 4;
        
        g.setColor(Color.DARK_GRAY);
        g.fillRect((int) (cobraHBarX - handler.getCamera().getxOffset()),(int) (cobraHBarY - handler.getCamera().getyOffset()), cobraHBarWidth, cobraHBarHeight);
        g.setColor(Color.RED);
        g.fillRect((int) (cobraHBarX - handler.getCamera().getxOffset()) + cobraHBarPadding, (int) (cobraHBarY - handler.getCamera().getyOffset()) + cobraHBarPadding,
                cobraHBarWidth - offset, cobraHBarHeight - offset);
        g.setColor(Color.GREEN);
        g.fillRect((int) (cobraHBarX - handler.getCamera().getxOffset()) + cobraHBarPadding, (int) (cobraHBarY - handler.getCamera().getyOffset()) + cobraHBarPadding,
                cobraHealthBar - offset, cobraHBarHeight - offset);
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
                yMove += y;
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
    public void die() {
        if (this.health <= 0) { 
           handler.getWorld().getiManager().addItem(Item.goldCoin.createNew((int) x, (int) y));
           handler.getWorld().getiManager().addItem(Item.goldCoin.createNew((int) x  + 5, (int) y));
           handler.getWorld().getiManager().addItem(Item.goldCoin.createNew((int) x  + 10, (int) y));
           System.out.println("Cobra Defeated");
       }
    }
    
}
