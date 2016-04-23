package com.example.shaurun.engine;

import java.util.Date;

/**
 * Created by Shaurun on 13.03.2016.
 */
public class Time {
    private static final long DAMPING = 12000000;

    private static long curTime;
    private static long lastTime;

    public static long getTime(){
        return System.nanoTime();
    }

    public static float getDelta(){
        return (curTime - lastTime) / DAMPING;
    }

    public static void update(){
        lastTime = curTime;
        curTime = getTime();
    }

    public static void init(){
        curTime = getTime();
        lastTime = getTime();
    }
}
