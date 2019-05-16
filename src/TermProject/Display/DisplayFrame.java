/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TermProject.Display;

import java.awt.Canvas;
import java.awt.Dimension;
import javax.swing.JFrame;

/**
 *
 * @author Deep
 */
public class DisplayFrame {
    
   // main window
   private JFrame frame;
   private String frameTitle;
   private int frameWidth, frameHeight;
   
   //graphics drawn to canvas
   private Canvas canvas;
   
   public DisplayFrame(String title, int width, int height) {
       this.frameTitle = title;
       this.frameWidth = width;
       this.frameHeight = height;
       
       createDisplay();
      
   }
   
   
   private void createDisplay() {
       frame = new JFrame(frameTitle);
       frame.setSize(frameWidth, frameHeight);
       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       frame.setResizable(false);
       frame.setLocationRelativeTo(null);
       frame.setVisible(true);
       
       canvas = new Canvas();
       canvas.setPreferredSize(new Dimension(frameWidth, frameHeight));
       canvas.setMaximumSize(new Dimension(frameWidth, frameHeight));
       canvas.setMinimumSize(new Dimension(frameWidth, frameHeight));
       canvas.setFocusable(false);
       
       frame.add(canvas);
       frame.pack();
   }
   
   public Canvas getCanvas() {
       return canvas;
   }
   
   public JFrame getFrame() {
       return frame;
   }
   
}
