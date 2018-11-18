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
    private final static Float SLOPE_MAX_HEIGHT = 80.0f;
    private Path speedPaintPath = new Path();

    public SpeedPaint(SurfaceView view, Context context) {
        super();
        this.view = view;
        this.setAntiAlias(true);
        this.setColor(context.getResources().getColor(R.color.cyan));
        this.setStyle(Paint.Style.FILL);
    }

    public void draw(Canvas canvas) {
        powerAssistedHeight += (float)bicycle.getPowerAssisted();

        /**
         * 設置一個傾斜高度最大值避免意外狀況造成圖形扭曲
         * 當腳踏車速度為0時，不設置傾斜高度
         */
        if (bicycle.getSpeed() != 0
                && Math.abs(bicycle.getSlope()) < SLOPE_MAX_HEIGHT)
            slopeHeight = bicycle.getSlope();
        else if(bicycle.getSpeed() == 0)
            slopeHeight = 0.0f;

        /**
         * 根據馬達助力和腳踏車傾斜角度設置水平面高度
         * 然後使用Path圍出馬達助力圖形的四邊形
         */
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
