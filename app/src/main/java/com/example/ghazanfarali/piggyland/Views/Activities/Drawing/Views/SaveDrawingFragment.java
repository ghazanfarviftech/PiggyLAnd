package com.example.ghazanfarali.piggyland.Views.Activities.Drawing.Views;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.ghazanfarali.piggyland.Controls.MultiClickListner;
import com.example.ghazanfarali.piggyland.R;
import com.example.ghazanfarali.piggyland.Views.Activities.Drawing.adapters.ShareArtWorkAdapter;
import com.example.ghazanfarali.piggyland.Views.Activities.MyGallery.adapter.MyGallaryITemDecor;
import com.example.ghazanfarali.piggyland.Views.Fragments.BaseMasterFragment.MasterFragment;
import com.example.ghazanfarali.piggyland.Views.Fragments.SharedArt_Comments_fragment.SharedComment_Fragment;

/**
 * Created by ghazanfarali on 12/02/2017.
 */

public class SaveDrawingFragment extends MasterFragment {


    String filePath,fileDir;
    Toolbar toolbar;
    TextView counterTextView;
    private View view;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if (view == null) {
            view = inflater.inflate(R.layout.activity_save_drawing, container, false);

            initUI();

        } else {
            if (view != null)
            {

            }
                //  userProfileActivity.hideHeaderLayout();
               // userProfileActivity.setHeaderTitle("");
        }
        return view;

    }

    @Override
    public void initUI() {
        super.initUI();

        toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        toolbar.setTitle("My Gallary");
        //setSupportActionBar(toolbar);
        counterTextView = (TextView) view.findViewById(R.id.cnt_text);
        counterTextView.setVisibility(View.VISIBLE);
        counterTextView.setText("Share Your Drawing");
        ImageView image_preview = (ImageView)view.findViewById(R.id.image_preview);
        //Intent intent= getIntent();
       /* Bundle bundle = intent.getExtras();
        filePath =bundle.getString("fileName");
        fileDir =bundle.getString("fileDir");*/

        Glide
                .with( getActivity() ) // safer!
                .load( filePath)
                .asBitmap()
                .into( image_preview );
        /*intent.putExtra("MESSAGE","data");
        setResult(2,intent);
        finish();*/

        ImageView share_to_public = (ImageView)view.findViewById(R.id.share_to_public);
        share_to_public.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //   selectAll();
                EditText title_enter = (EditText)view.findViewById(R.id.img_title);
                EditText img_description = (EditText)view.findViewById(R.id.img_description);
                // title_enter.getText().toString();
                if(title_enter.getText().length() == 0)
                {
                    Toast.makeText(getActivity(),"Enter the title please",Toast.LENGTH_SHORT).show();
                    // return false;
                }
                if(img_description.getText().length() == 0)
                {
                    Toast.makeText(getActivity(),"Enter image description",Toast.LENGTH_SHORT).show();
                    //return false;
                }
                else{
                    if(fileDir.contentEquals("shareToPublic")) {


                        Intent intent = new Intent();
                        intent.putExtra("Title", title_enter.getText().toString());
                        intent.putExtra("FullPath", filePath);
                        intent.putExtra("Description", img_description.getText().toString());
                        //setResult(3, intent);
                        //finish();
                    }else if(fileDir.contentEquals("saveToGallery")){
                        Intent intent = new Intent();
                        intent.putExtra("Title", title_enter.getText().toString());
                        intent.putExtra("FullPath", filePath);
                        intent.putExtra("Description", img_description.getText().toString());
                        //setResult(4, intent);
                        //finish();
                    }

                }
            }
        });

    }


    @Override
    public void onResume() {
        super.onResume();

    }
}
