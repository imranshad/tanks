package com.example.shaurun.visualization;

/**
 * Created by Shaurun on 28.02.2016.
 */
public class TexturedGameObject extends GameObject {
    public TexturedGameObject(){}

    protected void init(float x, float y, float sizeX, float sizeY, int resId){
        this.x = x;
        this.y = y;
        sprite = new TexturedSprite(sizeX, sizeY, resId);
    }
}
