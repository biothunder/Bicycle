package com.lihuan.bicycle.ui.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

import com.lihuan.bicycle.ui.view.graphics.BackgroungPaint;
import com.lihuan.bicycle.ui.view.graphics.RpmPaint;
import com.lihuan.bicycle.ui.view.graphics.SpeedPaint;

public class SpeedView extends BaseSurfaceView {
    private SpeedPaint speedPaint;
    private BackgroungPaint backgroungPaint;
    private RpmPaint rpmPaint;

    public SpeedView(Context context) {
        this(context, null);
    }

    public SpeedView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SpeedView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    protected void init() {
        speedPaint = new SpeedPaint(this, context);
        backgroungPaint = new BackgroungPaint(this, context);
        rpmPaint = new RpmPaint(this, context);
    }

    @Override
    protected void customDraw(Canvas canvas) {
        backgroungPaint.draw(canvas);
        speedPaint.draw(canvas);
        rpmPaint.draw(canvas);
    }
}