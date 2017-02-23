package com.example.ghazanfarali.piggyland;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.ghazanfarali.piggyland.Utils.SharedPrefrencesManger;
import com.example.ghazanfarali.piggyland.Views.Activities.BaseMasterActivity.MasterActivity;
import com.example.ghazanfarali.piggyland.Views.Activities.UserProfile.UserProfileActivity;
import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import io.fabric.sdk.android.Fabric;

public class SplashActivity extends MasterActivity {

    // Note: Your consumer key and secret should be obfuscated in your source code before shipping.
    private static final String TWITTER_KEY = "ZM2jLUD5L040PVUkgcI8FtlaS";
    private static final String TWITTER_SECRET = "nzNPMepl5rpIdtZ7nSJt9I7sF7UbjpbNIceDXzM7nEK7NrxCbr";


//    // Note: Your consumer key and secret should be obfuscated in your source code before shipping.
//    private static final String TWITTER_KEY = "	10RB4PJO61CCxnZfWiUMYoPbR";
//    private static final String TWITTER_SECRET = "tlz9vipR07Dd46ToAqnL9mrXQkkYxmiq3ejDKUMOfuMWvJA0t0";


    private final int SPLASH_DISPLAY_LENGTH = 1000;
    public SharedPrefrencesManger sharedPrefrencesManger;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TwitterAuthConfig authConfig = new TwitterAuthConfig(TWITTER_KEY, TWITTER_SECRET);
        Fabric.with(this, new Twitter(authConfig));
//        TwitterAuthConfig authConfig = new TwitterAuthConfig(TWITTER_KEY, TWITTER_SECRET);
//        Fabric.with(this, new Twitter(authConfig));
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
                Intent mainIntent = new Intent(SplashActivity.this,
                        UserProfileActivity.class);
                //Intent mainIntent = new Intent(SplashActivity.this, Drawing2Activity.class);
               // Intent mainIntent = new Intent(SplashActivity.this, UserProfileActivity.class);
                SplashActivity.this.startActivity(mainIntent);
                SplashActivity.this.finish();
            }
        }, SPLASH_DISPLAY_LENGTH);
    }
}
