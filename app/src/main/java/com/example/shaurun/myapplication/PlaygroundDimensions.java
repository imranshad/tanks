package com.example.shaurun.myapplication;

/**
 * Created by Shaurun on 03.04.2016.
 */
public class PlaygroundDimensions {
    private static float leftX;
    private static float bottomY;
    private static float rightX;
    private static float topY;
    private static float side;
    private static float cellSize;
    private static final int FIELD_SIZE = 13;

    public static void evaluate(float width, float height){
        side = width < height ? width : height;
        cellSize = side/FIELD_SIZE;

        float restWidth = width - side;
        float restHeight = height - side;

        leftX = 0.5f*restWidth;
        bottomY = 0.5f*restHeight;
        rightX = leftX+side;
        topY = bottomY+side;
    }

    public static float getWidth(){
        return side;
    }

    public static float getHeight(){
        return side;
    }

    public static float getX(int rightShift){
        return leftX+(rightShift+0.5f)*cellSize;
    }

    public static float getY(int topShift){
        return bottomY+(topShift+0.5f)*cellSize;
    }

    public static float getLeftX(){
        return leftX;
    }

    public static float getBottomY() {
        return bottomY;
    }

    public static float getCellSize(){
        return cellSize;
    }
}
