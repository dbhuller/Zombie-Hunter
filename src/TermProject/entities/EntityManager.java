/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TermProject.entities;

import TermProject.Handler;
import TermProject.entities.creatures.Player;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;

/**
 *
 * @author Owner
 */
public class EntityManager {
    
    private Handler handler;
    private Player player;
    private ArrayList<Entity> entities;
    private Comparator<Entity> sortEntitiesList = new Comparator<Entity>() {
        @Override
        public int compare(Entity e1, Entity e2) {
            if (e1.getY() + e1.getHeight()  < e2.getY() + e2.getHeight())
                return -1;
            return 1;
        }
    };
    
    
    
    public EntityManager(Handler handler, Player player) {
        this.handler = handler;
        this.player = player;
        entities = new ArrayList<Entity>();
        addEntity(player);
    }
    
    public void update() {
        Iterator<Entity> iter = entities.iterator();
        while (iter.hasNext()) {
            Entity e = iter.next();
            e.update();
            if (!e.isActive())
                iter.remove();
        }
        entities.sort(sortEntitiesList);
    }
    
    public void render(Graphics g) {
        for (Entity e : entities) {
            e.render(g);
        }
        //player.render(g);
        player.postRender(g);
    }
    
    public void addEntity(Entity e) {
        entities.add(e);
    }

    public Handler getHandler() {
        return handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public ArrayList<Entity> getEntities() {
        return entities;
    }

    public void setEntities(ArrayList<Entity> entities) {
        this.entities = entities;
    }
    

    
}
