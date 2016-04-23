package com.example.shaurun.visualization;


import com.example.shaurun.myapplication.AndroidGLSurfaceView;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

import static com.example.shaurun.visualization.BaseTypeSize.FLOAT_SIZE;

/**
 * Created by Shaurun on 28.02.2016.
 */
public class TexturedSprite extends Sprite {
    protected FloatBuffer textureCoordsBuffer;
    protected int textureResId;
    Texture texture;

    public TexturedSprite(float sizeX, float sizeY, int textureResId) {
        super(sizeX, sizeY);
        this.textureResId = textureResId;
    }

    @Override
    public void render(GL10 gl){
        if(texture == null){
            texture = new Texture(gl, AndroidGLSurfaceView.context, textureResId);
        }

        if(textureCoordsBuffer == null){
            setTextureCoordsBuffer();
        }

        gl.glEnable(GL10.GL_TEXTURE_2D);
        gl.glEnable(GL10.GL_BLEND);
        gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);
        // Enable the texture state
        gl.glEnableClientState(GL10.GL_TEXTURE_COORD_ARRAY);

        // Point to our buffers
        gl.glTexCoordPointer(2, GL10.GL_FLOAT, 0, textureCoordsBuffer);
        gl.glBindTexture(GL10.GL_TEXTURE_2D, texture.getTextureId());
        super.render(gl);
        gl.glDisableClientState(GL10.GL_TEXTURE_COORD_ARRAY);

        gl.glDisable(GL10.GL_TEXTURE_2D);
        gl.glDisable(GL10.GL_BLEND);

    }

    protected void setTextureCoordsBuffer() {
        float[] textureCoords = {
                0.0f, 0.0f, //top left
                0.0f, 1.0f, //bottom left
                1.0f, 1.0f, //bottom right
                1.0f, 0.0f, //top right
        };

        ByteBuffer byteBuf = ByteBuffer.allocateDirect(textureCoords.length * FLOAT_SIZE.value());
        byteBuf.order(ByteOrder.nativeOrder());
        textureCoordsBuffer = byteBuf.asFloatBuffer();
        textureCoordsBuffer.put(textureCoords);
        textureCoordsBuffer.position(0);
    }
}
