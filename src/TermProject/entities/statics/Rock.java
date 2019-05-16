/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TermProject.entities.statics;

import TermProject.Handler;
import TermProject.graphics.Assets;
import TermProject.items.Item;
import TermProject.tiles.Tile;
import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

/**
 *
 * @author Deep
 */
public class Rock extends StaticEntity {
    
    public Rock(Handler handler, float x, float y) {
        super(handler, x, y, Tile.TILE_WIDTH, Tile.TILE_HEIGHT);
        
        bounds.x = 16;
        bounds.y = 18;
        bounds.width = 28;
        bounds.height = 22;
    }
    
    public void respawn() {
        long respawnTime = System.currentTimeMillis();
        if (!active) {
            if (respawnTime > 1000) {
                this.active = true;
            }
        }
    }
    
    @Override
    public void die() {
        if (this.health <= 0) {
            Random r = new Random();
            int choose = r.nextInt(3);
            if (choose == 0) {
                System.out.println("Ruby Mined");
                handler.getWorld().getiManager().addItem(Item.ruby.createNew((int) x, (int) y));                
                
                
            } else if (choose == 1) {
                System.out.println("Sapphire Mined");
                handler.getWorld().getiManager().addItem(Item.sapphire.createNew((int) x, (int) y));
                
              
            } else if (choose == 2) {
                System.out.println("Diamond Mined");
                handler.getWorld().getiManager().addItem(Item.diamond.createNew((int) x, (int) y));
            }
            
        }
        active = false;
        respawn();
        
    }
    
    @Override
    public void update() {
        
    }
    
    @Override
    public void render(Graphics g) {
       g.drawImage(Assets.rock, (int) (x - handler.getCamera().getxOffset()),(int) (y - handler.getCamera().getyOffset()), width, height, null);
       
//       g.setColor(Color.red);
//        g.fillRect((int) (x + bounds.x - handler.getCamera().getxOffset()), (int) (y + bounds.y - handler.getCamera().getyOffset()), bounds.width, bounds.height);
       
    }
}
