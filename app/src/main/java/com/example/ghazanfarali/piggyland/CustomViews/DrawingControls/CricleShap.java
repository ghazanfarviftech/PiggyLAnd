package com.example.ghazanfarali.piggyland.CustomViews.DrawingControls;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by ghazanfarali on 16/01/2017.
 */

public class CricleShap extends View {
    Paint paint;
    public CricleShap(Context context) {
        super(context);
        paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(10);
        paint.setStyle(Paint.Style.STROKE);
    }

    public CricleShap(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CricleShap(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int radius = Math.min(canvas.getWidth(),canvas.getHeight()/2);

        // Set a pixels value to padding around the circle
        int padding = 5;
        canvas.drawCircle(50, 50, 30, paint);
    }
}
