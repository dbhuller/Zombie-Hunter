/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TermProject;

import TermProject.Display.DisplayFrame;
import TermProject.graphics.Assets;
import TermProject.graphics.GameCamera;
import TermProject.graphics.ImageLoader;
import TermProject.graphics.SpriteSheet;
import TermProject.input.KeyManager;
import TermProject.input.MouseManager;
import TermProject.states.GameState;
import TermProject.states.MenuState;
import TermProject.states.State;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

/**
 *
 * 
 * 
 * @author Deep
 */
public class Game implements Runnable {
    
   private DisplayFrame display;
   public String title;
   private int width, height;
   
   private Thread thread;    
   private boolean isRunning = false;
   
   private BufferStrategy bs;
   private Graphics g;
   
   //states
   public State gameState;
   public static State menuState;
   
   //input
   private KeyManager keyManager;
   private MouseManager mouseManager;
   
   //camera
   private GameCamera camera;
   
   //handler
   private Handler handler;
   
   
   public Game(String title, int width, int height) {
       this.width = width;
       this.height = height;
       this.title = title;
       keyManager = new KeyManager();
       mouseManager = new MouseManager();
   } 
   
   
   
   // initializes all components of the game
   private void init() {
       
       display = new DisplayFrame(title, width, height);
       display.getFrame().addKeyListener(keyManager);
       display.getFrame().addMouseListener(mouseManager);
       display.getFrame().addMouseMotionListener(mouseManager);
       display.getCanvas().addMouseListener(mouseManager);
       display.getCanvas().addMouseMotionListener(mouseManager);
       
       Assets.init();
       
       handler = new Handler(this);
       camera = new GameCamera(handler, 0, 0);
       
       
       gameState = new GameState(handler);
       menuState = new MenuState(handler);
       State.setState(menuState);
    
   }
   
   private void update() {
       keyManager.update();
       
       if (State.getState() != null) {
           State.getState().update();
       }
   }
   
   private void render() {
     bs = display.getCanvas().getBufferStrategy();
     if(bs == null) {
         display.getCanvas().createBufferStrategy(3);
         return;
     }
     //paintbursh
     g = bs.getDrawGraphics();
     
     //clear screen
     g.clearRect(0, 0, width, height);
          
     //begin drawing     
     
     //g.drawImage(Assets.grass, width, height, null);
     
    if (State.getState() != null) {
        State.getState().render(g);
    }
     
     //end drawing     
     bs.show();
     g.dispose();
     
     
   }
    
   @Override
   public void run() {
       init();
       
       //fps timer code from game development forum
       int fps = 60;    //update and render 60times per second
       double timePerUpdate = 1000000000 / fps;
       double delta = 0;
       long now;
       long lastTime = System.nanoTime();
       long timer = 0;
       int ticks = 0;
       
       while(isRunning) {
           now = System.nanoTime();
           delta += (now - lastTime) / timePerUpdate;
           timer += now - lastTime;
           lastTime = now;
           
           if (delta >= 1) {
            update();
            render();
            ticks++;
            delta--;
           }
           
           if (timer >= 1000000000) {
               System.out.println("Ticks and Frames: " + ticks);
               ticks = 0;
               timer = 0;
           }
           
       }
       
       stop();
   }
   
   public KeyManager getKeyManager() {
       return keyManager;
   }
   
   public MouseManager getMouseManager() {
       return mouseManager;
   }
   
   public GameCamera getCamera() {
       return camera;
   }
   
   public int getWidth() {
       return width;
   }
   
   public int getHeight() {
       return height;
   }
   
    public synchronized void start() {
       if(isRunning)
           return;
       isRunning = true;
       thread = new Thread(this);
       //calls run method
       thread.start();
    }
   
    public synchronized void stop() {
        if(!isRunning)
            return;
        isRunning = false;
       try {
           thread.join();
       } catch (InterruptedException ex) {
           ex.printStackTrace();
       }
           
    }

}
    
