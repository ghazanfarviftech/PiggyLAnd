package com.example.ghazanfarali.piggyland.Views.Activities.BaseMasterActivity;

import android.content.Context;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.example.ghazanfarali.piggyland.Controls.BaseInterface;
import com.example.ghazanfarali.piggyland.Utils.SharedPrefrencesManger;

/**
 * Created by Amir.jehangir on 1/10/2017.
 */
public class MasterActivity extends AppCompatActivity implements BaseInterface {

    public SharedPrefrencesManger sharedPrefrencesManger;
    public String userName,userPassword,email,deviceId ;
    private FragmentTransaction transaction;

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState,persistentState);
        //   setContentView(R.layout.activity_main);
        initUI();
    }



    public void clearBackStack() {
        FragmentManager manager = getSupportFragmentManager();
        if (manager.getBackStackEntryCount() > 0) {
            FragmentManager.BackStackEntry first = manager.getBackStackEntryAt(0);
            manager.popBackStack(first.getId(), FragmentManager.POP_BACK_STACK_INCLUSIVE);
        }
    }

    public void replaceFragmnet(Fragment fragment, int container, boolean addTobackStack) {
        transaction = getSupportFragmentManager().beginTransaction();
        FragmentManager fm = getSupportFragmentManager(); // didnot add fragment in back stack twice.
        for (int entry = 0; entry < fm.getBackStackEntryCount(); entry++) {
            if (fm.getFragments().get(entry).getClass().equals(fragment.getClass())) {
                removeExisitingFragment(fragment);
            }
        }
        if (!addTobackStack) {   // if fragment must add to back stack
            transaction.replace(container, fragment,
                    "").commit();
        } else {
            transaction.replace(container, fragment,
                    "").addToBackStack(null).commit();
        }
        getCurrentFragment();
    }

    public void removeExisitingFragment(Fragment myFrag) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction trans = manager.beginTransaction();
        trans.remove(myFrag);
        trans.commit();
        manager.popBackStack();
    }

    private Fragment getCurrentFragment() {


        return null;
    }

    public void hideKeyBoard() {
        View view = getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    @Override
    public void initUI() {
        sharedPrefrencesManger = new SharedPrefrencesManger(this);
        userName = sharedPrefrencesManger.getuserName();
        userPassword = sharedPrefrencesManger.getPassword();
        email = sharedPrefrencesManger.getEmail();
        deviceId = sharedPrefrencesManger.getDeviceId();
        Log.v("deviceID", deviceId + " ");

    }
}
