package com.example.ghazanfarali.piggyland.Utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import com.example.ghazanfarali.piggyland.R;

/**
 * Created by Amir.jehangir on 1/10/2017.
 */
public class SharedPrefrencesManger  {
    private Context mContext;
    private SharedPreferences sharedPrefs;
    private SharedPreferences.Editor prefsEditor;


    public SharedPrefrencesManger(Context context) {
        mContext = context;
        sharedPrefs = context.getSharedPreferences(mContext.getString(R.string.APP_SHARED_PREFS), Activity.MODE_PRIVATE);
        prefsEditor = sharedPrefs.edit();
    }

//    public void setLanguage(String lang) {
//        prefsEditor.putString(ConstantUtils.language, lang);
//        prefsEditor.commit();
//    }
//
//    public String getLanguage() {
//        return sharedPrefs.getString(ConstantUtils.language, "");
//    }
public void setEmail(String deviceId) {
    prefsEditor.putString(ConstantUtils.email, deviceId);
    prefsEditor.commit();
}

    public String getEmail() {
        return sharedPrefs.getString(ConstantUtils.email, "");
    }

    public void setFacebooklogin(boolean isFiristTime) {
        prefsEditor.putBoolean(ConstantUtils.isFiristTime, isFiristTime);
        prefsEditor.commit();
    }

    public boolean isFacebooklogin() {
        return sharedPrefs.getBoolean(ConstantUtils.isFiristTime, false);

    }

    public void setGooglelogin(boolean isFiristTime) {
        prefsEditor.putBoolean(ConstantUtils.isFiristTime, isFiristTime);
        prefsEditor.commit();
    }

    public boolean isGooglelogin() {
        return sharedPrefs.getBoolean(ConstantUtils.isFiristTime, false);

    }
    public void setPassword(String password) {
        prefsEditor.putString(ConstantUtils.userPassword, password);
        prefsEditor.commit();
    }

    public String getPassword() {
        return sharedPrefs.getString(ConstantUtils.userPassword, "");
    }

    public void setIsFiristTime(boolean isFiristTime) {
        prefsEditor.putBoolean(ConstantUtils.isFiristTime, isFiristTime);
        prefsEditor.commit();
    }

    public boolean isFiristTimeLaunch() {
        return sharedPrefs.getBoolean(ConstantUtils.isFiristTime, false);

    }

    public void setDeviceId(String deviceId) {
        prefsEditor.putString(ConstantUtils.deviceId, deviceId);
        prefsEditor.commit();
    }

    public String getDeviceId() {
        return sharedPrefs.getString(ConstantUtils.deviceId, "deviceId");
    }

    public void setFullName(String deviceId) {
        prefsEditor.putString(ConstantUtils.fullName, deviceId);
        prefsEditor.commit();
    }

    public String getFullName() {
        return sharedPrefs.getString(ConstantUtils.fullName, "");
    }


    public void setuserName(String deviceId) {
        prefsEditor.putString(ConstantUtils.userName, deviceId);
        prefsEditor.commit();
    }

    public String getuserName() {
        return sharedPrefs.getString(ConstantUtils.userName, "");
    }


    public void setUserProfileImage(String deviceId) {
        prefsEditor.putString(ConstantUtils.userProfileImage, deviceId);
        prefsEditor.commit();
    }

    public String getUserProfileImage() {
        return sharedPrefs.getString(ConstantUtils.userProfileImage, "");
    }



    public void clearSharedPref() {
        prefsEditor.remove(ConstantUtils.fullName).commit();
        prefsEditor.remove(ConstantUtils.email).commit();
        prefsEditor.remove(ConstantUtils.gender).commit();
        prefsEditor.remove(ConstantUtils.userProfileImage).commit();
        prefsEditor.remove(ConstantUtils.fullName).commit();
        prefsEditor.remove(ConstantUtils.facebooklogin).commit();
        prefsEditor.remove(ConstantUtils.googlelogin).commit();
    }

}
