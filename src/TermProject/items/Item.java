/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TermProject.items;

import TermProject.Handler;
import TermProject.graphics.Animation;
import TermProject.graphics.Assets;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

/**
 * This class defines all pickUpable items in the game
 * 
 * @author Deep
 */
public class Item {
    //handler
    public static Item[] items = new Item[256];
    
    public static Item goldCoin = new Item(Assets.gCoin, "Gold Coin", 0);
    public static Item silverCoin = new Item(Assets.sCoin, "Silver Coin", 1);
    public static Item copperCoin = new Item(Assets.cCoin, "Copper Coin", 2);
    public static Item ruby = new Item(Assets.ruby, "Ruby", 3);
    public static Item sapphire = new Item(Assets.sapphire, "Sapphire", 4);
    public static Item diamond = new Item(Assets.diamond, "Diamond", 5);
//    public static Item venomOrb = new Item(Assets.venomOrb, "Blood Orb", 6);
    
    
    //CLASS
    public static final int ITEMWIDTH = 32, ITEMHEIGHT = 32;
    
    protected Handler handler;
    protected BufferedImage img;
    protected String name;
    protected Animation anim;

    
    protected int id;
    protected int x, y, count;
    protected Rectangle bounds;
    protected boolean pickedUp = false;
    
    public Item(BufferedImage img, String name, int id) {
        this.img = img;
        this.name = name;
        this.id = id;
        count = 1;
        
        bounds = new Rectangle(x, y, ITEMWIDTH, ITEMHEIGHT);
        
        items[id] = this;
    }
    
    public Item(Animation anim, String name, int id) {
        this.anim = anim;
        this.name = name;
        this.id = id;
        count = 1;
        
        bounds = new Rectangle(x, y, ITEMWIDTH, ITEMHEIGHT);
        items[id] = this;
    }
    
    
    
    public void update() {
        if (handler.getWorld().geteManager().getPlayer().getCollisionBounds(0f, 0f).intersects(bounds)) {
            pickedUp = true;
            handler.getWorld().geteManager().getPlayer().getInventory().addItem(this);
        }
        
    }

    public boolean isPickedUp() {
        return pickedUp;
    }
    
    public Item createNew(int x, int y) {
        Item i = new Item(img, name, id);
        i.setPosition(x, y);
        return i;
        
    }
    
    public Item createNew(int count) {
        Item i = new Item(img, name, id);
        i.setPickedUp(true);
        i.setCount(count);
        return i;
        
    }
    
    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
        bounds.x = x;
        bounds.y = y;
    }
    
    public void render(Graphics g, int x, int y) {
       g.drawImage(img, x, y, ITEMWIDTH, ITEMHEIGHT, null);
    }
    
   
    
      public void render(Graphics g) {
        if (handler == null)
            return;
        render(g, (int) (x - handler.getCamera().getxOffset()), (int) (y - handler.getCamera().getyOffset()));
    }

    public Handler getHandler() {
        return handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    public BufferedImage getImg() {
        return img;
    }

    public void setImg(BufferedImage img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
    
    public void setPickedUp(boolean pu) {
        this.pickedUp = pu;
    }
    
    public boolean getPickedUp() {
        return pickedUp;
    }
    
  
}
