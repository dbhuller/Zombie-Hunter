/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TermProject.states;

import TermProject.Game;
import TermProject.Handler;
import TermProject.entities.creatures.Player;
import TermProject.entities.statics.Cactus;
import TermProject.entities.statics.Rock;
import TermProject.entities.statics.Stump;
import TermProject.entities.statics.Tree;
import TermProject.gFX.Assets;
import TermProject.tiles.Tile;
import TermProject.world.World;
import java.awt.Graphics;

/**
 *
 * @author Owner
 */
public class GameState extends State {
    
//    private Player player;
    
    private World world1;
    
//    private Rock rock;
//    private Tree tree;
//    private Stump stump;
//    private Cactus cactus;

    public GameState(Handler handler) {
        super(handler);
      //some code here
      world1 = new World(handler, "Resources/world1.txt");
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
//        player.update();
//        rock.update();
//        tree.update();
//        stump.update();
//        cactus.update();
    }

    @Override
    public void render(Graphics g) {
        world1.render(g);
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
