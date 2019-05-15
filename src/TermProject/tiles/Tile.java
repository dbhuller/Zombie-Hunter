/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TermProject.tiles;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 *
 * @author Owner
 */
public class Tile {
    
    public static Tile[] tiles = new Tile[256];
    public static Tile grassTile = new GrassTile(0);
    public static Tile waterTile = new WaterTile(1);
    public static Tile cactusTile = new CactusTile(2);
    public static Tile sandTile = new SandTile(3);
    public static Tile rockTile = new RockTile(4);
    public static Tile stumpTile = new StumpTile(5);
    public static Tile wallTile = new WallTile(6);
    
    protected BufferedImage texture;
    protected final int id;
    public static final int TILE_WIDTH = 64, TILE_HEIGHT = 64;
    
    public Tile(BufferedImage texture, int id) {
        this.texture = texture;
        this.id = id;
        
        tiles[id] = this;
    }
    
    public int getId() {
        return id;
    }
    
    public void update() {
        
    }
    
    public void render(Graphics g, int x, int y) {
        g.drawImage(texture, x, y, TILE_WIDTH, TILE_HEIGHT, null);
    }
    
    public boolean isSolid() {
        return false;
    }
        
        
    
}
