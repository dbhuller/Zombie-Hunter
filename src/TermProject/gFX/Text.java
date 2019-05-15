/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TermProject.gFX;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

/**
 *
 * @author Owner
 */
public class Text {
   public static void drawString(Graphics g, String text, int xPos, int yPos, boolean center, Color c, Font font) {
       g.setColor(c);
       g.setFont(font);
       int x = xPos;
       int y = yPos;
       if (center) {
           FontMetrics f = g.getFontMetrics(font);
           x = xPos - f.stringWidth(text) / 2;
           y = (yPos - f.getHeight() / 2) + f.getAscent();
       }
       g.drawString(text, x, y);
   } 
}
