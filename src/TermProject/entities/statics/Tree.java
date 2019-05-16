/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TermProject.entities.statics;

import TermProject.Handler;
import TermProject.graphics.Animation;
import TermProject.graphics.Assets;
import TermProject.items.Item;
import TermProject.tiles.Tile;
import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author Deep
 */
public class Tree extends StaticEntity {
    
    Animation gCoinAnim;
    
    public Tree(Handler handler, float x, float y) {
        super(handler, x, y, Tile.TILE_WIDTH, Tile.TILE_HEIGHT);
        
        bounds.x = 10;
        bounds.y = 24;
        bounds.width = 24;
        bounds.height = 32;
        
        gCoinAnim = new Animation(500, Assets.goldCoin);
        
    }
    
    @Override
    public void update() {
        
    }
    
    @Override
    public void die() {
        if (this.health <= 0) {
            System.out.println("Tree Defeated");
            handler.getWorld().getiManager().addItem(Item.goldCoin.createNew((int) x, (int) y));
        }
    }
    
    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.tree, (int) (x - handler.getCamera().getxOffset()), (int) (y - handler.getCamera().getyOffset()), width, height, null);
        
//        g.setColor(Color.red);
//        g.fillRect((int) (x + bounds.x - handler.getCamera().getxOffset()), (int) (y + bounds.y - handler.getCamera().getyOffset()), bounds.width, bounds.height);
    }
    
}
