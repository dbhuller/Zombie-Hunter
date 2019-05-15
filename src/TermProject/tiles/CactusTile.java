/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TermProject.tiles;

import TermProject.gFX.Assets;

/**
 *
 * @author Owner
 */
public class CactusTile extends Tile {
    
    public CactusTile(int id) {
        super(Assets.cactus, id);
    }
    
    @Override
    public boolean isSolid() {
        return true;
    }
    
}
