package com.example.shaurun.playObjects;

import com.example.shaurun.myapplication.R;
import com.example.shaurun.visualization.TexturedGameObject;

/**
 * Created by Shaurun on 12.03.2016.
 */
public class BreakableWall extends TexturedGameObject {
    public BreakableWall(float x, float y, float sizeX, float sizeY) {
        super();
        init(x, y, sizeX, sizeY, R.drawable.brik_wall);
    }


    @Override
    public boolean isSolid(){
        return true;
    }
}
