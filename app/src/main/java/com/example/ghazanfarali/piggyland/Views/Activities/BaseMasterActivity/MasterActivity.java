package com.example.ghazanfarali.piggyland.Views.Activities.BaseMasterActivity;

import android.content.Context;
import android.os.Bundle;
import android.os.PersistableBundle;
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

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState,persistentState);
        //   setContentView(R.layout.activity_main);
        initUI();
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
