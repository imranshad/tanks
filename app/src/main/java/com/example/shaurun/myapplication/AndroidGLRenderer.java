package com.example.shaurun.myapplication;

import android.opengl.GLSurfaceView;

import com.example.shaurun.engine.Time;
import com.example.shaurun.visualization.GameObject;
import java.util.List;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * Created by Shaurun on 27.02.2016.
 */
public class AndroidGLRenderer implements GLSurfaceView.Renderer {
//    final int TICKS_PER_SECOND = 25;
//    final int SKIP_TICKS = 1000 / TICKS_PER_SECOND;
//    final int MAX_FRAMESKIP = 5;


    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        Time.init();
        gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        gl.glViewport(0, 0, width, height);
        PlaygroundDimensions.evaluate(width, height);
        Game.game.init();
        //Game.game.init(width, height);

        // Select the projection matrix
        gl.glMatrixMode(GL10.GL_PROJECTION);
        // Reset the projection matrix
        gl.glLoadIdentity();
        //Calculate the aspect ratio of the window
        gl.glOrthof(0, width, 0, height, -1, 1);
        //gl.glFrustumf(0, width, 0, height, 0.1f, 1);
        // Select the modelview matrix
        gl.glMatrixMode(GL10.GL_MODELVIEW);
    }

    @Override
    public void onDrawFrame(GL10 gl) {
        // Redraw background color
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);

        Time.update();

       // Game.game.loop(gl);
//        long nextGameTick =  SystemClock.elapsedRealtime();
//        int loops;
//        float interpolation;
//
//        loops = 0;
//        while( SystemClock.elapsedRealtime() > nextGameTick && loops < MAX_FRAMESKIP) {
//            Game.game.update();
//
//            nextGameTick += SKIP_TICKS;
//            loops++;
//        }
//
//        interpolation = (float)(SystemClock.elapsedRealtime() + SKIP_TICKS - nextGameTick) / (float)SKIP_TICKS;
//        Game.game.render(gl, interpolation);



        Game.game.update();
        Game.game.render(gl);
    }


    public static List<GameObject> rectangleCollide(GameObject gameObject) {
        return Game.rectangleCollide(gameObject);
    }

    public static void addObject(GameObject go){
        Game.game.dinamicAdd(go);
    }
}
