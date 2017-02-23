package com.example.ghazanfarali.piggyland.Views.Activities.UserProfile;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.ghazanfarali.piggyland.R;
import com.example.ghazanfarali.piggyland.Utils.SharedPrefrencesManger;
import com.example.ghazanfarali.piggyland.Views.Activities.BaseMasterActivity.MasterActivity;
import com.example.ghazanfarali.piggyland.Views.Activities.Drawing.Views.AllSharedActivity;
import com.example.ghazanfarali.piggyland.Views.Activities.Login.LoginActivity;
import com.example.ghazanfarali.piggyland.Views.Activities.MyGallery.MyGallery;
import com.example.ghazanfarali.piggyland.Views.Fragments.BaseMasterFragment.UserProfile.UserProfileFragment;
import com.example.ghazanfarali.piggyland.Views.Fragments.MessageforYou.MessageforyouFragment;
import com.example.ghazanfarali.piggyland.Views.Fragments.PeopleInPiggyLand.PeopleInPiggyLandFragment;
import com.example.ghazanfarali.piggyland.Views.Fragments.StartMainFragment.StartScreenFragment;
import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterSession;

import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * Created by Amir.jehangir on 1/10/2017.
 */
public class UserProfileActivity extends MasterActivity implements NavigationView.OnNavigationItemSelectedListener {


    private Button btn_sidemenu, btn_createnew;
    RelativeLayout backLayout;
    RelativeLayout helpLayout;
    DrawerLayout drawer;
    boolean mSlideState = false;
    int hideShow = 0;

    public static UserProfileActivity userProInstance;
    RelativeLayout headerLayoutID;
    TextView titleTxt, text_title;
    public static String fragmentType;
    ImageView nav_logo_img, imageView_nav_main_logo;
    LinearLayout nav_header_main_ll;
    public SharedPrefrencesManger sharedPrefrencesManger;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.userprofile_activity);
        sharedPrefrencesManger = new SharedPrefrencesManger(this);
        if (sharedPrefrencesManger.getuserLogin().contentEquals("true")) {

            Bundle extra = getIntent().getExtras();
            if (extra != null) {
                fragmentType = getIntent().getExtras().getString("fragmentIndex");
                getIntent().removeExtra("fragmentIndex");
            } else {
                fragmentType = "1";
            }
        } else {
            this.finish();
            startActivity(new Intent(this, LoginActivity.class));
        }

        initUI();
        initListner();

    }

    @Override
    public void initUI() {
        userProInstance = this;

        headerLayoutID = (RelativeLayout) findViewById(R.id.headerLayoutID);
        text_title = (TextView) findViewById(R.id.text_title);
//        headerLayoutID.bringToFront();
        titleTxt = (TextView) findViewById(R.id.titleTxt);
        btn_sidemenu = (Button) findViewById(R.id.btn_menuimg);
        btn_createnew = (Button) findViewById(R.id.btn_createnew);
//btn_createnew.setOnClickListener(new View.OnClickListener() {
//    @Override
//    public void onClick(View v) {
//       UserProfileFragment.UpdateUserProfile();
//    }
//});
        backLayout = (RelativeLayout) findViewById(R.id.backLayout);
        helpLayout = (RelativeLayout) findViewById(R.id.helpLayout);
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        nav_logo_img = (ImageView) findViewById(R.id.nav_logo_img);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        View headerview = navigationView.getHeaderView(0);
        nav_header_main_ll = (LinearLayout) headerview.findViewById(R.id.nav_header_main_ll);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                int id = item.getItemId();

                if (id == R.id.new_drawing) {
                    clickEventSlide();
                    setVisibilities(1);
                    showHeaderLayout();
                    fragmentType = "1";
                    clearBackStack();
                    AddFragment(new StartScreenFragment(), R.id.frameLayout, false);
                    drawer.closeDrawer(GravityCompat.START);
                    // replaceFragmnet(new StartScreenFragment(), R.id.frameLayout, false);
                    // displayNextFragment();

                } else if (id == R.id.gallery) {
                    //titleTxt.setText("My Gallary");

                    setVisibilities(1);
                    fragmentType = "2";
                    hideHeaderLayout();
                    replaceFragmnet(new MyGallery(), R.id.frameLayout, true);
                    drawer.closeDrawer(GravityCompat.START);
                } else if (id == R.id.order) {
                    //  Toast.makeText(UserProfileActivity.this,"this",Toast.LENGTH_LONG).show();
                } else if (id == R.id.memebers) {
                    // setVisibilities(4);
                    showHeaderLayout();
                    fragmentType = "3";
                    setVisibilities(4);
                    replaceFragmnet(new PeopleInPiggyLandFragment(), R.id.frameLayout, true);
                    drawer.closeDrawer(GravityCompat.START);
                } else if (id == R.id.message) {
                    showHeaderLayout();
                    setVisibilities(5);
                    fragmentType = "5";

                    replaceFragmnet(new MessageforyouFragment(), R.id.frameLayout, true);
                    drawer.closeDrawer(GravityCompat.START);
                } else if (id == R.id.profile) {
                    // hideHeaderLayout();
                    showHeaderLayout();
                    setVisibilities(6);

                    //  fragmentType = "6";
                    // hideHeaderLayout();
                    fragmentType = "6";
                    replaceFragmnet(new UserProfileFragment(), R.id.frameLayout, true);
                    drawer.closeDrawer(GravityCompat.START);
                } else if (id == R.id.Logout) {
                    //showHeaderLayout();
                    fragmentType = "7";
                    finish();
                    drawer.closeDrawer(GravityCompat.START);
                    if (sharedPrefrencesManger.getuserLoginTwitter()) {
                        logoutTwitter();
                        sharedPrefrencesManger.setuserLoginTwitter(false);
                        sharedPrefrencesManger.clearSharedTwitterSession();
                        startActivity(new Intent(UserProfileActivity.this, LoginActivity.class));
                    } else {
                        SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(UserProfileActivity.this, SweetAlertDialog.NORMAL_TYPE);
                        sweetAlertDialog.setCancelText("Cancel")
                                .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                    @Override
                                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                                        sweetAlertDialog.dismiss();
//                            ptrHome.setRefreshing(false);
//                            loading = false;

                                    }
                                })
                                .setTitleText("Are you sure?")
                                .setContentText("Won't be Logout")
                                .setConfirmText("Yes,Logout it!")
                                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                    @Override
                                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                                        sweetAlertDialog.cancel();
                                        sharedPrefrencesManger.setuserLogin("false");
                                        startActivity(new Intent(UserProfileActivity.this, LoginActivity.class));
                                        finish();

                                    }
                                });
                        sweetAlertDialog.show();


                    }
                } else if (id == R.id.ArtWork) {
                    showHeaderLayout();
                    setVisibilities(1);
                    // showHeaderLayout();
                    fragmentType = "8";
                    replaceFragmnet(new AllSharedActivity(), R.id.frameLayout, true);
                    drawer.closeDrawer(GravityCompat.START);
                    //  displayNextFragment();
                }
                return true;
            }
        });

        displayNextFragment();

        btn_sidemenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                clickEventSlide();
            }
        });


        btn_createnew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //  startActivity(new Intent(UserProfileActivity.this, MyGallery.class));
                //startActivity(new Intent(UserProfileActivity.this, MyGalleryMultiSelect.class));
            }
        });


        nav_header_main_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentType = "1";
                displayNextFragment();
            }
        });

        super.initUI();
        // navigationView.setNavigationItemSelectedListener(this);
    }


    private void displayNextFragment() {
        Log.v("fragmentIndex", fragmentType + " ");


        if (fragmentType != null) {
            switch (fragmentType) {
                case "0":
                    setVisibilities(2);
                    AddFragment(new UserProfileFragment(), R.id.frameLayout, false);
                    break;
                case "-1":
                    replaceFragmnet(new UserProfileFragment(), R.id.frameLayout, false);
                    break;
                case "1":
                    setVisibilities(1);

                    clearBackStack();
                    AddFragment(new StartScreenFragment(), R.id.frameLayout, false);
                    break;
                case "2":
                    setVisibilities(1);
                    //   replaceFragmnet(new MyGallery(), R.id.frameLayout, false);
                    //startActivity(new Intent(this, MyGalleryMultiSelect.class));
                    hideHeaderLayout();
                    replaceFragmnet(new MyGallery(), R.id.frameLayout, true);
                    break;
                case "3":
                    setVisibilities(4);
                    replaceFragmnet(new PeopleInPiggyLandFragment(), R.id.frameLayout, true);
                    break;
                case "4":
                    setVisibilities(1);
                    replaceFragmnet(new MessageforyouFragment(), R.id.frameLayout, true);
                    break;
                case "5":
                    setVisibilities(5);
                    replaceFragmnet(new MessageforyouFragment(), R.id.frameLayout, true);
                    break;
                case "6":
                    setVisibilities(6);
                    // hideHeaderLayout();
                    fragmentType = "6";
                    replaceFragmnet(new UserProfileFragment(), R.id.frameLayout, true);
                    break;
                case "7":
                    finish();
                    if (sharedPrefrencesManger.getuserLoginTwitter()) {
                        logoutTwitter();
                        sharedPrefrencesManger.setuserLoginTwitter(false);
                        sharedPrefrencesManger.clearSharedTwitterSession();
                        startActivity(new Intent(this, LoginActivity.class));
                    } else {
                        sharedPrefrencesManger.setuserLogin("false");
                        startActivity(new Intent(this, LoginActivity.class));
                    }
                    break;
                case "8":
                    setVisibilities(1);
                    // hideHeaderLayout();
                    fragmentType = "8";
                    replaceFragmnet(new AllSharedActivity(), R.id.frameLayout, true);
                    break;
                default:
                    // setVisibilities(2);
                    replaceFragmnet(new StartScreenFragment(), R.id.frameLayout, false);
                    break;

            }
        } else {
//            nav_logo_img.setVisibility(View.VISIBLE);
//            replaceFragmnet(new StartScreenFragment(), R.id.frameLayout, false);
        }

        drawer.closeDrawer(GravityCompat.START);
//        }
    }


    public void initListner() {


    }


    public void setVisibilities(int fragNumber) {
        switch (fragNumber) {
            case 1: {
                headerLayoutID.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                nav_logo_img.setVisibility(View.VISIBLE);
                text_title.setVisibility(View.INVISIBLE);
                btn_createnew.setVisibility(View.INVISIBLE);
            }
            break;
            case 2: {
                headerLayoutID.setBackgroundColor(Color.TRANSPARENT);
                nav_logo_img.setVisibility(View.INVISIBLE);
                text_title.setVisibility(View.INVISIBLE);
                btn_createnew.setVisibility(View.VISIBLE);
            }
            break;

            case 4: {
                headerLayoutID.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                nav_logo_img.setVisibility(View.INVISIBLE);
                text_title.setVisibility(View.VISIBLE);
                text_title.setText("Members of PiggyLand");
                btn_createnew.setVisibility(View.VISIBLE);
                btn_createnew.setBackgroundResource(R.drawable.ic_action_search);
            }
            break;

            case 5: {
                headerLayoutID.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                nav_logo_img.setVisibility(View.INVISIBLE);
                text_title.setVisibility(View.VISIBLE);
                text_title.setText("Message for you");
                btn_createnew.setVisibility(View.VISIBLE);
                btn_createnew.setBackgroundResource(R.drawable.ic_action_search);
            }
            break;
            case 6: {
                headerLayoutID.setBackgroundColor(Color.TRANSPARENT);
                nav_logo_img.setVisibility(View.INVISIBLE);
                text_title.setVisibility(View.INVISIBLE);
                btn_createnew.setVisibility(View.VISIBLE);
                btn_createnew.setBackgroundResource(R.drawable.ic_action_edit);
            }
            break;


            default: {
                headerLayoutID.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                nav_logo_img.setVisibility(View.VISIBLE);
                text_title.setVisibility(View.INVISIBLE);
                btn_createnew.setVisibility(View.INVISIBLE);
            }
        }
    }


    public void clickEventSlide() {
        drawer.openDrawer(GravityCompat.START);

//        if (mSlideState) {
//            drawer.closeDrawer(GravityCompat.START);
//            mSlideState = false;
//            //   btn_sidemenu.setVisibility(View.VISIBLE);
//        } else {
//            //  btn_sidemenu.setVisibility(View.GONE);
//            drawer.openDrawer(GravityCompat.START);
//            mSlideState = true;
//        }
    }

    public static void setUserProInstance(UserProfileActivity regInstance) {
        UserProfileActivity.userProInstance = regInstance;
    }

    public static UserProfileActivity getRegInstance() {
        return userProInstance;
    }

    public void showHeaderLayout() {
        headerLayoutID.setVisibility(View.VISIBLE);
        setHeaderTitle("");
    }

    public void hideHeaderLayout() {
        headerLayoutID.setVisibility(View.INVISIBLE);
    }

    public void setHeaderTitle(String title) {
        titleTxt.setText(title);
    }


    public void logoutTwitter() {
        TwitterSession twitterSession = TwitterCore.getInstance().getSessionManager().getActiveSession();
        if (twitterSession != null) {
            ClearCookies(getApplicationContext());
            Twitter.getSessionManager().clearActiveSession();
            Twitter.logOut();
        }
    }

    public static void ClearCookies(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1) {
            CookieManager.getInstance().removeAllCookies(null);
            CookieManager.getInstance().flush();
        } else {
            CookieSyncManager cookieSyncMngr = CookieSyncManager.createInstance(context);
            cookieSyncMngr.startSync();
            CookieManager cookieManager = CookieManager.getInstance();
            cookieManager.removeAllCookie();
            cookieManager.removeSessionCookie();
            cookieSyncMngr.stopSync();
            cookieSyncMngr.sync();
        }
    }


    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
//        int id = item.getItemId();
//
//        if (id == R.id.new_drawing) {
//
//            //  Toast.makeText(this, "this", Toast.LENGTH_LONG).show();
//            // Handle the camera action
//        } else if (id == R.id.gallery) {
//            //  Toast.makeText(this, "this", Toast.LENGTH_LONG).show();
//        } else if (id == R.id.order) {
//            //  Toast.makeText(this, "this", Toast.LENGTH_LONG).show();
//        }

//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        if (fragmentType == "1") {

//            SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(this, SweetAlertDialog.NORMAL_TYPE);
//            sweetAlertDialog.setCancelText("Cancel")
//                    .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
//                        @Override
//                        public void onClick(SweetAlertDialog sweetAlertDialog) {
//                            sweetAlertDialog.dismiss();
////                            ptrHome.setRefreshing(false);
////                            loading = false;
//
//                        }
//                    })
//                    .setTitleText("Are you sure?")
//                    .setContentText("Won't be Exit")
//                    .setConfirmText("Yes,Exit it!")
//                    .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
//                        @Override
//                        public void onClick(SweetAlertDialog sweetAlertDialog) {
//                            sweetAlertDialog.cancel();
//                            finish();
//
//                        }
//                    });
//            sweetAlertDialog.show();

        }


        if (fragmentType == "2") {
//            showHeaderLayout();
//            setVisibilities(1);
//            clearBackStack();
//            replaceFragmnet(new StartScreenFragment(), R.id.frameLayout, false);
        }
        if (fragmentType == "8") {
            setVisibilities(1);
        }
        if (fragmentType == "5") {
            showHeaderLayout();
            setVisibilities(1);
//            clearBackStack();
//            replaceFragmnet(new StartScreenFragment(), R.id.frameLayout, false);
        }

        if (fragmentType == "102") {
            showHeaderLayout();
            if (MyGallery.is_in_action_mode) {
                MyGallery.clearActionM();
                return;
            } else {

            }
        }

        if (fragmentType == "103") {
            setVisibilities(5);
        }

        if (fragmentType == "4") {
            //  headerLayoutID.setBackgroundColor(Color.TRANSPARENT);
        } else {
            //  finish();
        }
        if (fragmentType == "6") {
            setVisibilities(1);
        } else {

        }
        if (fragmentType == "100") {
            showHeaderLayout();
        }
        if (fragmentType == "101") {

            if (hideShow == 0) {
                hideHeaderLayout();
                hideShow++;
            } else {

                //hideShow = 1;
                showHeaderLayout();
                hideShow = 0;
            }


        }
    }


}
