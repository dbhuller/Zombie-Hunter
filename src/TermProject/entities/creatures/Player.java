/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TermProject.entities.creatures;

import TermProject.Game;
import TermProject.Handler;
import TermProject.entities.Entity;
import TermProject.gFX.Animation;
import TermProject.gFX.Assets;
import TermProject.inventory.Inventory;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

/**
 *
 * @author Owner
 */
public class Player extends Creature {
    
    //attack Timer
    private long lastAttackTimer;
    private long attackCoolDown = 300; //800 millis between attacks 
    private long attackTimer = attackCoolDown;
    
    boolean isAttacking;
    
    
    private Animation walkDown, walkUp, walkLeft, walkRight;
    private Animation attackUp, attackDown, attackLeft, attackRight;
    
    private Inventory inventory;
    
    public Player(Handler handler, float x, float y) {
        super(handler, x, y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT); 
        
        bounds.x = 8;
        bounds.y = 32;
        bounds.width = 16;
        bounds.height = 16;
        
        
        
        walkDown = new Animation(200, Assets.playerDown);
        walkUp = new Animation(200, Assets.playerUp);
        walkRight = new Animation(200, Assets.playerRight);
        walkLeft = new Animation(200, Assets.playerLeft);
        
        attackDown = new Animation(100, Assets.pAttackDown);
        attackUp = new Animation(100, Assets.pAttackUp);
        attackRight = new Animation(100, Assets.pAttackRight);
        attackLeft = new Animation(100, Assets.pAttackLeft);
        
        inventory = new Inventory(handler);
        
    }
   
    @Override
    public void update() {
        //animations
      walkDown.update();
      walkUp.update();
      walkRight.update();
      walkLeft.update();
      
      attackDown.update();
      attackUp.update();
      attackRight.update();
      attackLeft.update();
      
      //movement
      getInput();
      move();      
      handler.getCamera().centerOnEntity(this);
      
      //attack
      checkAttack();
      
      inventory.update();
        
    }
    
    private void checkAttack() {
        
        attackTimer += System.currentTimeMillis() - lastAttackTimer;
        lastAttackTimer = System.currentTimeMillis();
        if (attackTimer < attackCoolDown) {
            return;
        }
        
        if (inventory.isActive())
            return;
        
        Rectangle collisionBounds = getCollisionBounds(0, 0);
        Rectangle attackRect = new Rectangle();
        int attackRectSize = 20;
        attackRect.width = attackRectSize;
        attackRect.height = attackRectSize;
        
        if (handler.getKeyManager().attack) {
            if (handler.getKeyManager().up) {
                attackRect.x = collisionBounds.x + collisionBounds.width / 2 - attackRectSize / 2;
                attackRect.y = collisionBounds.y - attackRectSize;
            } else if (handler.getKeyManager().down) {
                attackRect.x = collisionBounds.x + collisionBounds.width / 2 - attackRectSize / 2;
                attackRect.y = collisionBounds.y + collisionBounds.height;
            } else if (handler.getKeyManager().left) {
                attackRect.x = collisionBounds.x - attackRectSize;
                attackRect.y = collisionBounds.y + collisionBounds.height / 2 - attackRectSize / 2;
            } else if (handler.getKeyManager().right) {
                attackRect.x = collisionBounds.x + collisionBounds.width;
                attackRect.y = collisionBounds.y + collisionBounds.height / 2 - attackRectSize / 2;      
            } else {                
                return;
            }        
    
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
    
    private void getInput() {
        if (inventory.isActive())
            return;
        
        xMove = 0;
        yMove = 0;
        
        if (handler.getKeyManager().up)
            yMove = -speed;
        if (handler.getKeyManager().down)
            yMove = speed;
        if (handler.getKeyManager().left)
            xMove = -speed;
        if (handler.getKeyManager().right)
            xMove = speed;  
    }
    
  
    @Override
    public void render(Graphics g) {
//        g.drawImage(getCurrentAnimationFrame(),(int) (x - handler.getCamera().getxOffset()),(int) (y - handler.getCamera().getyOffset()), width, height, null);
        
        if (isAttacking()) {
            g.drawImage(getCurrentAttackFrame(),(int) (x - handler.getCamera().getxOffset()),(int) (y - handler.getCamera().getyOffset()), width, height, null);
        } else {
           g.drawImage(getCurrentAnimationFrame(),(int) (x - handler.getCamera().getxOffset()),(int) (y - handler.getCamera().getyOffset()), width, height, null); 
        }
        
        int playerHealthBar = this.getHealth() * 3 + 2;
        int playerHBarX = (int) this.getX();
        int playerHBarY = (int) this.getY();
        int playerHBarWidth = (int) this.getWidth();
        int playerHBarHeight = 8;
        int playerHBarPadding = 2;
        int offset = 4;
        
        g.setColor(Color.DARK_GRAY);
        g.fillRect((int) (playerHBarX - handler.getCamera().getxOffset()),(int) (playerHBarY - handler.getCamera().getyOffset()), playerHBarWidth, playerHBarHeight);
        g.setColor(Color.RED);
        g.fillRect((int) (playerHBarX - handler.getCamera().getxOffset()) + playerHBarPadding, (int) (playerHBarY - handler.getCamera().getyOffset()) + playerHBarPadding,
                playerHBarWidth - offset, playerHBarHeight - offset);
        g.setColor(Color.GREEN);
        g.fillRect((int) (playerHBarX - handler.getCamera().getxOffset()) + playerHBarPadding, (int) (playerHBarY - handler.getCamera().getyOffset()) + playerHBarPadding,
                playerHealthBar - offset, playerHBarHeight - offset);
        
        
        
//        g.drawImage(getCurrentAttackFrame(),(int) (x - handler.getCamera().getxOffset()),(int) (y - handler.getCamera().getyOffset()), width, height, null);
        
        
     // Testing Rectangle for Collisions             
//        g.setColor(Color.red);
//        g.fillRect((int) (x + bounds.x - handler.getCamera().getxOffset()), (int) (y + bounds.y - handler.getCamera().getyOffset()), bounds.width, bounds.height);
    }
    
    public void postRender(Graphics g) {
        inventory.render(g);
    }

    public Inventory getInventory() {
        return inventory;
    }
    
    private boolean isAttacking() {
        if (handler.getKeyManager().attack) {
            return true;
        } else {
            return false;
        }
    }
    
    private BufferedImage getCurrentAnimationFrame() {
        if (xMove < 0) {
            return walkLeft.getCurrentFrame();            
        } else if (xMove > 0) {
            return walkRight.getCurrentFrame();
        } else if (yMove < 0) {
            return walkUp.getCurrentFrame();
        } else if (yMove > 0) {
            return walkDown.getCurrentFrame();
        } else {
            return Assets.player;
        }          
    }
    
    private BufferedImage getCurrentAttackFrame() {
        if (handler.getKeyManager().down) {
            return attackDown.getCurrentFrame();
        } else if (handler.getKeyManager().up) {
            return attackUp.getCurrentFrame();
        } else if (handler.getKeyManager().right) {
            return attackRight.getCurrentFrame();
        } else if (handler.getKeyManager().left) {
            return attackLeft.getCurrentFrame();
        } else {
            return attackDown.getCurrentFrame();
        }
        
    }
    
    @Override
    public void die() {
        System.out.println("You Loose");
    }

    
    
}
