/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TermProject.graphics;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * Class to load external font to be used in game with specified size
 * 
 * @author Deep
 */
public class FontLoader {
    
 public static Font loadFont(String path, float size) {
     try {
         return Font.createFont(Font.TRUETYPE_FONT, new File(path)).deriveFont(Font.PLAIN, size);
     } catch (FontFormatException | IOException e) {
         Logger.getLogger(FontLoader.class.getName()).log(Level.SEVERE, null, e);
         System.exit(1);
     }
     return null;
 }  
 
}
