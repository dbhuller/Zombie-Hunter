/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TermProject.gFX;

import java.awt.image.BufferedImage;

/**
 *
 * @author Owner
 */
public class SpriteSheet {
    private BufferedImage spriteSheet;
    
    public SpriteSheet(BufferedImage sheet) {
       this.spriteSheet = sheet; 
    }
    
    public BufferedImage crop(int x, int y, int width, int height) {
        return spriteSheet.getSubimage(x, y, width, height);
    }
}
