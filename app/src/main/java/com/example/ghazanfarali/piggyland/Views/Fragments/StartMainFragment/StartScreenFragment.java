package com.example.ghazanfarali.piggyland.Views.Fragments.StartMainFragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.ghazanfarali.piggyland.R;
import com.example.ghazanfarali.piggyland.Views.Activities.Drawing.Drawing2Activity;
import com.example.ghazanfarali.piggyland.Views.Activities.Drawing.Views.AllSharedActivity;
import com.example.ghazanfarali.piggyland.Views.Activities.MyGallery.Views.MyGalleryMultiSelect;
import com.example.ghazanfarali.piggyland.Views.Fragments.BaseMasterFragment.MasterFragment;

/**
 * Created by Amir.jehangir on 2/3/2017.
 */
public class StartScreenFragment extends MasterFragment {
    private View view;
    Button create_new ;
    LinearLayout gallery;
    LinearLayout order;
    LinearLayout share;

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



    public void Listner(){

        create_new = (Button)view.findViewById(R.id.create_new);
        create_new.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(),Drawing2Activity.class);
                startActivity(i);
            }
        });

        gallery = (LinearLayout)view.findViewById(R.id.gallery);
        gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(),MyGalleryMultiSelect.class);
                startActivity(i);
            }
        });


        order = (LinearLayout)view.findViewById(R.id.order);
        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              /*  Intent i = new Intent(StartActivity.this,MembersActivity.class);
                startActivity(i);*/
            }
        });

       share = (LinearLayout)view.findViewById(R.id.share);
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), AllSharedActivity.class);
                startActivity(i);
            }
        });
    }

    @Override
    public void initUI() {
        super.initUI();

        Listner();
    }
}
