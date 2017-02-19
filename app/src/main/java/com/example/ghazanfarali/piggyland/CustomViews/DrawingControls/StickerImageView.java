package com.example.ghazanfarali.piggyland.CustomViews.DrawingControls;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

import com.example.ghazanfarali.piggyland.R;


public class StickerImageView extends StickerView {

    private String owner_id;
    public ImageView iv_main;
    public StickerImageView(Context context) {
        super(context);
    }

    public StickerImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public StickerImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void setOwnerId(String owner_id){
        this.owner_id = owner_id;
    }

    public String getOwnerId(){
        return this.owner_id;
    }
    boolean count = true;
    @Override
    public View getMainView() {
        if(this.iv_main == null) {
            this.iv_main = new ImageView(getContext());
            this.iv_main.setScaleType(ImageView.ScaleType.FIT_XY);
            Drawable img = getResources().getDrawable(R.drawable.ic_check_box_empty);
            this.iv_main.setImageDrawable(img);
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
    public void setImageBitmap(Bitmap bmp){
        this.iv_main.setImageBitmap(bmp);
    }

    public void setImageResource(int res_id){
        this.iv_main.setImageResource(res_id);
    }

    public void setImageDrawable(Drawable drawable){ this.iv_main.setImageDrawable(drawable); }

    public Bitmap getImageBitmap(){ return ((BitmapDrawable)this.iv_main.getDrawable()).getBitmap() ; }

}
