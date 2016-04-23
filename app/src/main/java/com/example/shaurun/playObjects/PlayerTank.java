package com.example.shaurun.playObjects;

import com.example.shaurun.myapplication.AndroidGLRenderer;
import com.example.shaurun.myapplication.Game;
import com.example.shaurun.myapplication.R;
import com.example.shaurun.visualization.AnimatedGameObject;
import com.example.shaurun.visualization.GameObject;

/**
 * Created by Shaurun on 02.03.2016.
 */
public class PlayerTank extends AnimatedGameObject{
    public Movement movement = Movement.STOP;
    public Movement direction = Movement.RIGHT;
    private int lifes = 3;

    final float initialX;
    final float initialY;

    final float realX, realY;

    public PlayerTank(float x, float y, float sizeX, float sizeY) {
        super();
        init(x, y, sizeX, sizeY, R.drawable.tanks, 8);
        initialX = x;
        initialY = y;

        realX = sizeX; realY = sizeY;
    }

    @Override
    public void update(){
        if(getSizeX() < realX){
            setSizeX(getSizeX()+0.1f);
        }

        if(getSizeY() < realY){
            setSizeY(getSizeY()+0.1f);
        }

        if(movement != Movement.STOP){
            startAnimation();
        }

        float prewX = x, prewY = y;
        int speed = 5;

        switch (movement){
            case LEFT: x-=speed; angle = 180; break;
            case RIGHT: x+=speed; angle = 0; break;
            case UP: y+=speed; angle = 90; break;
            case DOWN: y-=speed; angle = 270; break;
            case STOP: stopAnimation(); break;
        }

        if(Game.goesOutField(this)){
            x = prewX; y = prewY; return;
        }

        for (GameObject go : AndroidGLRenderer.rectangleCollide(this)) {
            if (go instanceof BreakableWall){
                x = prewX;
                y = prewY;
            }
        }
    }

    public void setMovement(Movement movement){
        if (this.movement != Movement.STOP){
            direction = this.movement;
        }
        this.movement = movement;

    }

    public void fire(){
        AndroidGLRenderer.addObject(new Bullet(x, y, getSizeX()/2, getSizeY()/2, direction, BulletType.PLAYER));
    }

    public void die() {
        lifes--;

        x = initialX;
        y = initialY;
        respawn();

        if(lifes <= 0){
            remove();
        }
    }

    protected void respawn(){
        setSizeX(0);
        setSizeY(0);
    }

    @Override
    public boolean isSolid(){
        return true;
    }
}
