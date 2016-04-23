package com.example.shaurun.visualization;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLUtils;

import com.example.shaurun.myapplication.R;

import javax.microedition.khronos.opengles.GL10;

/**
 * Created by Shaurun on 28.02.2016.
 */
public class Texture {
    private int iTextureId;

    public Texture(GL10 gl, Context ctx, int resourceId){
        iTextureId = loadTexture(gl, ctx, resourceId);
    }

    public int getTextureId(){
        return iTextureId;
    }

    private int loadTexture(GL10 gl, Context ctx, int resourceId){
        int[] iTextureId = new int[1];

        gl.glActiveTexture(GL10.GL_TEXTURE0);
        gl.glGenTextures(1, iTextureId, 0);

        if(iTextureId[0] != 0){
            gl.glBindTexture(GL10.GL_TEXTURE_2D, iTextureId[0]);

            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inScaled = false;

            Bitmap bitmap = BitmapFactory.decodeResource(ctx.getResources(), resourceId, options);

            GLUtils.texImage2D(GL10.GL_TEXTURE_2D, 0, bitmap, 0);
            bitmap.recycle();

            gl.glTexParameterf(GL10.GL_TEXTURE_2D,
                    GL10.GL_TEXTURE_MIN_FILTER,
                    GL10.GL_NEAREST);

            gl.glTexParameterf(GL10.GL_TEXTURE_2D,
                    GL10.GL_TEXTURE_MAG_FILTER,
                    GL10.GL_NEAREST);
        } else {
          throw new RuntimeException("Error loading texture");
        }

        return  iTextureId[0];
    }
}
