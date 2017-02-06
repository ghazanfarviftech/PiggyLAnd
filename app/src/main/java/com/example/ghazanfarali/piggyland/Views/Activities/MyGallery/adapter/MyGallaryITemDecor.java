package com.example.ghazanfarali.piggyland.Views.Activities.MyGallery.adapter;

import android.content.Context;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.ghazanfarali.piggyland.R;

/**
 * Created by Amir.jehangir on 1/11/2017.
 */
public class MyGallaryITemDecor extends RecyclerView.ItemDecoration{

    private int margin;
    private int marginTop;
    private int marginBottom;
    public MyGallaryITemDecor(Context context) {
        margin = context.getResources().getDimensionPixelSize(R.dimen.x1)/2;

        marginBottom = context.getResources().getDimensionPixelSize(R.dimen.x8);


    }

    @Override
    public void getItemOffsets(
            Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.set(margin,margin,margin,margin);
    }


}
