package com.example.ghazanfarali.piggyland.Utils;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.preference.PreferenceManager;
import android.util.Log;

import java.util.ArrayList;

import static android.Manifest.permission.ACCESS_WIFI_STATE;
import static android.Manifest.permission.INTERNET;
import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

/**
 * Created by Amir.jehangir on 2/6/2017.
 */
public class MarshmallowPermissions {
    private final static int WRITE_CALENDAR_RESULT = 100;
    private final static int READ_CALENDAR_RESULT  = 101;
    private final static int CAMERA_RESULT  = 102;
    private final static int READ_CONTACTS_RESULT  = 103;
    private final static int WRITE_CONTACTS_RESULT  = 104;
    private final static int ACCESS_FINE_LOCATION_RESULT  = 105;
    private final static int ACCESS_COARSE_LOCATION_RESULT  = 106;
    private final static int CALL_PHONE_RESULT  = 107;
  //  private final static int WRITE_EXTERNAL_STORAGE_RESULT  = 108;
    private final static int ALL_PERMISSIONS_RESULT  = 109;

    private final static int INTERNET_RESULT  = 1010;
    private final static int READ_EXTERNAL_STORAGE_RESULT  = 1011;
    private final static int WRITE_EXTERNAL_STORAGE_RESULT  = 1012;
    private final static int ACCESS_WIFI_STATE_RESULT  = 1013;
    private SharedPreferences sharedPreferences;
    private ArrayList<String> permissionsToRequest;
    private ArrayList<String> permissionsRejected;

    Activity act;

    public MarshmallowPermissions(Activity act) {

        this.act = act;

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(act);
    }

    @TargetApi(Build.VERSION_CODES.M)
    public void CheckPermission() {

        ArrayList<String> permissions = new ArrayList<>();
        int resultCode = 0;
        permissions.add(INTERNET);
        permissions.add(READ_EXTERNAL_STORAGE);
        permissions.add(WRITE_EXTERNAL_STORAGE);
        permissions.add(ACCESS_WIFI_STATE);
//        permissions.add(WRITE_CONTACTS);
//        permissions.add(ACCESS_FINE_LOCATION);
//        permissions.add(ACCESS_COARSE_LOCATION);
//        permissions.add(CALL_PHONE);
//        permissions.add(WRITE_EXTERNAL_STORAGE);
        resultCode = ALL_PERMISSIONS_RESULT;

        permissionsToRequest = findUnAskedPermissions(permissions);

        permissionsRejected = findRejectedPermissions(permissions);

        if (permissionsToRequest.size() > 0) {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                act.requestPermissions(permissionsToRequest.toArray(new String[permissionsToRequest.size()]), resultCode);
            }

            for (String perm : permissionsToRequest) {
                markAsAsked(perm);
            }
        }
        else
        {
            Log.e("Mars Permissions","ALL ACCEPTED");
          //  ThreadFn(act);
        }
    }

    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {

        switch (requestCode) {
            case INTERNET_RESULT:
                if (hasPermission(INTERNET)) {

                } else {
                    permissionsRejected.add(INTERNET);
                    makePostRequestSnack();
                }
                break;
            case READ_EXTERNAL_STORAGE_RESULT:
                if (hasPermission(READ_EXTERNAL_STORAGE)) {

                } else {
                    permissionsRejected.add(READ_EXTERNAL_STORAGE);
                    makePostRequestSnack();
                }
                break;
            case WRITE_EXTERNAL_STORAGE_RESULT:
                if (hasPermission(WRITE_EXTERNAL_STORAGE)) {

                } else {
                    permissionsRejected.add(WRITE_EXTERNAL_STORAGE);
                    makePostRequestSnack();
                }
                break;
            case ACCESS_WIFI_STATE_RESULT:
                if (hasPermission(ACCESS_WIFI_STATE)) {

                } else {
                    permissionsRejected.add(ACCESS_WIFI_STATE);
                    makePostRequestSnack();
                }
                break;
//            case WRITE_CONTACTS_RESULT:
//                if (hasPermission(WRITE_CONTACTS)) {
//
//                } else {
//                    permissionsRejected.add(WRITE_CONTACTS);
//                    makePostRequestSnack();
//                }
//                break;
//            case ACCESS_FINE_LOCATION_RESULT:
//                if (hasPermission(ACCESS_FINE_LOCATION)) {
//
//                } else {
//                    permissionsRejected.add(ACCESS_FINE_LOCATION);
//                    makePostRequestSnack();
//
//                }
//                break;
//            case ACCESS_COARSE_LOCATION_RESULT:
//                if (hasPermission(ACCESS_COARSE_LOCATION)) {
//
//                } else {
//                    permissionsRejected.add(ACCESS_COARSE_LOCATION);
//                    makePostRequestSnack();
//                }
//                break;
//            case CALL_PHONE_RESULT:
//                if (hasPermission(CALL_PHONE)) {
//
//                } else {
//                    permissionsRejected.add(CALL_PHONE);
//                    makePostRequestSnack();
//                }
//                break;
//            case WRITE_EXTERNAL_STORAGE_RESULT:
//                if (hasPermission(WRITE_EXTERNAL_STORAGE)) {
//
//                } else {
//                    permissionsRejected.add(WRITE_EXTERNAL_STORAGE);
//                    makePostRequestSnack();
//                }
//                break;
            case ALL_PERMISSIONS_RESULT:
                boolean someAccepted = false;
                boolean someRejected = false;
                for (String perms : permissionsToRequest) {
                    if (hasPermission(perms)) {
                        someAccepted = true;
                    } else {
                        someRejected = true;
                        permissionsRejected.add(perms);
                    }
                }

                if (permissionsRejected.size() > 0) {
                    someRejected = true;
                }

                if (someAccepted || someRejected) {

                    Log.e("Mars Permissions","SOME ACCEPTED");

                  //  ThreadFn(act);

                }
                /*if (someRejected) {
                    makePostRequestSnack();
                }*/
                break;
        }

    }

    private void makePostRequestSnack() {
        for(String perm: permissionsRejected){
            clearMarkAsAsked(perm);
        }
    }

    private boolean canMakeSmores() {
        return (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1);
    }

    private boolean hasPermission(String permission) {
        if (canMakeSmores()) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                return (act.checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED);
            }
        }
        return true;
    }

    private boolean shouldWeAsk(String permission) {
        return (sharedPreferences.getBoolean(permission, true));
    }

    private void markAsAsked(String permission) {
        sharedPreferences.edit().putBoolean(permission, false).apply();
    }

    private void clearMarkAsAsked(String permission) {
        sharedPreferences.edit().putBoolean(permission, true).apply();
    }

    private ArrayList<String> findUnAskedPermissions(ArrayList<String> wanted) {
        ArrayList<String> result = new ArrayList<String>();

        for (String perm : wanted) {
//            if (!hasPermission(perm) && shouldWeAsk(perm)) {
//                result.add(perm);
//            }
            if (!hasPermission(perm) && true) {
                result.add(perm);
            }
        }

        return result;
    }

    private ArrayList<String> findRejectedPermissions(ArrayList<String> wanted) {
        ArrayList<String> result = new ArrayList<String>();

        for (String perm : wanted) {
            if (!hasPermission(perm) && !shouldWeAsk(perm)) {
                result.add(perm);
            }
        }

        return result;
    }

//    public void ThreadFn(Activity act)
//    {
//        try {
//            Thread.sleep(3000);
//        } catch (InterruptedException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//
//            FirebaseCrash.report(new Exception("My first Android non-fatal error"));
//            FirebaseCrash.log("Activity created");
//        }
//
//        Intent intent = new Intent(act, MainActivity.class);
//        act.startActivity(intent);
//        act.finish();
//    }
}
