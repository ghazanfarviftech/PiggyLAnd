package com.example.ghazanfarali.piggyland.Views.Activities.SignUp.Views;

import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.Toast;

import com.example.ghazanfarali.piggyland.EndPoint.ApiClient;
import com.example.ghazanfarali.piggyland.EndPoint.ApiInterface;
import com.example.ghazanfarali.piggyland.EndPoint.DataResponse.LoginResponse;
import com.example.ghazanfarali.piggyland.R;
import com.example.ghazanfarali.piggyland.Views.Activities.BaseMasterActivity.MasterActivity;
import com.example.ghazanfarali.piggyland.Views.Activities.UserProfile.UserProfileActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpActivity extends MasterActivity {

    Button btn_createaccount;
    TextInputLayout til_username, til_password,ed_email,ed_contact;
    TextInputEditText tie_username, tei_password,tei_email,tei_contact;
    private static final String TAG = SignUpActivity.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
       // til_username = (TextInputLayout) findViewById(R.id.ed_username);
        til_password = (TextInputLayout) findViewById(R.id.ed_password);
       // tie_username= (TextInputEditText)findViewById(R.id.tie_username);
        tei_password= (TextInputEditText)findViewById(R.id.tei_password);

        ed_email = (TextInputLayout) findViewById(R.id.ed_email);
        ed_contact = (TextInputLayout) findViewById(R.id.ed_password_confrm);
        tei_email= (TextInputEditText)findViewById(R.id.tei_email);
        tei_contact= (TextInputEditText)findViewById(R.id.tei_password_cnfrm);

        btn_createaccount = (Button)findViewById(R.id.btn_createAccount);
        btn_createaccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(tei_email.getText().length() > 0  && tei_password.getText().length() > 0)
                {



                    String confrm = tei_contact.getText().toString();
                    String password = tei_password.getText().toString();
                    if(confrm.contentEquals(password))
                    {
                        hideKeyBoard();
                        try {
                            ApiInterface apiService =
                                    ApiClient.getClient().create(ApiInterface.class);
//                        Login task = new Login("fazila", "fazila", "123444");
//                        Gson gson = new Gson();
//                        gson.toJson(task);
                            WifiManager manager = (WifiManager) getSystemService(Context.WIFI_SERVICE);
                            WifiInfo info = manager.getConnectionInfo();
                            String address = info.getMacAddress();

                            sharedPrefrencesManger.setEmail(tei_email.getText().toString());
                            sharedPrefrencesManger.setPassword(tei_password.getText().toString());
                            sharedPrefrencesManger.setuserMac(address);

                            Call<LoginResponse> call = apiService.getSignUp(tei_email.getText().toString(),tei_password.getText().toString(),address);
                            call.enqueue(new Callback<LoginResponse>() {
                                @Override
                                public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                                    LoginResponse statusCode = response.body();//code();
                                    if(statusCode.getStatus().contentEquals("success"))
                                    {

                                        Log.e("Sign up response",response.body().getStatus());
                                        Intent in = new Intent(SignUpActivity.this, UserProfileActivity.class);
                                        in.putExtra("fragmentIndex","0");
                                        startActivity(in);

                                        //  startActivity(new Intent(SignUpActivity.this, UserProfileActivity.class));
                                    }else{
                                        Toast.makeText(SignUpActivity.this,"Error While Sign Up",Toast.LENGTH_SHORT).show();
                                    }
                                    // Profile profile = response.body().getProfile();
                                    //recyclerView.setAdapter(new MoviesAdapter(movies, R.layout.list_item_movie, getApplicationContext()));
                                }

                                @Override
                                public void onFailure(Call<LoginResponse> call, Throwable t) {
                                    // Log error here since request failed
                                    Log.e(TAG, t.toString());
                                }
                            });
                        } catch (Exception e) {
                            e.getLocalizedMessage();
                        }
                    }else{
                        Toast.makeText(SignUpActivity.this,"Password Does Not Matched",Toast.LENGTH_SHORT).show();
                    }

                }else{

                    Toast.makeText(SignUpActivity.this,"Please Fill the fields",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void hideKeyBoard() {
        View view = getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

}
