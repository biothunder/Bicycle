package com.lihuan.bicycle.ui.view.graphics;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.SurfaceView;

import com.lihuan.bicycle.R;
import com.lihuan.bicycle.model.Bicycle;

public class SpeedPaint extends Paint {
    private SurfaceView view;
    private Bicycle bicycle = Bicycle.getInstance();
    private Float powerAssistedHeight = 0.0f;
    private Float slopeHeight = 0.0f;
    private Path speedPaintPath = new Path();

    public SpeedPaint(SurfaceView view, Context context) {
        super();
        this.view = view;
        this.setAntiAlias(true);
        this.setColor(context.getResources().getColor(R.color.speed));
        this.setStyle(Paint.Style.FILL);
    }

    public void draw(Canvas canvas) {
        powerAssistedHeight = powerAssistedHeight + (float) (bicycle.getPowerAssisted());
        if (bicycle.getSpeed() != 0)
            slopeHeight = bicycle.getSlope();

        //根據馬達助力設置水平面高度
        speedPaintPath.reset();
        speedPaintPath.moveTo(0, view.getHeight());
        speedPaintPath.lineTo(0,
                view.getHeight() - powerAssistedHeight - slopeHeight);
        speedPaintPath.lineTo(view.getWidth(),
                view.getHeight() - powerAssistedHeight + slopeHeight);
        speedPaintPath.lineTo(view.getWidth(), view.getHeight());
        speedPaintPath.close();
        canvas.drawPath(speedPaintPath, this);
    }
}
