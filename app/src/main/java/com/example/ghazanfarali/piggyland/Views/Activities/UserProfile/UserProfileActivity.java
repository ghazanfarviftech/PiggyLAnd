package com.example.ghazanfarali.piggyland.Views.Activities.UserProfile;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.ghazanfarali.piggyland.R;
import com.example.ghazanfarali.piggyland.Views.Activities.BaseMasterActivity.MasterActivity;
import com.example.ghazanfarali.piggyland.Views.Activities.MyGallery.Views.MyGalleryMultiSelect;
import com.example.ghazanfarali.piggyland.Views.Fragments.BaseMasterFragment.UserProfile.UserProfileFragment;

/**
 * Created by Amir.jehangir on 1/10/2017.
 */
public class UserProfileActivity extends MasterActivity implements NavigationView.OnNavigationItemSelectedListener {


   private Button btn_sidemenu, btn_createnew;
    RelativeLayout backLayout;
    RelativeLayout helpLayout;
    DrawerLayout drawer;
    boolean mSlideState = false;


    public static UserProfileActivity userProInstance;
    RelativeLayout headerLayoutID;
    TextView titleTxt;
    private String fragmentType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.userprofile_activity);


        initUI();
        initListner();

    }

    @Override
    public void initUI() {
        userProInstance = this;

        headerLayoutID = (RelativeLayout) findViewById(R.id.headerLayoutID);
//        headerLayoutID.bringToFront();
        titleTxt =(TextView)findViewById(R.id.titleTxt);
        btn_sidemenu = (Button)findViewById(R.id.btn_menuimg);
        btn_createnew = (Button) findViewById(R.id.btn_createnew);
        backLayout = (RelativeLayout) findViewById(R.id.backLayout);
        helpLayout = (RelativeLayout) findViewById(R.id.helpLayout);
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);


        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);

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
                startActivity(new Intent(UserProfileActivity.this, MyGalleryMultiSelect.class));
            }
        });

        super.initUI();
        // navigationView.setNavigationItemSelectedListener(this);
    }





    private void displayNextFragment() {
        Log.v("fragmentIndex", fragmentType + " ");

        Bundle extra = getIntent().getExtras();
        if (extra != null) {
            fragmentType = "0";//getIntent().getExtras().getString("fragIndex");
        }


        if (fragmentType != null) {
            switch (fragmentType) {
                case "0":
                    replaceFragmnet(new UserProfileFragment(), R.id.frameLayout, false);
                    break;
                case "-1":
                    replaceFragmnet(new UserProfileFragment(), R.id.frameLayout, false);
                    break;
                case "4":
                    replaceFragmnet(new UserProfileFragment(), R.id.frameLayout, false);
                    break;
                case "7":
//                    Fragment loginFrag = new LoginFragment();
//                    Bundle bundle = new Bundle();
//                    bundle.putBoolean(ConstantUtils.showBackBtn, false);
//                    loginFrag.setArguments(bundle);
//                    replaceFragmnet(loginFrag, R.id.frameLayout, false);
                    break;

                default:
                    replaceFragmnet(new UserProfileFragment(), R.id.frameLayout, false);
                    break;

            }
        } else {
            replaceFragmnet(new UserProfileFragment(), R.id.frameLayout, false);
        }
//        }
    }




    public void initListner() {



    }

    public void clickEventSlide() {
        if (mSlideState) {
            drawer.closeDrawer(GravityCompat.START);
            mSlideState = false;
         //   btn_sidemenu.setVisibility(View.VISIBLE);
        } else {
          //  btn_sidemenu.setVisibility(View.GONE);
            drawer.openDrawer(GravityCompat.START);
            mSlideState = true;
        }
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
        headerLayoutID.setVisibility(View.GONE);
    }

    public void setHeaderTitle(String title) {
        titleTxt.setText(title);
    }


    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        finish();
    }
}
