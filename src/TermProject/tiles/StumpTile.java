/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TermProject.tiles;

import TermProject.graphics.Assets;

/**
 *
 * @author Deep
 */
public class StumpTile extends Tile {
    public StumpTile(int id) {
        super(Assets.stump, id);
    }
    
    @Override
    public boolean isSolid() {
        return true;
    }
}
