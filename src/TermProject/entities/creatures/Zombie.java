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
 * Another enemy 
 * 
 * @author Deep
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
    
    /**
     * Time between attacks for Zombie is 800 ms
     * Method checks which direction to create attack bounds box
     * For all entities, if entity is this entity, continue
     * if entity's bounds box intersects with this entity's collision bounds, then hurt that entity 
     * 
     */
    public void checkAttack() {
        attackTimer += System.currentTimeMillis() - lastAttackTimer;
        lastAttackTimer = System.currentTimeMillis();
        if (attackTimer < attackCoolDown) {
            return;
        }
        
        Rectangle attackRect = new Rectangle();
        Rectangle cb = getCollisionBounds(0f, 0f);
        int arSize = 20;
        attackRect.width = arSize;
        attackRect.height = arSize;
        
        if (xMove > 0) {    //right
            attackRect.x = cb.x + cb.width;
            attackRect.y = cb.y + cb.height / 2 - arSize / 2;
        }
        if (xMove < 0) {    //left
            attackRect.x = cb.x - arSize;
            attackRect.y = cb.y + cb.height / 2 - arSize / 2;
        }
        if (yMove > 0) {    //down
            attackRect.x = cb.x + cb.width / 2 - arSize / 2;
        }
        if (yMove < 0) {    //up
            attackRect.x = cb.x + cb.width / 2 - arSize / 2;
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
    
    /**
     * Handles movement in the x direction (Left and Right)
     * Checks if creature collides with solid tile and moves accordingly
     */
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
//        attack();
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
    
    /**
     * draw image at position specified 
     * 
     * @param g
     */
    @Override
    public void render(Graphics g) {
        g.drawImage(getCurrentAnimation(), (int) (x - handler.getCamera().getxOffset()), (int) (y - handler.getCamera().getyOffset()), width, height, null);
        
        // draw rectangle
//        g.setColor(Color.red);        
//        g.drawRect((int) (x - handler.getCamera().getxOffset()), (int) (y - handler.getCamera().getyOffset()), width, height);
        // reused from tank game
        int zombieHealthBar = this.getHealth() * 3 + 2;
        int zombieHBarX = (int) this.getX();
        int zombieHBarY = (int) this.getY();
        int zombieHBarWidth = (int) this.getWidth();
        int zombieHBarHeight = 8;
        int zombieHBarPadding = 2;
        int offset = 4;
        
        g.setColor(Color.DARK_GRAY);
        g.fillRect((int) (zombieHBarX - handler.getCamera().getxOffset()),(int) (zombieHBarY - handler.getCamera().getyOffset()), zombieHBarWidth, zombieHBarHeight);
        g.setColor(Color.RED);
        g.fillRect((int) (zombieHBarX - handler.getCamera().getxOffset()) + zombieHBarPadding, (int) (zombieHBarY - handler.getCamera().getyOffset()) + zombieHBarPadding,
                zombieHBarWidth - offset, zombieHBarHeight - offset);
        g.setColor(Color.GREEN);
        g.fillRect((int) (zombieHBarX - handler.getCamera().getxOffset()) + zombieHBarPadding, (int) (zombieHBarY - handler.getCamera().getyOffset()) + zombieHBarPadding,
                zombieHealthBar - offset, zombieHBarHeight - offset);
        
    }
    
    @Override
    public void die() {
       if (this.health <= 0) { 
           handler.getWorld().getiManager().addItem(Item.silverCoin.createNew((int) x + 32, (int) y + 32));
           
           System.out.println("Zombie Defeated");
       }
    }


        
    }
    



   
    
    
    

