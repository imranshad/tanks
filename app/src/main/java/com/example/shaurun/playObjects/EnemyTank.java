package com.example.shaurun.playObjects;

import com.example.shaurun.engine.Delay;
import com.example.shaurun.myapplication.AndroidGLRenderer;
import com.example.shaurun.myapplication.Game;
import com.example.shaurun.myapplication.R;
import com.example.shaurun.visualization.AnimatedGameObject;
import com.example.shaurun.visualization.GameObject;

/**
 * Created by Shaurun on 20.03.2016.
 */
 //TODO: common methods move to somewhere
public class EnemyTank extends AnimatedGameObject{
    public static final float DAMPING = 0.5f;
    private Delay attackDelay;
    public Movement movement = Movement.STOP;
    public Movement direction = Movement.DOWN;

    public EnemyTank(float x, float y, float sizeX, float sizeY){
        super();
        init(x, y, sizeX, sizeY, R.drawable.tanks, 8);
        attackDelay = new Delay(2000); //2 sec
        attackDelay.terminate();
    }

    @Override
    public void update(){
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
            if (go.isSolid()){
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
        if(attackDelay.isOver()) {
            AndroidGLRenderer.addObject(new Bullet(x, y, getSizeX() / 2, getSizeY() / 2, direction, BulletType.ENEMY));
            attackDelay.restart();
        }
    }

    public void die() {
        remove();
    }

    @Override
    public boolean isSolid(){
        return true;
    }
}