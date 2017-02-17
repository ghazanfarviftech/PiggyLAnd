package com.example.ghazanfarali.piggyland.Views.Fragments.DrawingFragment.ShareDrawing;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.ghazanfarali.piggyland.Helper.ValidationUtils;
import com.example.ghazanfarali.piggyland.R;
import com.example.ghazanfarali.piggyland.Utils.Defines;
import com.example.ghazanfarali.piggyland.Views.Fragments.BaseMasterFragment.MasterFragment;

/**
 * Created by Amir.jehangir on 2/17/2017.
 */
public class ShareYourDrawing extends MasterFragment {


    private View view;
    String filePath,fileDir;
    ImageView image_preview,share_to_public;
    EditText title_enter;
    EditText img_description;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if (view == null) {
            view = inflater.inflate(R.layout.activity_save_drawing, container, false);

            initUI();
            userProfileActivity.fragmentType = "101";

userProfileActivity.showHeaderLayout();
            share_to_public.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //   selectAll();

                    // title_enter.getText().toString();
                    if(title_enter.getText().length() ==0)
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

                            Defines.is_shareToPublic = true;
                            Intent intent = new Intent();
                            intent.putExtra("Title", title_enter.getText().toString());
                            intent.putExtra("FullPath", filePath);
                            intent.putExtra("Description", img_description.getText().toString());
                            if(!ValidationUtils.testEmpty(title_enter.getText().toString())){
                                Defines.Share_title = title_enter.getText().toString();
                            }else {
                                Defines.Share_title = "";
                            }
                            if(!ValidationUtils.testEmpty(filePath)){
                                Defines.Share_fullpath = filePath;
                            }else {
                                Defines.Share_fullpath = "";
                            }
                            if(!ValidationUtils.testEmpty(img_description.getText().toString())){
                                Defines.Share_description = img_description.getText().toString();
                            }else {
                                Defines.Share_description = "";
                            }

                        //   getActivity(). setResult(3, intent);

                           userProfileActivity.onBackPressed();
                        }else if(fileDir.contentEquals("saveToGallery")){

                            Defines.is_saveToGallery = true;
                            Intent intent = new Intent();
                            intent.putExtra("Title", title_enter.getText().toString());
                            intent.putExtra("FullPath", filePath);
                            intent.putExtra("Description", img_description.getText().toString());
                            if(!ValidationUtils.testEmpty(title_enter.getText().toString())){
                                Defines.Share_title = title_enter.getText().toString();
                            }else {
                                Defines.Share_title = "";
                            }
                            if(!ValidationUtils.testEmpty(filePath)){
                                Defines.Share_fullpath = filePath;
                            }else {
                                Defines.Share_fullpath = "";
                            }
                            if(!ValidationUtils.testEmpty(img_description.getText().toString())){
                                Defines.Share_description = img_description.getText().toString();
                            }else {
                                Defines.Share_description = "";
                            }
                          //  getActivity(). setResult(4, intent);
                            userProfileActivity.onBackPressed();
                        }

                    }
                }
            });

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
        image_preview = (ImageView)view.findViewById(R.id.image_preview);
        //Intent intent= getActivity().getIntent();
      //  Bundle bundle = intent.getExtras();
        title_enter = (EditText)view.findViewById(R.id.img_title);
        img_description = (EditText)view.findViewById(R.id.img_description);
        filePath =getArguments().getString("fileName");
        fileDir =getArguments().getString("fileDir");

        Glide
                .with( getActivity() ) // safer!
                .load( filePath)
                .asBitmap()
                .into( image_preview );


       share_to_public = (ImageView)view.findViewById(R.id.share_to_public);


    }


}
