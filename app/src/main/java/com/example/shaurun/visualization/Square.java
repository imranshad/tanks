package com.example.shaurun.visualization;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

import javax.microedition.khronos.opengles.GL10;

import static com.example.shaurun.visualization.BaseTypeSize.*;

/**
 * Created by Shaurun on 28.02.2016.
 */
public class Square {
    private float vertices[] = {
            -0.99f, 0.99f, 0.0f, //top left
            -0.99f, -0.99f, 0.0f, //bottom left
            0.99f, -0.99f, 0.0f, //bottom right
            0.99f, 0.99f, 0.0f, //top right
    };

    private short indeces[] = {
            0, 1, 2,
            0, 2, 3,
    };

    private FloatBuffer vertexBuffer;
    private ShortBuffer indexBuffer;

    public Square(){
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

    /**
     * This function draws our square on screen.
     * @param gl
     */
    public void draw(GL10 gl) {
        // Counter-clockwise winding.
        gl.glFrontFace(GL10.GL_CCW);
        // Enable face culling.
        //gl.glEnable(GL10.GL_CULL_FACE);
        // What faces to remove with the face culling.
        //gl.glCullFace(GL10.GL_BACK);

        // Enabled the vertices buffer for writing and to be used during
        // rendering.
        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
        // Specifies the location and data format of an array of vertex
        // coordinates to use when rendering.
        gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vertexBuffer);

        gl.glDrawElements(GL10.GL_TRIANGLES, indeces.length, GL10.GL_UNSIGNED_SHORT, indexBuffer);

        // Disable the vertices buffer.
        gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
        // Disable face culling.
        //gl.glDisable(GL10.GL_CULL_FACE);

    }
}
