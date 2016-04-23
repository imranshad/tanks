package com.example.shaurun.playObjects;

import com.example.shaurun.myapplication.R;
import com.example.shaurun.visualization.AnimatedGameObject;
import com.example.shaurun.visualization.AnimatedSprite;

/**
 * Created by Shaurun on 13.03.2016.
 */
public class Bang extends AnimatedGameObject {
    public Bang(float x, float y, float sizeX, float sizeY) {
        super();
        init(x, y, sizeX, sizeY, R.drawable.bang, 12, 1);
        startAnimation();
    }

    @Override
    public void update() {
        super.update();

        if( ((AnimatedSprite) sprite).playedOnce ){
            remove();
        }
    }
}
