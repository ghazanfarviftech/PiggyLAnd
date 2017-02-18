package com.example.ghazanfarali.piggyland.Controls;

import android.view.View;

/**
 * Created by Amir.jehangir on 2/7/2017.
 */
public interface MultiClickListner {

    public void onLikeItemClick(int position);

    public void onCommentItemClick(View view, int position);

    public void onShareItemClick(View view, int position);
}
