package com.example.ghazanfarali.piggyland.CustomViews.DrawingControls;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * Created by ghazanfarali on 16/01/2017.
 */

public class CircleShapeView extends StickerView {

    private CricleShap iv_main;
    public CircleShapeView(Context context) {
        super(context);
    }

    public CircleShapeView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CircleShapeView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }
    Paint paint;
    int color= Color.BLUE;
    @Override
    public View getMainView() {
        if(this.iv_main == null) {
            this.iv_main = new CricleShap(getContext());
            //this.iv_main.setScaleType(ImageView.ScaleType.FIT_XY);
            //this.iv_main.changeColor(color);
           // this.iv_main.invalidate();
           /* this.iv_main.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                  *//*  Toast.makeText(getContext(),"Tata",Toast.LENGTH_SHORT).show();
                    if(count)
                    {
                        setControlItemsHidden(true);
                        setOnTouchListener(null);
                        count = false;
                    }else{
                        setControlItemsHidden(false);
                        setOnTouchListener(mTouchListener);
                        count = true;
                    }*//*
                }
            });*/
        }
        return iv_main;
    }

    public void updateColor(int color)
    {
        this.color = color;
    }
    /*@Override
    protected View getMainView() {
        if(this.iv_main == null) {
            iv_main = new CricleShap(getContext());
            LayoutParams params = new LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
            );
            params.gravity = Gravity.CENTER;
            iv_main.setLayoutParams(params);
           *//* Canvas canvas =  new Canvas();
            int radius = Math.min(getWidth(),getHeight()/2);
            paint = new Paint();
            paint.setColor(Color.BLACK);
            paint.setStrokeWidth(10);
            paint.setStyle(Paint.Style.STROKE);
            canvas.drawCircle(getWidth()/ 2, // cx
                    getWidth() / 2, radius, paint);*//*
            *//*FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
            );
            params.gravity = Gravity.CENTER;
            iv_main.setLayoutParams(params);*//*
            *//*this.iv_main = new CricleShap(getContext());
            Canvas canvas =  new Canvas();
            int radius = Math.min(canvas.getWidth(),canvas.getHeight()/2);
            paint = new Paint();
            paint.setColor(Color.BLACK);
            paint.setStyle(Paint.Style.STROKE);
            // Set a pixels value to padding around the circle
            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
            );
            params.gravity = Gravity.CENTER;
            iv_main.setLayoutParams(params);
            int padding = 5;
            canvas.drawCircle(params.width / 2, // cx
                    params.height / 2, radius - padding, paint);
            iv_main.onDraw(canvas);
*//*

        }
        return iv_main;
    }*/
}
