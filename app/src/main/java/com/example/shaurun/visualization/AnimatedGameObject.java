package com.example.shaurun.visualization;

import javax.microedition.khronos.opengles.GL10;

/**
 * Created by Shaurun on 12.03.2016.
 */
public class AnimatedGameObject extends TexturedGameObject {
    private boolean isMoving = false;

    protected void init(float x, float y, float sizeX, float sizeY, int resId, int animationLength){
        this.x = x;
        this.y = y;
        sprite = new AnimatedSprite(sizeX, sizeY, resId, animationLength);
    }

    protected void init(float x, float y, float sizeX, float sizeY, int resId, int animationLengthX, int animationLengthY){
        this.x = x;
        this.y = y;
        sprite = new AnimatedSprite(sizeX, sizeY, resId, animationLengthX, animationLengthY);
    }

    public void startAnimation(){
        isMoving = true;
        //((AnimatedSprite) sprite).nextFrameDelay.terminate();
    }

    public void stopAnimation(){
        isMoving = false;
    }

    @Override
    public void render(GL10 gl){
        super.render(gl);

        if(isMoving){
            ((AnimatedSprite) sprite).nextFrame();
        }
    }
}
