package com.example.ghazanfarali.piggyland;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.ghazanfarali.piggyland.Utils.SharedPrefrencesManger;
import com.example.ghazanfarali.piggyland.Views.Activities.BaseMasterActivity.MasterActivity;
import com.example.ghazanfarali.piggyland.Views.Activities.Drawing.Drawing2Activity;
import com.example.ghazanfarali.piggyland.Views.Activities.Login.LoginActivity;
import com.example.ghazanfarali.piggyland.Views.Activities.UserProfile.UserProfileActivity;

public class SplashActivity extends MasterActivity {

    private final int SPLASH_DISPLAY_LENGTH = 1000;
    public SharedPrefrencesManger sharedPrefrencesManger;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        initUI();
    }

    @Override
    public void initUI() {
        super.initUI();
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                /* Create an Intent that will start the Menu-Activity. */
               // Intent mainIntent = new Intent(SplashActivity.this, LoginActivity.class);
                //Intent mainIntent = new Intent(SplashActivity.this, Drawing2Activity.class);
                Intent mainIntent = new Intent(SplashActivity.this, UserProfileActivity.class);
                SplashActivity.this.startActivity(mainIntent);
                SplashActivity.this.finish();
            }
        }, SPLASH_DISPLAY_LENGTH);
    }
}
