package com.example.ghazanfarali.piggyland.Views.Fragments.StartMainFragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ghazanfarali.piggyland.R;
import com.example.ghazanfarali.piggyland.Views.Fragments.BaseMasterFragment.MasterFragment;

/**
 * Created by Amir.jehangir on 2/3/2017.
 */
public class StartScreenFragment extends MasterFragment {
    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if (view == null) {
            view = inflater.inflate(R.layout.startscreen_fragment, container, false);
            // mContext.getApplicationContext();
            initUI();

//            startService();
        } else {
            if (view != null)
                //  userProfileActivity.hideHeaderLayout();
                userProfileActivity.setHeaderTitle("");
        }
        return view;

    }


    @Override
    public void initUI() {
        super.initUI();
    }
}
