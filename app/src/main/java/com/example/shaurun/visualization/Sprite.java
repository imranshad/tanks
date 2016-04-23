package com.example.shaurun.visualization;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

import javax.microedition.khronos.opengles.GL10;

import static com.example.shaurun.visualization.BaseTypeSize.FLOAT_SIZE;
import static com.example.shaurun.visualization.BaseTypeSize.SHORT_SIZE;

/**
 * Created by Shaurun on 28.02.2016.
 */
public class Sprite {
    private float sizeX, sizeY;
    private FloatBuffer vertexBuffer;
    private ShortBuffer indexBuffer;

    public Sprite(float sizeX, float sizeY){
        setSizeX(sizeX);
        setSizeY(sizeY);
        setVertexAndIndexBuffers();
    }

    public void setSizeX(float sizeX){
        if (sizeX < 0){
            sizeX = 0.0f;
        }

        this.sizeX = sizeX;
    }

    public void setSizeY(float sizeY){
        if (sizeY < 0){
            sizeY = 0.0f;
        }

        this.sizeY = sizeY;
    }

    public float getSizeX(){
        return sizeX;
    }

    public float getSizeY(){
        return sizeY;
    }

    public void render(GL10 gl){
        // Counter-clockwise winding.
        gl.glFrontFace(GL10.GL_CCW);
        // Enabled the vertices buffer for writing and to be used during rendering.
        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
        // Specifies the location and data format of an array of vertex coordinates to use when rendering.
        gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vertexBuffer);
        //draw
        gl.glDrawElements(GL10.GL_TRIANGLES, getVertecesCount(), GL10.GL_UNSIGNED_SHORT, indexBuffer);
        // Disable the vertices buffer.
        gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
    }

    private void setVertexAndIndexBuffers(){
        float[] vertices = {
//                0.0f, sizeY, 0.0f,  //top left
//                0.0f, 0.0f, 0.0f,   //bottom left
//                sizeX, 0.0f, 0.0f,  //bottom right
//                sizeX, sizeY, 0.0f, //top right

                -sizeX/2, sizeY/2, 0.0f,  //top left
                -sizeX/2, -sizeY/2, 0.0f,   //bottom left
                sizeX/2, -sizeY/2, 0.0f,  //bottom right
                sizeX/2, sizeY/2, 0.0f, //top right
        };

        short[] indeces = {
                0, 1, 2,
                0, 2, 3,
        };

        ByteBuffer vbb = ByteBuffer.allocateDirect(vertices.length * FLOAT_SIZE.value());
        vbb.order(ByteOrder.nativeOrder());
        vertexBuffer = vbb.asFloatBuffer();
        vertexBuffer.put(vertices);
        vertexBuffer.position(0);

        ByteBuffer ibb = ByteBuffer.allocateDirect(vertices.length * SHORT_SIZE.value());
        ibb.order(ByteOrder.nativeOrder());
        indexBuffer = ibb.asShortBuffer();
        indexBuffer.put(indeces);
        indexBuffer.position(0);
    }

    protected int getVertecesCount(){
        return 6;
    }
}
