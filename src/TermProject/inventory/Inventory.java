/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TermProject.inventory;

import TermProject.Handler;
import TermProject.graphics.Assets;
import TermProject.graphics.Text;
import TermProject.items.Item;
import com.sun.glass.events.KeyEvent;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

/**
 *
 * Player inventory class
 * 
 * @author Deep
 */
public class Inventory {
    
    private Handler handler;
    private boolean active = false;
    private ArrayList<Item> inventoryItems;
    
    private int invX = 64, invY = 48;
    private int invWidth = 512, invHeight = 384;
    private int invCenterX = invX + 171, invCenterY = invY + invHeight/2 + 5;
    private int invListSpacing = 30;
    
    private int invImgX = 452, invImgY = 82, invImgWidth = 64, invImgHeight = 64;
    
    private int invCountX = 484, invCountY = 172;
    
    private int selectedItem = 0;   // index of inventoryItems[]
    
    public Inventory(Handler handler) {
        this.handler = handler;
        inventoryItems = new ArrayList<Item>();
        
//        addItem(Item.goldCoin.createNew(5));
//        addItem(Item.silverCoin.createNew(2));
//        addItem(Item.silverCoin.createNew(2));
//        addItem(Item.goldCoin.createNew(2));
//        addItem(Item.silverCoin.createNew(2));
//        addItem(Item.silverCoin.createNew(2));
//        addItem(Item.copperCoin.createNew(2));
//        addItem(Item.silverCoin.createNew(2));
//        addItem(Item.copperCoin.createNew(2));
//        addItem(Item.silverCoin.createNew(2));
//        addItem(Item.goldCoin.createNew(2));
//        addItem(Item.silverCoin.createNew(2));
    }

    public boolean isActive() {
        return active;
    }
    
    public void update() {
        if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_I)) {
            active = !active;
        }
        
        if (!active) {                    
            return;
        }
        
        if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_W))
            selectedItem--;
        if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_S))
            selectedItem++;
        
        if (selectedItem < 0)
            selectedItem = inventoryItems.size() - 1;
        else if (selectedItem >= inventoryItems.size())
            selectedItem = 0;
        
        System.out.println("INVENTORY: ");
        for (Item i: inventoryItems) {
            System.out.println(i.getName() + "  " + i.getCount());
        }
        
    }
    
    public void addItem(Item itemToAdd) {
        for (Item i : inventoryItems) {
            if (itemToAdd.getId() == i.getId()) {
                i.setCount(i.getCount() + itemToAdd.getCount());
                return;
            }
        }
        inventoryItems.add(itemToAdd);
    }

    
    public void render(Graphics g) {
        if (!active) {
            return;
        }
        
        g.drawImage(Assets.inventory, invX, invY, invWidth, invHeight, null);
        
        //Text.drawString(g, " ", invCenterX, invCenterY, true, Color.white, Assets.font28);
        
        int length = inventoryItems.size();
        if (length == 0) {
            return;
        }
        
        for (int i = -5; i < 6; i++) {
            if (selectedItem + i < 0 || selectedItem + i >= length) {
                continue;
            }
            if (i == 0) {
                Text.drawString(g, ">" + inventoryItems.get(selectedItem + i).getName() + "<", invCenterX, invCenterY + i * invListSpacing, true, Color.yellow, Assets.font28);
            } else {
                
            
            Text.drawString(g, inventoryItems.get(selectedItem + i).getName(), invCenterX, invCenterY + i * invListSpacing, true, Color.white, Assets.font28);
            }
        }
        
        Item item = inventoryItems.get(selectedItem);
        g.drawImage(item.getImg(), invImgX, invImgY, invImgWidth, invImgHeight, null);
        Text.drawString(g, Integer.toString(item.getCount()), invCountX, invCountY, true, Color.white, Assets.font28);
        
    }
    
    public Handler getHandler() {
        return handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }
    
}
