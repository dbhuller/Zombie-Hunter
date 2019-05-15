/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TermProject.entities.creatures;

import TermProject.Handler;
import TermProject.gFX.Assets;
import TermProject.items.Item;
import java.awt.Graphics;

/**
 *
 * @author Owner
 */
public class Cobra extends Creature {

    public Cobra(Handler handler, float x, float y) {
        super(handler, x, y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT);
        
        bounds.x = 8;
        bounds.y = 32;
        bounds.width = 16;
        bounds.height = 16;
    }

    @Override
    public void update() {
        
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.cobra, (int) (x - handler.getCamera().getxOffset()), (int) (y - handler.getCamera().getyOffset()), width, height, null);
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
