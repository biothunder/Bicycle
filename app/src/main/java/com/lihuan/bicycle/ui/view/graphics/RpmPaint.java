package com.lihuan.bicycle.ui.view.graphics;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;
import android.view.SurfaceView;

import com.lihuan.bicycle.R;
import com.lihuan.bicycle.model.Bicycle;

public class RpmPaint extends Paint {
    private SurfaceView view;
    private Float centerX, z, positionX = 0.0f, positionY;
    private final static Float radius = 15f;
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

    public void draw(Canvas canvas) {
        if (bicycle.getSpeed() > 0 && alpha < 255)
            alpha = Math.round(bicycle.getSpeed() * 10);
        else if (bicycle.getSpeed() == 0)
            alpha = 0;
        else
            alpha = 255;
        this.setAlpha(alpha);

        centerX = (float) view.getWidth() / 2;
        float startX = view.getWidth() / 4, finishX = view.getWidth() / 4 * 3;
        float startY = view.getHeight() - (float) view.getHeight() / 4;

        if (positionX == 0.0f) {
            positionX = startX;
            z = centerX - positionX;
        }


        if (positionX > finishX) {
            sign = -1f;
        } else if (positionX < startX) {
            sign = 1f;
        }

        positionX = positionX + bicycle.getSpeed() * sign;

        float newZ = centerX - positionX;
        float pow = z * z - newZ * newZ;
        float y = (float) Math.sqrt(pow);
        positionY = view.getHeight() - (float) view.getHeight() / 4 - y * sign;

        canvas.drawCircle(positionX,
                startY - y * sign, radius, this);

        canvas.drawCircle(view.getWidth() - positionX,
                startY + y * sign, radius, this);

    }
}
