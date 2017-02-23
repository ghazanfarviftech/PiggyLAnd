package com.example.ghazanfarali.piggyland.Views.Fragments.BaseMasterFragment;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;

import com.example.ghazanfarali.piggyland.Controls.BaseInterface;
import com.example.ghazanfarali.piggyland.Utils.SharedPrefrencesManger;
import com.example.ghazanfarali.piggyland.Utils.ValidationHelper;
import com.example.ghazanfarali.piggyland.Views.Activities.UserProfile.UserProfileActivity;

import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * Created by Amir.jehangir on 1/12/2017.
 */
public class MasterFragment extends Fragment implements BaseInterface {
    public UserProfileActivity userProfileActivity;
    public SharedPrefrencesManger sharedPrefrencesManger;
   // private RetrofitErrorHandeler retrofitErrorHandeler;
    public ValidationHelper validationHelper;
    public SweetAlertDialog pDialog;
    public static MasterFragment newInstance() {

        return new MasterFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        initUI();
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void initUI() {
        sharedPrefrencesManger = new SharedPrefrencesManger(getActivity());
        validationHelper = new ValidationHelper();
        userProfileActivity = UserProfileActivity.getRegInstance();

    }

    public void ShowProgress(Activity activity){
        pDialog = new SweetAlertDialog(activity, SweetAlertDialog.PROGRESS_TYPE);
        pDialog.getProgressHelper().setBarColor(Color.parseColor("#5FAE08"));
        pDialog.setTitleText("Loading");
        pDialog.setCancelable(false);
    }

    public void StartProgressLoading(){
        pDialog.show();
    }
    public void StopProgressLoading(){
        pDialog.dismiss();
    }

    public void HideMainHeader(){
        userProfileActivity.hideHeaderLayout();
    }

    public void hideKeyBoard() {
        if (getActivity() != null) {
            View view = getActivity().getCurrentFocus();
            if (view != null) {
                InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        }
    }

    private Animation loadAnimation(int animType) {
        Animation animation = AnimationUtils.loadAnimation(getActivity(), animType);
        animation.setFillEnabled(true);
        animation.setFillAfter(true);
        return animation;
    }

    public void setTextInputLayoutError(TextInputLayout textInputLayoutError, String error) {
        textInputLayoutError.setError(error);
    }

    public void removeTextInputLayoutError(TextInputLayout textInputLayoutError) {
        textInputLayoutError.setError(null);
    }
}
