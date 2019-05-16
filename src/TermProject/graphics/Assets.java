/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TermProject.graphics;

import java.awt.Font;
import java.awt.image.BufferedImage;

/**
 * Initializes all resources used in the game. Animations are stored as BufferedImage[] and static images are BufferedImage
 *
 * @author Deep
 */
public class Assets {

    public static Font font28;
    
    public static BufferedImage zombie;
    public static BufferedImage[] zUp, zDown, zLeft, zRight;
    public static BufferedImage cobra;
    public static BufferedImage[] cUp, cDown, cLeft, cRight;
    
    public static BufferedImage player;
    public static BufferedImage[] playerDown, playerUp, playerRight, playerLeft;
    public static BufferedImage[] pAttackUp, pAttackDown, pAttackRight, pAttackLeft;
    
    public static BufferedImage[] goldCoin, silverCoin, copperCoin;
    public static BufferedImage gCoin, sCoin, cCoin;
    public static BufferedImage ruby, diamond, sapphire;
    
    public static BufferedImage[] playButton;
    
    public static BufferedImage play_Button;
    
    public static BufferedImage grass, water, cactus, sand, rock, stump, tree, wall;
    
    public static BufferedImage inventory;
    
    private static int width = 32, height = 32;
    
    
    // loads all resources
    public static void init() {
        
        font28 = FontLoader.loadFont("Resources/slkscr.ttf", 28);
        
        goldCoin = new BufferedImage[8];
        silverCoin = new BufferedImage[8];
        copperCoin = new BufferedImage[8];
        
        playerDown = new BufferedImage[4];
        playerUp = new BufferedImage[4];
        playerRight = new BufferedImage[4];
        playerLeft = new BufferedImage[4];
        
        zUp = new BufferedImage[3];
        zDown = new BufferedImage[3];
        zLeft = new BufferedImage[3];
        zRight = new BufferedImage[3];
        
        cUp = new BufferedImage[3];
        cDown = new BufferedImage[3];
        cLeft = new BufferedImage[3];
        cRight = new BufferedImage[3];

        
        pAttackUp = new BufferedImage[4];
        pAttackDown = new BufferedImage[4];
        pAttackLeft = new BufferedImage[4];
        pAttackRight = new BufferedImage[4];
        
        playButton = new BufferedImage[2];
        
        //inventory
        inventory = ImageLoader.loadImage("/Resources/inventoryScreen.png");
        
        //play button
        SpriteSheet buttonSheet = new SpriteSheet(ImageLoader.loadImage("/Resources/buttonSheet.png"));
        playButton[0] = buttonSheet.crop(0, 64, 192, 128);
        playButton[1] = buttonSheet.crop(192, 64, 192, 128);
        
//        SpriteSheet play_button_sheet = new SpriteSheet(ImageLoader.loadImage("/Resources/PlayButton.png"));
//        play_Button = play_button_sheet.crop(0, 0, 192, 128);
        
        //tree 
        SpriteSheet treeSheet = new SpriteSheet(ImageLoader.loadImage("/Resources/trees.png"));
        tree = treeSheet.crop(0, 196, 48, 60);
        
        //World objects
        SpriteSheet groundTextrueSheet = new SpriteSheet(ImageLoader.loadImage("/Resources/textures.png"));
        grass = groundTextrueSheet.crop(0, 0, width, height);
        water = groundTextrueSheet.crop(496, 396, width, height);
        cactus = groundTextrueSheet.crop(0, 296, width, height);
        sand = groundTextrueSheet.crop(32, 396, width, height);        
        rock = groundTextrueSheet.crop(0, 196, width, height);
        stump = groundTextrueSheet.crop(32, 198, width, height);
        
        //Wall Sprite
        SpriteSheet wallSheet = new SpriteSheet(ImageLoader.loadImage("/Resources/Inner.png"));
        wall = wallSheet.crop(0, 0, 16, 16);
        
        //Player Animations and Sprite
        SpriteSheet playerSheet = new SpriteSheet(ImageLoader.loadImage("/Resources/character.png"));
        player = playerSheet.crop(0, 0, 16, height);
        playerDown[0] = playerSheet.crop(0, 0, 16, height);
        playerDown[1] = playerSheet.crop(16, 0, 16, height);
        playerDown[2] = playerSheet.crop(32, 0, 16, height);
        playerDown[3] = playerSheet.crop(48, 0, 16, height);
        
        playerRight[0] = playerSheet.crop(0, 32, 16, height);
        playerRight[1] = playerSheet.crop(16, 32, 16, height);
        playerRight[2] = playerSheet.crop(32, 32, 16, height);
        playerRight[3] = playerSheet.crop(48, 32, 16, height);
        
        playerUp[0] = playerSheet.crop(0, 64, 16, height);
        playerUp[1] = playerSheet.crop(16, 64, 16, height);
        playerUp[2] = playerSheet.crop(32, 64, 16, height);
        playerUp[3] = playerSheet.crop(48, 64, 16, height);
        
        playerLeft[0] = playerSheet.crop(0, 96, 16, height);
        playerLeft[1] = playerSheet.crop(16, 96, 16, height);
        playerLeft[2] = playerSheet.crop(32, 96, 16, height);
        playerLeft[3] = playerSheet.crop(48, 96, 16, height);
        
        
        //player attack
        pAttackDown[0] = playerSheet.crop(0, 128, 32, height);
        pAttackDown[1] = playerSheet.crop(32, 128, 32, height);
        pAttackDown[2] = playerSheet.crop(64, 128, 32, height);
        pAttackDown[3] = playerSheet.crop(96, 128, 32, height);
        
        pAttackUp[0] = playerSheet.crop(0, 160, 32, height);
        pAttackUp[1] = playerSheet.crop(32, 160, 32, height);
        pAttackUp[2] = playerSheet.crop(64, 160, 32, height);
        pAttackUp[3] = playerSheet.crop(96, 160, 32, height);
        
        pAttackRight[0] = playerSheet.crop(0, 192, 32, height);
        pAttackRight[1] = playerSheet.crop(32, 192, 32, height);
        pAttackRight[2] = playerSheet.crop(64, 192, 32, height);
        pAttackRight[3] = playerSheet.crop(96, 192, 32, height);
        
        pAttackLeft[0] = playerSheet.crop(0, 224, 32, height);
        pAttackLeft[1] = playerSheet.crop(32, 224, 32, height);
        pAttackLeft[2] = playerSheet.crop(64, 224, 32, height);
        pAttackLeft[3] = playerSheet.crop(96, 224, 32, height);
        
        //cobra sheet
        SpriteSheet cobraSheet = new SpriteSheet(ImageLoader.loadImage("/Resources/blue_cobra.png"));
        cobra = cobraSheet.crop(0, 128, 64, 64);
        
        cUp[0] = cobraSheet.crop(0, 0, 64, height);
        cUp[1] = cobraSheet.crop(64, 0, 64, height);
        cUp[2] = cobraSheet.crop(128, 0, 64, height);
        
        cRight[0] = cobraSheet.crop(0, 64, 64, height);
        cRight[1] = cobraSheet.crop(64, 64, 64, height);
        cRight[2] = cobraSheet.crop(128, 64, 64, height);
        
        cDown[0] = cobraSheet.crop(0, 128, 64, height);
        cDown[1] = cobraSheet.crop(64, 128, 64, height);
        cDown[2] = cobraSheet.crop(128, 128, 64, height);
        
        cLeft[0] = cobraSheet.crop(0, 96, 64, height);
        cLeft[1] = cobraSheet.crop(64, 96, 64, height);
        cLeft[2] = cobraSheet.crop(128, 96, 64, height);
        
        
        
        //zombie sheet
        SpriteSheet zombieSheet = new SpriteSheet(ImageLoader.loadImage("/Resources/zombie.png"));
        
        
        zUp[0] = zombieSheet.crop(0, 0, 24, height);
        zUp[1] = zombieSheet.crop(24, 0, 24, height);
        zUp[2] = zombieSheet.crop(48, 0, 24, height);
        
        zRight[0] = zombieSheet.crop(0, 32, 24, height);
        zRight[1] = zombieSheet.crop(24, 32, 24, height);
        zRight[2] = zombieSheet.crop(48, 32, 24, height);
        
        zDown[0] = zombieSheet.crop(0, 64, 24, height);
        zDown[1] = zombieSheet.crop(24, 64, 24, height);
        zDown[2] = zombieSheet.crop(48, 64, 24, height);
        
        zLeft[0] = zombieSheet.crop(0, 96, 24, height);
        zLeft[1] = zombieSheet.crop(24, 96, 24, height);
        zLeft[2] = zombieSheet.crop(48, 96, 24, height);
        
        zombie = zDown[0];
        
        //coins
        SpriteSheet goldCoinSheet = new SpriteSheet(ImageLoader.loadImage("/Resources/coin_gold.png"));
        goldCoin[0] = goldCoinSheet.crop(0, 0, width, height);
        goldCoin[1] = goldCoinSheet.crop(32, 0, width, height);
        goldCoin[2] = goldCoinSheet.crop(64, 0, width, height);
        goldCoin[3] = goldCoinSheet.crop(96, 0, width, height);
        goldCoin[4] = goldCoinSheet.crop(128, 0, width, height);
        goldCoin[5] = goldCoinSheet.crop(160, 0, width, height);
        goldCoin[6] = goldCoinSheet.crop(192, 0, width, height);
        goldCoin[7] = goldCoinSheet.crop(224, 0, width, height);
        
        gCoin = goldCoin[4];
        
        
        SpriteSheet silverCoinSheet = new SpriteSheet(ImageLoader.loadImage("/Resources/coin_silver.png"));
        silverCoin[0] = silverCoinSheet.crop(0, 0, width, height);
        silverCoin[1] = silverCoinSheet.crop(32, 0, width, height);
        silverCoin[2] = silverCoinSheet.crop(64, 0, width, height);
        silverCoin[3] = silverCoinSheet.crop(96, 0, width, height);
        silverCoin[4] = silverCoinSheet.crop(128, 0, width, height);
        silverCoin[5] = silverCoinSheet.crop(160, 0, width, height);
        silverCoin[6] = silverCoinSheet.crop(192, 0, width, height);
        silverCoin[7] = silverCoinSheet.crop(224, 0, width, height);
        
        sCoin = silverCoin[4];
        
        SpriteSheet copperCoinSheet = new SpriteSheet(ImageLoader.loadImage("/Resources/coin_copper.png"));
        copperCoin[0] = copperCoinSheet.crop(0, 0, width, height);
        copperCoin[1] = copperCoinSheet.crop(32, 0, width, height);
        copperCoin[2] = copperCoinSheet.crop(64, 0, width, height);
        copperCoin[3] = copperCoinSheet.crop(96, 0, width, height);
        copperCoin[4] = copperCoinSheet.crop(128, 0, width, height);
        copperCoin[5] = copperCoinSheet.crop(160, 0, width, height);
        copperCoin[6] = copperCoinSheet.crop(192, 0, width, height);
        copperCoin[7] = copperCoinSheet.crop(224, 0, width, height);
        
        cCoin = copperCoin[4];
        
        
        SpriteSheet itemSheet = new SpriteSheet(ImageLoader.loadImage("/Resources/icons2.png"));
        ruby = itemSheet.crop(224, 192, 32, 32);
        sapphire = itemSheet.crop(160, 224, 32, 32);
        diamond = itemSheet.crop(224, 224, 32, 32);
        
        
    }
}
