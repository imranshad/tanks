package com.example.shaurun.myapplication;

import android.app.Activity;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.shaurun.playObjects.Movement;

/**
 * Created by Shaurun on 27.02.2016.
 */
public class AndroidMainActivity extends Activity {
    public GLSurfaceView surfaceView;
    Button btnMoveLeft/* = (Button) findViewById(R.id.btnLeft)*/;
    Button btnMoveRight /*= (Button) findViewById(R.id.btnRight)*/;
    Button btnMoveUp /*= (Button) findViewById(R.id.btnUp)*/;
    Button btnMoveDown /*= (Button) findViewById(R.id.btnDown)*/;
    Button btnFire;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        surfaceView = new AndroidGLSurfaceView(this);
        //setContentView(surfaceView);


        setContentView(R.layout.main_activity);
        final LinearLayout surface = (LinearLayout)findViewById(R.id.view);
        surface.addView(surfaceView);
        Game.game = new Game();


        btnMoveLeft = (Button) findViewById(R.id.btnLeft);
        btnMoveRight = (Button) findViewById(R.id.btnRight);
        btnMoveUp = (Button) findViewById(R.id.btnUp);
        btnMoveDown = (Button) findViewById(R.id.btnDown);
        btnFire = (Button) findViewById(R.id.btnFire);

        if(btnMoveLeft != null) {

            btnFire.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Game.game.getPlayer().fire();
                }
            });

            btnMoveLeft.setOnTouchListener(new View.OnTouchListener() {

                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    switch (event.getAction()) {
                        case MotionEvent.ACTION_DOWN:
//                        ((AndroidGLSurfaceView) surfaceView).getRenderer().player.setMovement(Movement.LEFT);
                            Game.game.getPlayer().setMovement(Movement.LEFT);
                            return true;
                        case MotionEvent.ACTION_UP:
                            //((AndroidGLSurfaceView) surfaceView).getRenderer().player.setMovement(Movement.STOP);
                            Game.game.getPlayer().setMovement(Movement.STOP);
                            return true;
                    }
                    return false;
                }
            });

            btnMoveRight.setOnTouchListener(new View.OnTouchListener() {

                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    switch (event.getAction()) {
                        case MotionEvent.ACTION_DOWN:
                            //((AndroidGLSurfaceView) surfaceView).getRenderer().player.setMovement(Movement.RIGHT);
                            Game.game.getPlayer().setMovement(Movement.RIGHT);
                            return true;
                        case MotionEvent.ACTION_UP:
                            //((AndroidGLSurfaceView) surfaceView).getRenderer().player.setMovement(Movement.STOP);
                            Game.game.getPlayer().setMovement(Movement.STOP);
                            return true;
                    }
                    return false;
                }
            });

            btnMoveUp.setOnTouchListener(new View.OnTouchListener() {

                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    switch (event.getAction()) {
                        case MotionEvent.ACTION_DOWN:
                            //((AndroidGLSurfaceView) surfaceView).getRenderer().player.setMovement(Movement.UP);
                            Game.game.getPlayer().setMovement(Movement.UP);
                            return true;
                        case MotionEvent.ACTION_UP:
                            //((AndroidGLSurfaceView) surfaceView).getRenderer().player.setMovement(Movement.STOP);
                            Game.game.getPlayer().setMovement(Movement.STOP);
                            return true;
                    }
                    return false;
                }
            });

            btnMoveDown.setOnTouchListener(new View.OnTouchListener() {

                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    switch (event.getAction()) {
                        case MotionEvent.ACTION_DOWN:
                            //((AndroidGLSurfaceView) surfaceView).getRenderer().player.setMovement(Movement.DOWN);
                            Game.game.getPlayer().setMovement(Movement.DOWN);
                            return true;
                        case MotionEvent.ACTION_UP:
                            //((AndroidGLSurfaceView) surfaceView).getRenderer().player.setMovement(Movement.STOP);
                            Game.game.getPlayer().setMovement(Movement.STOP);
                            return true;
                    }
                    return false;
                }
            });

        }

        Activities.activities.add(this);
    }

    @Override
    public void finish(){
        Activities.activities.remove(this);
        super.finish();
    }
}
