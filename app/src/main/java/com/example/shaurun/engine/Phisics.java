package com.example.shaurun.engine;

import com.example.shaurun.visualization.GameObject;

/**
 * Created by Shaurun on 12.03.2016.
 */
public class Phisics {

    public static boolean checkCollision(GameObject go1, GameObject go2){
        float tw = go1.getSizeX();
        float th = go1.getSizeY();
        float rw = go2.getSizeX();
        float rh = go2.getSizeY();

        if (rw <= 0 || rh <= 0 || tw <= 0 || th <= 0) {
            return false;
        }

        float tx = go1.getX()-0.5f*tw;
        float ty = go1.getY()-0.5f*th;
        float rx = go2.getX()-0.5f*rw;
        float ry = go2.getY()-0.5f*rh;
        rw += rx;
        rh += ry;
        tw += tx;
        th += ty;
        //      overflow || intersect
        return ((rw < rx || rw > tx) &&
                (rh < ry || rh > ty) &&
                (tw < tx || tw > rx) &&
                (th < ty || th > ry));
    }



    //TODO: fix bug - when moving to laft-right from start, can't move
    /**
     *
     * @param go
     * @param width
     * @param heigth
     * @param lbX left bottom x coord
     * @param lbY left bottom y coord
     * @return
     */
    public static boolean checkMovingOutRegion(GameObject go, float width, float heigth, float lbX, float lbY){
        return !checkMovingWithinRegion(go, width, heigth, lbX, lbY);
    }

    public static boolean checkMovingWithinRegion(GameObject go, float width, float heigth, float lbX, float lbY){
        float tw = go.getSizeX();
        float th = go.getSizeY();

        if (width <= 0 || heigth <= 0 || tw > width || th > heigth) {
            return false;
        }

        float top = go.getY()+0.5f*th;
        float bottom = top-th;
        float right = go.getX()+0.5f*tw;
        float left = right-tw;

        return  top <= lbY+heigth &&
                bottom >= lbY &&
                right <= lbX+width &&
                left >= lbX;
    }
}
