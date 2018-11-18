package com.lihuan.bicycle.ui.view.graphics;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.SurfaceView;

import com.lihuan.bicycle.R;
import com.lihuan.bicycle.model.Bicycle;

public class RpmPaint extends Paint {
    private SurfaceView view;
    private Float centerX, pathRadius, positionX = 0.0f;
    private final static Float PEDAL_RADIUS = 15f;
    private Bicycle bicycle = Bicycle.getInstance();
    private Float sign = 1.0f;
    private Integer alpha = 0;

    public RpmPaint(SurfaceView view, Context context) {
        super();
        this.view = view;
        this.setAntiAlias(true);
        this.setColor(context.getResources().getColor(R.color.black));
        this.setStyle(Paint.Style.FILL);
        this.setAlpha(alpha);
    }

    /**
     * 先計算出第一個踏板的位置，再用第一個踏板的位置決定第二個踏板的位置
     * 並依照速度改變透明度
     */
    public void draw(Canvas canvas) {
        alpha = Math.round(bicycle.getSpeed() * 10);

        if (alpha > 255)
            alpha = 255;
        else if (bicycle.getSpeed() == 0)
            alpha = 0;

        this.setAlpha(alpha);

        centerX = (float) view.getWidth() / 2;
        float startX = view.getWidth() / 4, finishX = view.getWidth() / 4 * 3;
        float startY = view.getHeight() - (float) view.getHeight() / 4;

        /**
        * 初始化第一個踏板x軸位置，並算出移動路徑的半徑
        */
        if (positionX == 0.0f) {
            positionX = startX;
            pathRadius = centerX - positionX;
        }

        /**
         * 踏板的X軸移動方向做往返處理
         */
        if (positionX > finishX) {
            sign = -1f;
        } else if (positionX < startX) {
            sign = 1f;
        }

        /**
        * 速度越快，X軸位置改變得就越快
        */
        positionX += bicycle.getSpeed() * sign;

        /**
         * 計算Y軸篇移量
         */
        float deviationY = (float) Math.sqrt(pathRadius * pathRadius -
                        (centerX - positionX) * (centerX - positionX));

        canvas.drawCircle(positionX,
                startY - deviationY * sign, PEDAL_RADIUS, this);

        canvas.drawCircle(view.getWidth() - positionX,
                startY + deviationY * sign, PEDAL_RADIUS, this);

    }
}
