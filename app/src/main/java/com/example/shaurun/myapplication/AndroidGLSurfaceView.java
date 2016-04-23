package com.example.shaurun.myapplication;

import android.content.Context;
import android.opengl.GLSurfaceView;

/**
 * Created by Shaurun on 27.02.2016.
 */
public class AndroidGLSurfaceView extends GLSurfaceView {
    private final AndroidGLRenderer renderer;
    public static Context context;

    public AndroidGLSurfaceView(Context context) {
        super(context);
        //added by me
        this.context = context;
        ////

        // Create an OpenGL ES 2.0 context
        setEGLContextClientVersion(1);

        //Set config
        super.setEGLConfigChooser(8,8,8,8,16,0);

        renderer = new AndroidGLRenderer();

        // Set the Renderer for drawing on the GLSurfaceView
        setRenderer(renderer);
    }

    public AndroidGLRenderer getRenderer(){
        return renderer;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int parentWidth = MeasureSpec.getSize(widthMeasureSpec);
        int parentHeight = MeasureSpec.getSize(heightMeasureSpec);
        this.setMeasuredDimension(parentWidth, parentHeight);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
