package com.example.shaurun.playObjects;

import com.example.shaurun.myapplication.Game;
import com.example.shaurun.myapplication.R;
import com.example.shaurun.visualization.TexturedGameObject;

/**
 * Created by Shaurun on 17.03.2016.
 */
public class Government extends TexturedGameObject{
    public Government(float x, float y, float sizeX, float sizeY) {
        super();
        init(x, y, sizeX, sizeY, R.drawable.government);
    }

    @Override
    public void remove(){
        super.remove();
        Game.game.over();
    }

    @Override
    public boolean isSolid(){
        return true;
    }
}
