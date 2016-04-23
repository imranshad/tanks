package com.example.shaurun.playObjects;

import com.example.shaurun.myapplication.Activities;
import com.example.shaurun.myapplication.AndroidGLRenderer;
import com.example.shaurun.myapplication.R;
import com.example.shaurun.visualization.GameObject;
import com.example.shaurun.visualization.TexturedGameObject;

/**
 * Created by Shaurun on 13.03.2016.
 */
public class Bullet extends TexturedGameObject {
    private Movement direction;
    private float speed = 10.0f;
    private BulletType bulletType;

    public Bullet(float x, float y, float sizeX, float sizeY, Movement direction, BulletType bulletType) {
        super();
        init(x, y, sizeX, sizeY, R.drawable.bullet);
        this.direction = direction;
        this.bulletType = bulletType;
    }

    @Override
    public void update() {
        switch (direction){
            case LEFT: x-=speed; angle = 90; break;
            case RIGHT: x+=speed; angle = 270; break;
            case UP: y+=speed; angle = 0; break;
            case DOWN: y-=speed; angle = 180; break;
        }

        for (GameObject go : AndroidGLRenderer.rectangleCollide(this)) {
            if (go instanceof BreakableWall){
                AndroidGLRenderer.addObject(new Bang(x, y, 100, 100));
                remove();
                go.remove();
            }

            if (go instanceof Government){
                AndroidGLRenderer.addObject(new Bang(x, y, 100, 100));
                remove();
                go.remove();
            }

            if(bulletType == BulletType.ENEMY && go instanceof PlayerTank){
                AndroidGLRenderer.addObject(new Bang(x, y, 100, 100));
                remove();
                ((PlayerTank) go).die();
            }

            if(bulletType == BulletType.PLAYER && go instanceof EnemyTank){
                AndroidGLRenderer.addObject(new Bang(x, y, 100, 100));
                remove();
                ((EnemyTank) go).die();
            }

            if(bulletType == bulletType.PLAYER && go instanceof Bullet){
                remove();
                go.remove();
            }
        }

        if(x<-50 || x >3000 || y <0 || y > 5000){
            remove();
        }
    }
}
