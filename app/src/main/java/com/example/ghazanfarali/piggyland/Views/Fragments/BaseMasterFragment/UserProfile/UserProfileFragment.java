package com.example.ghazanfarali.piggyland.Views.Fragments.BaseMasterFragment.UserProfile;

import android.animation.Animator;
import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;

import com.example.ghazanfarali.piggyland.R;
import com.example.ghazanfarali.piggyland.Views.Fragments.BaseMasterFragment.MasterFragment;

/**
 * Created by Amir.jehangir on 1/12/2017.
 */
public class UserProfileFragment extends MasterFragment {
    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if (view == null) {
            view = inflater.inflate(R.layout.userprofilefragment, container, false);
            initUI();
            loadingAnimation();
//            startService();
        } else {
            if (view != null)
              //  userProfileActivity.hideHeaderLayout();
            userProfileActivity.setHeaderTitle("Main");
        }
        return view;

    }

    @Override
    public void initUI() {
        super.initUI();
    }

    private void loadingAnimation() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            view.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
                @TargetApi(Build.VERSION_CODES.LOLLIPOP)
                @Override
                public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop,
                                           int oldRight, int oldBottom) {
                    v.removeOnLayoutChangeListener(this);
                    int cx = v.getWidth() / 2;
                    int cy = v.getHeight() / 2;

                    // get the hypothenuse so the radius is from one corner to the other
                    int radius = (int) Math.hypot(top, bottom);


                    Animator reveal = ViewAnimationUtils.createCircularReveal(v, cx, cy, 0, radius);
                    reveal.setInterpolator(new DecelerateInterpolator(2f));
                    reveal.setDuration(6000);
                    reveal.start();

                }
            });
        }
    }

}
