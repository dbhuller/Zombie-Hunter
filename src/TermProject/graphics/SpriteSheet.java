/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TermProject.graphics;

import java.awt.image.BufferedImage;

/**
 * Creates BufferedImage of sprite sheet desired to use
 * Crop() cuts out specified portion of spritesheet by getting the subimage
 *
 * @author Deep
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
