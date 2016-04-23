package com.example.shaurun.visualization;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import static com.example.shaurun.visualization.BaseTypeSize.FLOAT_SIZE;

/**
 * Created by Shaurun on 12.03.2016.
 */
public class AnimatedSprite extends TexturedSprite {
    final int animationLengthX;
    final int animationLengthY;
    int curFrame;
    boolean isMoving;
    public boolean playedOnce = false;
    //public Delay nextFrameDelay = new Delay(1000);


    public AnimatedSprite(float sizeX, float sizeY, int textureResId, int animationLength) {
        super(sizeX, sizeY, textureResId);
        this.animationLengthX = animationLength;
        this.animationLengthY = animationLength;
        curFrame = 0;
        isMoving = false;
    }

    public AnimatedSprite(float sizeX, float sizeY, int textureResId, int animationLengthX, int animationLengthY) {
        super(sizeX, sizeY, textureResId);
        this.animationLengthX = animationLengthX;
        this.animationLengthY = animationLengthY;
        curFrame = 0;
        isMoving = false;
    }

    public int getCurFrame(){
        return curFrame;
    }

    public int getAnimationLength(){
        return animationLengthX;
    }

    public void nextFrame(){
        //if(nextFrameDelay.isOver()) {
            if (curFrame < animationLengthX) {
                curFrame++;
            } else {
                curFrame = 0;
                playedOnce = true;
            }
            //nextFrameDelay.restart();
            setTextureCoordsBuffer();
        //}
    }

    @Override
    protected void setTextureCoordsBuffer() {
        float[] textureCoords = {
                curFrame/(float)animationLengthX, 0.0f/(float)animationLengthY, //top left
                curFrame/(float)animationLengthX, 1.0f/(float)animationLengthY, //bottom left
                (curFrame+1)/(float)animationLengthX, 1.0f/(float)animationLengthY, //bottom right
                (curFrame+1)/(float)animationLengthX, 0.0f/(float)animationLengthY, //top right
        };

        ByteBuffer byteBuf = ByteBuffer.allocateDirect(textureCoords.length * FLOAT_SIZE.value());
        byteBuf.order(ByteOrder.nativeOrder());
        textureCoordsBuffer = byteBuf.asFloatBuffer();
        textureCoordsBuffer.put(textureCoords);
        textureCoordsBuffer.position(0);
    }
}
