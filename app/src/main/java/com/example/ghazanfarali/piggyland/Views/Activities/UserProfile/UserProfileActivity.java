package com.example.ghazanfarali.piggyland.Views.Activities.UserProfile;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.ghazanfarali.piggyland.R;
import com.example.ghazanfarali.piggyland.Views.Activities.BaseMasterActivity.MasterActivity;
import com.example.ghazanfarali.piggyland.Views.Activities.Login.LoginActivity;
import com.example.ghazanfarali.piggyland.Views.Activities.MyGallery.Views.MyGalleryMultiSelect;
import com.example.ghazanfarali.piggyland.Views.Fragments.BaseMasterFragment.UserProfile.UserProfileFragment;
import com.example.ghazanfarali.piggyland.Views.Fragments.MessageforYou.MessageforyouFragment;
import com.example.ghazanfarali.piggyland.Views.Fragments.PeopleInPiggyLand.PeopleInPiggyLandFragment;
import com.example.ghazanfarali.piggyland.Views.Fragments.StartMainFragment.StartScreenFragment;

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
    TextView titleTxt,text_title;
    private String fragmentType;
    ImageView nav_logo_img, imageView_nav_main_logo;
    LinearLayout nav_header_main_ll;

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
        text_title = (TextView)findViewById(R.id.text_title);
//        headerLayoutID.bringToFront();
        titleTxt = (TextView) findViewById(R.id.titleTxt);
        btn_sidemenu = (Button) findViewById(R.id.btn_menuimg);
        btn_createnew = (Button) findViewById(R.id.btn_createnew);
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
                    fragmentType = "1";
                    displayNextFragment();
//                    fragmentType = "4";
//                    displayNextFragment();
//                    headerLayoutID.setBackgroundColor( getResources().getColor(R.color.colorPrimaryDark));
//                    Toast.makeText(UserProfileActivity.this,"this",Toast.LENGTH_LONG).show();
                    // Handle the camera action
                } else if (id == R.id.gallery) {
                    //titleTxt.setText("My Gallary");
                    fragmentType = "2";
                    displayNextFragment();
                    // Toast.makeText(UserProfileActivity.this,"this",Toast.LENGTH_LONG).show();
                } else if (id == R.id.order) {
                    //  Toast.makeText(UserProfileActivity.this,"this",Toast.LENGTH_LONG).show();
                } else if (id == R.id.memebers) {
                    fragmentType = "3";
                    displayNextFragment();
                } else if (id == R.id.message) {
                    fragmentType = "5";
                    displayNextFragment();
                } else if (id == R.id.profile) {
                    fragmentType = "6";
                    displayNextFragment();
                } else if (id == R.id.Logout) {
                    fragmentType = "7";
                    displayNextFragment();
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

        Bundle extra = getIntent().getExtras();
        if (extra != null) {
            fragmentType = getIntent().getExtras().getString("fragmentIndex");
             getIntent().removeExtra("fragmentIndex");
        }


        if (fragmentType != null) {
            switch (fragmentType) {
                case "0":
                    setVisibilities(2);
                    replaceFragmnet(new UserProfileFragment(), R.id.frameLayout, false);
                    break;
                case "-1":
                    replaceFragmnet(new UserProfileFragment(), R.id.frameLayout, false);
                    break;
                case "1":
                    setVisibilities(1);
                    replaceFragmnet(new StartScreenFragment(), R.id.frameLayout, false);
                    break;
                case "2":
                    setVisibilities(1);
                 //   replaceFragmnet(new MyGallery(), R.id.frameLayout, false);
                    startActivity(new Intent(this,MyGalleryMultiSelect.class));
                    break;
                case "3":
                    setVisibilities(4);
                    replaceFragmnet(new PeopleInPiggyLandFragment(), R.id.frameLayout, false);
                    break;
                case "4":
                    setVisibilities(1);
                    replaceFragmnet(new MessageforyouFragment(), R.id.frameLayout, true);
                    break;
                case "5":
                    setVisibilities(5);
                    replaceFragmnet(new MessageforyouFragment(), R.id.frameLayout, false);
                    break;
                case "6":
                    setVisibilities(2);
                    replaceFragmnet(new UserProfileFragment(), R.id.frameLayout, false);
                    break;
                case "7":
                    finish();
                    startActivity(new Intent(this, LoginActivity.class));
                    break;

                default:
                    setVisibilities(2);
                    replaceFragmnet(new UserProfileFragment(), R.id.frameLayout, false);
                    break;

            }
        } else {
            nav_logo_img.setVisibility(View.VISIBLE);
            replaceFragmnet(new StartScreenFragment(), R.id.frameLayout, false);
        }

        drawer.closeDrawer(GravityCompat.START);
//        }
    }


    public void initListner() {


    }


    public void setVisibilities(int fragNumber){
        switch (fragNumber){
            case 1:{
                headerLayoutID.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                nav_logo_img.setVisibility(View.VISIBLE);
                text_title.setVisibility(View.GONE);
                btn_createnew.setVisibility(View.GONE);
            }
            break;
            case 2:{
                headerLayoutID.setBackgroundColor(Color.TRANSPARENT);
                nav_logo_img.setVisibility(View.GONE);
                text_title.setVisibility(View.GONE);
                btn_createnew.setVisibility(View.VISIBLE);
            }break;

            case 4:{
                headerLayoutID.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                nav_logo_img.setVisibility(View.GONE);
                text_title.setVisibility(View.VISIBLE);
                text_title.setText("Members of PiggyLand");
                btn_createnew.setVisibility(View.VISIBLE);
                btn_createnew.setBackgroundResource(R.drawable.ic_action_search);
            }break;

            case 5:{
                headerLayoutID.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                nav_logo_img.setVisibility(View.GONE);
                text_title.setVisibility(View.VISIBLE);
                text_title.setText("Message for you");
                btn_createnew.setVisibility(View.VISIBLE);
                btn_createnew.setBackgroundResource(R.drawable.ic_action_search);
            }break;


            default:{
                headerLayoutID.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                nav_logo_img.setVisibility(View.VISIBLE);
                text_title.setVisibility(View.GONE);
                btn_createnew.setVisibility(View.GONE);
            }
        }
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

        if (id == R.id.new_drawing) {

          //  Toast.makeText(this, "this", Toast.LENGTH_LONG).show();
            // Handle the camera action
        } else if (id == R.id.gallery) {
          //  Toast.makeText(this, "this", Toast.LENGTH_LONG).show();
        } else if (id == R.id.order) {
          //  Toast.makeText(this, "this", Toast.LENGTH_LONG).show();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (fragmentType == "4") {
            //  headerLayoutID.setBackgroundColor(Color.TRANSPARENT);
        }
        else {
          //  finish();
        }
        //  finish();
    }
}
