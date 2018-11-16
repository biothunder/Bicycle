package com.lihuan.bicycle.ui.view;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public abstract class BaseSurfaceView extends SurfaceView implements SurfaceHolder.Callback,
        Runnable {
    private SurfaceHolder surfaceHolder;
    protected Context context;
    private boolean isDrawing = false;

    public BaseSurfaceView(Context context) {
        this(context, null);
    }

    public BaseSurfaceView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BaseSurfaceView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        this.context = context;
        surfaceHolder = getHolder();
        surfaceHolder.addCallback(this);
        setFocusable(true);

        init();
    }

    protected void init(){}

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        isDrawing = true;
        new Thread(this).start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        isDrawing = false;
    }

    @Override
    public void run() {
        while (isDrawing) {
            Canvas canvas = null;
            try {
                canvas = surfaceHolder.lockCanvas();
                synchronized (surfaceHolder) {
                    draw(canvas);
                }
            } finally {
                if (canvas != null)
                    surfaceHolder.unlockCanvasAndPost(canvas);
            }
        }
    }

    public void draw(Canvas canvas) {
        if (canvas == null)
            return;
        super.draw(canvas);

        customDraw(canvas);
    }

    abstract void customDraw(Canvas canvas);
}
