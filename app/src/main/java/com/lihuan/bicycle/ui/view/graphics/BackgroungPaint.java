package com.lihuan.bicycle.ui.view.graphics;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.SurfaceView;

import com.lihuan.bicycle.R;

public class BackgroungPaint extends Paint{
    private SurfaceView view;

    public BackgroungPaint(SurfaceView view, Context context) {
        super();
        this.view = view;
        this.setColor(context.getResources().getColor(R.color.white));
        this.setStyle(Paint.Style.FILL);
    }

    public void draw(Canvas canvas) {
        canvas.drawRect(0, 0, view.getWidth(), view.getHeight(), this);
    }
}
