package com.example.ghazanfarali.piggyland.Views.Activities.UserProfile;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.ghazanfarali.piggyland.MainActivity;
import com.example.ghazanfarali.piggyland.R;
import com.example.ghazanfarali.piggyland.Views.Activities.BaseMasterActivity.MasterActivity;
import com.example.ghazanfarali.piggyland.Views.Activities.MyGallery.MyGallery;

/**
 * Created by Amir.jehangir on 1/10/2017.
 */
public class UserProfileActivity extends MasterActivity {


    Button btn_sidemenu, btn_createnew;
    boolean bottom_sheet = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.userprofile_activity);

        initUI();
        initListner();

    }

    @Override
    public void initUI() {
        super.initUI();

        btn_sidemenu = (Button) findViewById(R.id.btn_menuimg);
        btn_createnew = (Button) findViewById(R.id.btn_createnew);
    }

    public void initListner() {
        btn_sidemenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!bottom_sheet) {
                    LinearLayout bottomSheet = (LinearLayout) findViewById(R.id.bottomSheetLayout);
                    bottomSheet.setVisibility(View.VISIBLE);

                    LinearLayout contact_us = (LinearLayout) bottomSheet.findViewById(R.id.contact_us);
                    contact_us.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            Intent i = new Intent(UserProfileActivity.this, MainActivity.class);
                            startActivity(i);
                        }
                    });
                    LinearLayout about_us = (LinearLayout) bottomSheet.findViewById(R.id.about_uss);
                    about_us.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            Intent i = new Intent(UserProfileActivity.this, MainActivity.class);
                            startActivity(i);
                        }
                    });

                    LinearLayout homes = (LinearLayout) bottomSheet.findViewById(R.id.homes);
                    homes.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            finish();
                            Intent i = new Intent(UserProfileActivity.this, MainActivity.class);
                            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                            startActivity(i);
                        }
                    });

                    bottom_sheet = true;
                } else {

                    LinearLayout bottomSheet = (LinearLayout) findViewById(R.id.bottomSheetLayout);
                    bottomSheet.setVisibility(View.INVISIBLE);
                    // Adapter.setOnItemClickListener(onItemClickListener);
                    bottom_sheet = false;
                }

            }
        });




        btn_createnew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UserProfileActivity.this,MyGallery.class));
            }
        });


    }


}
