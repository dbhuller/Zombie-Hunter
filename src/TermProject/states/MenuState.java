/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TermProject.states;

import TermProject.Game;
import TermProject.Handler;
import TermProject.gFX.Assets;
import TermProject.userinterface.ClickListener;
import TermProject.userinterface.UIImageButton;
import TermProject.userinterface.UIManager;
import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author Owner
 */
public class MenuState extends State {

    private UIManager uiManager;
    
    public MenuState(Handler handler) {
        super(handler);
        uiManager = new UIManager(handler);
        handler.getMouseManager().setUIManager(uiManager);
        
        uiManager.addObject(new UIImageButton(150, 200, 192, 128, Assets.playButton, new ClickListener(){
            @Override
            public void onClick() {
                handler.getMouseManager().setUIManager(null);
                State.setState(handler.getGame().gameState);
            }
        }));
    }

    @Override
    public void update() {
        uiManager.update();
    }

    @Override
    public void render(Graphics g) {
        uiManager.render(g);
    }
    
    
    
}
