/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TermProject.userinterface;

import TermProject.graphics.Assets;
import TermProject.graphics.Text;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 *
 * @author Deep
 */
public class UIImageButton extends UIObject {
    
    private BufferedImage[] images;
    private ClickListener clicker;

    public UIImageButton(float x, float y, int width, int height, BufferedImage[] images, ClickListener clicker) {
        super(x, y, width, height);
        this.images = images;
        this.clicker = clicker;
    }

    @Override
    public void update() {}

    @Override
    public void render(Graphics g) {
        
        if (hovering)            
            g.drawImage(images[1], (int) x + (width * 1/2), (int) y + (height * 1/2), width, height, null);
        else            
            g.drawImage(images[0], (int) x + (width * 1/2), (int) y + (height * 1/2), width, height, null);
    }

    @Override
    public void onClick() {
        clicker.onClick();
    }
    
}
