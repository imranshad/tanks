package com.example.shaurun.visualization;

import javax.microedition.khronos.opengles.GL10;

/**
 * Created by Shaurun on 28.02.2016.
 */
public class GameObject {
    protected float x, y;
    protected Sprite sprite;
    protected float angle = 0;
    protected boolean remove = false;

    public GameObject(){}

    public float getX(){
        return x;
    }

    public float getY(){
        return y;
    }

    public float getSizeX(){
        return sprite.getSizeX();
    }

    public float getSizeY(){
        return sprite.getSizeY();
    }

    public void render(GL10 gl){
        gl.glPushMatrix();
        {
            gl.glTranslatef(x, y, 0.0f);
            gl.glRotatef(angle, 0.0f, 0.0f, 1.0f);
            sprite.render(gl);
        }
        gl.glPopMatrix();
    }

    public void render(GL10 gl, float interpolation){
        gl.glPushMatrix();
        {
            gl.glTranslatef(x, y, 0.0f);
            gl.glRotatef(angle, 0.0f, 0.0f, 1.0f);
            sprite.render(gl);
        }
        gl.glPopMatrix();
    }

    public void update(){
    }

    protected void init(float x, float y, float sizeX, float sizeY){
        this.x = x;
        this.y = y;
        sprite = new Sprite(sizeX, sizeY);
    }

    public void remove(){
        remove = true;
    }

    public boolean getRemove(){
        return remove;
    }

    public void setSizeX(float sizeX) {
        sprite.setSizeX(sizeX);
    }

    public void setSizeY(float sizeY) {
        sprite.setSizeY(sizeY);
    }

    public boolean isSolid(){
        return false;
    }
}
