/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TermProject.states;

import TermProject.Game;
import TermProject.Handler;
import TermProject.entities.Entity;
import TermProject.entities.creatures.Player;
import TermProject.entities.statics.Cactus;
import TermProject.entities.statics.Rock;
import TermProject.entities.statics.Stump;
import TermProject.entities.statics.Tree;
import TermProject.graphics.Assets;
import TermProject.tiles.Tile;
import TermProject.world.World;
import java.awt.Graphics;

/**
 * GameState for the game, loads main world
 * attempted to load other worlds but couldnt get it to work
 * 
 * @author Deep
 */
public class GameState extends State {
    
//    private Player player;
    
    private World world1;
    private World world2;
    
//    private Rock rock;
//    private Tree tree;
//    private Stump stump;
//    private Cactus cactus;

    public GameState(Handler handler) {
        super(handler);
      //some code here
      world1 = new World(handler, "Resources/world1.txt");
//      world2 = new World(handler, "Resources/world2.txt");
      handler.setWorld(world1);
      
      
//      player = new Player(handler, 100, 100);
//      rock = new Rock(handler, 300, 300);
//      tree = new Tree(handler, 400, 500);
//      stump = new Stump(handler, 200, 200);
//      cactus = new Cactus(handler, 700, 900);
       
    }
    
    @Override
    public void update() {
        world1.update();
        
//        if (handler.getWorld().geteManager().getEntities().size() == 1) {
//            if (handler.getWorld().geteManager().getPlayer().isActive()) {
//                handler.setWorld(world2);
//            } else {
//                world1.update();
//            }
//        }
//        if (handler.getWorld().geteManager().getEntities().size() <= 1) {
//            handler.setWorld(world2);
//            world2.update();
//        }
//        player.update();
//        rock.update();
//        tree.update();
//        stump.update();
//        cactus.update();
    }

    @Override
    public void render(Graphics g) {
        world1.render(g);
        
//        if (handler.getWorld().geteManager().getEntities().size() == 1) {
//            if (handler.getWorld().geteManager().getPlayer().isActive()) {
//                world2.render(g);
//            } else {
//                world1.render(g);
//            }
//        }
        
//        if (handler.getWorld().geteManager().getEntities().size() <= 1) {
//            handler.setWorld(world2);
//            world2.render(g);
//        }
  
//        player.render(g);
//        rock.render(g);
//        tree.render(g);
//        stump.render(g);
//        cactus.render(g);
        
//        Tile.tiles[0].render(g, 0, 0);
//        Tile.tiles[1].render(g, 200, 200);
//        Tile.tiles[2].render(g, 300, 300);
//        Tile.tiles[3].render(g, 400, 400);
    }
    
}
