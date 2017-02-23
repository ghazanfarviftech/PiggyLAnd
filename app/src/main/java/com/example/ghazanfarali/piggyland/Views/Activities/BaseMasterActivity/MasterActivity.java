package com.example.ghazanfarali.piggyland.Views.Activities.BaseMasterActivity;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.example.ghazanfarali.piggyland.Controls.BaseInterface;
import com.example.ghazanfarali.piggyland.Utils.SharedPrefrencesManger;

import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * Created by Amir.jehangir on 1/10/2017.
 */
public class MasterActivity extends AppCompatActivity implements BaseInterface {

    public SharedPrefrencesManger sharedPrefrencesManger;
    public String userName,userPassword,email,deviceId ;
    private FragmentTransaction transaction;
    public SweetAlertDialog pDialog;

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState,persistentState);
        //   setContentView(R.layout.activity_main);
        sharedPrefrencesManger = new SharedPrefrencesManger(this);


        initUI();
    }


    public void ShowProgress(Activity activity){
        pDialog = new SweetAlertDialog(activity, SweetAlertDialog.PROGRESS_TYPE);
        pDialog.getProgressHelper().setBarColor(Color.parseColor("#5FAE08"));
        pDialog.setTitleText("Loading");
        pDialog.setCancelable(false);
    }


    public void clearBackStack() {
        FragmentManager manager = getSupportFragmentManager();
        if (manager.getBackStackEntryCount() > 0) {
            FragmentManager.BackStackEntry first = manager.getBackStackEntryAt(0);
            manager.popBackStack(first.getId(), FragmentManager.POP_BACK_STACK_INCLUSIVE);
        }
    }





    public void AddFragment(Fragment fragment, int container, boolean addTobackStack) {

        transaction = getSupportFragmentManager().beginTransaction();
        if (!addTobackStack) {   // if fragment must add to back stack
            transaction.add(container, fragment,
                    "").commit();
        } else {
            transaction.add(container, fragment,
                    "").addToBackStack(null).commit();
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

//        userName = sharedPrefrencesManger.getuserName();
//        userPassword = sharedPrefrencesManger.getPassword();
//        email = sharedPrefrencesManger.getEmail();
//        deviceId = sharedPrefrencesManger.getDeviceId();
//        Log.v("deviceID", deviceId + " ");

    }
}
