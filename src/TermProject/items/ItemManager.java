/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TermProject.items;

import TermProject.Handler;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author Owner
 */
public class ItemManager {
    private Handler handler;
    private ArrayList<Item> items;
    
    public ItemManager(Handler handler) {
        this.handler = handler;
        items = new ArrayList<Item>();
    }
    
    public void update() {
        Iterator<Item> iter = items.iterator();
        while(iter.hasNext()) {
            Item i = iter.next();
            i.update();
            if (i.pickedUp)
                iter.remove();
        }
    }



    
    public void render(Graphics g) {
        for (Item i : items)
            i.render(g);
    }
    
    public void addItem(Item i) {
        i.setHandler(handler);
        items.add(i);
    }
    
    public Handler getHandler() {
        return handler;
    }
    
}
