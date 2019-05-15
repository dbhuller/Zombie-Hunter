/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TermProject.world;

import TermProject.Game;
import TermProject.Handler;
import TermProject.entities.EntityManager;
import TermProject.entities.creatures.Cobra;
import TermProject.entities.creatures.Player;
import TermProject.entities.creatures.Zombie;
import TermProject.entities.statics.Cactus;
import TermProject.entities.statics.Rock;
import TermProject.entities.statics.Stump;
import TermProject.entities.statics.Tree;
import TermProject.items.ItemManager;
import TermProject.tiles.Tile;
import TermProject.utils.Utils;
import java.awt.Graphics;

/**
 *
 * @author Owner
 */
public class World {
    private int width, height;
    private int[][] worldTiles;
    private Handler handler;
    
    //entity manager
    private EntityManager eManager;
    
    //item manager
    private ItemManager iManager;
    
    private int spawnX, spawnY;
    
    public World(Handler handler, String path) {
        this.handler = handler;
        eManager = new EntityManager(handler, new Player(handler, 100, 100));
        
        iManager = new ItemManager(handler);
        
        eManager.addEntity(new Tree(handler, 200, 200));
        eManager.addEntity(new Tree(handler, 240, 200));
        eManager.addEntity(new Tree(handler, 280, 200));
        eManager.addEntity(new Tree(handler, 320, 200));
        
        eManager.addEntity(new Tree(handler, 200, 300));
        eManager.addEntity(new Tree(handler, 240, 300));
        eManager.addEntity(new Tree(handler, 280, 300));
        eManager.addEntity(new Tree(handler, 320, 300));
        
        eManager.addEntity(new Tree(handler, 200, 400));
        eManager.addEntity(new Tree(handler, 240, 400));
        eManager.addEntity(new Tree(handler, 280, 400));
        eManager.addEntity(new Tree(handler, 320, 400));
        
        
        
        eManager.addEntity(new Stump(handler, 600, 200));
        eManager.addEntity(new Stump(handler, 600, 260));
        
        eManager.addEntity(new Rock(handler, 700, 200));
        eManager.addEntity(new Cactus(handler, 700, 1000));
        
        
        eManager.addEntity(new Zombie(handler, 800, 800));
        eManager.addEntity(new Zombie(handler, 232, 832));
        
        
        
        eManager.addEntity(new Cobra(handler, 860, 860));
        eManager.addEntity(new Cobra(handler, 928, 928));
        
        loadWorld(path);
        
        eManager.getPlayer().setX(spawnX);
        eManager.getPlayer().setY(spawnY);
        
    }

    public EntityManager geteManager() {
        return eManager;
    }
    
    public void update() {
        iManager.update();
        eManager.update();
    }
    
    public void render(Graphics g) {
        int xStart = (int) Math.max(0, handler.getCamera().getxOffset() / Tile.TILE_WIDTH);
        int xEnd = (int) Math.min(width, (handler.getCamera().getxOffset() + handler.getWidth())/ Tile.TILE_WIDTH + 1);
        int yStart = (int) Math.max(0, handler.getCamera().getyOffset() / Tile.TILE_HEIGHT);
        int yEnd = (int) Math.min(height, (handler.getCamera().getyOffset() + handler.getHeight()) / Tile.TILE_HEIGHT + 1);
        
        
        for (int y = yStart; y < yEnd; y++) {
            for (int x = xStart; x < xEnd; x++) {
                getTile(x, y).render(g, (int) (x * Tile.TILE_WIDTH - handler.getCamera().getxOffset()), (int) (y * Tile.TILE_HEIGHT - handler.getCamera().getyOffset()));
            } 
        }
        iManager.render(g);
        
        eManager.render(g);
    }

    public Handler getHandler() {
        return handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    public ItemManager getiManager() {
        return iManager;
    }

    public void setiManager(ItemManager iManager) {
        this.iManager = iManager;
    }
    
    public Tile getTile(int x, int y) {
        if (x < 0 || y < 0 || x >= width || y >= height ) {
            return Tile.grassTile;
        }
        
        Tile t = Tile.tiles[worldTiles[x][y]];
        if (t == null) {
            return Tile.grassTile;
        }
        
        return t;
    }
    
    private void loadWorld(String path) {
        String file = Utils.loadFileAsString(path);
        String[] tokens = file.split("\\s+");
        width = Utils.parseInt(tokens[0]);
        height = Utils.parseInt(tokens[1]);
        spawnX = Utils.parseInt(tokens[2]);
        spawnY = Utils.parseInt(tokens[3]);
        
        worldTiles = new int[width][height];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                worldTiles[x][y] = Utils.parseInt(tokens[(x + y * width) + 4]);
            }
        }
    }
    
    
    public int getWidth() {
        return this.width;
    }
    
    public int getHeight() { 
        return this.height;
    }
}
