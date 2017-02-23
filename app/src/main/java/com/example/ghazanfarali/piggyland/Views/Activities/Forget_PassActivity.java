package com.example.ghazanfarali.piggyland.Views.Activities;

import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.ghazanfarali.piggyland.EndPoint.ApiClient;
import com.example.ghazanfarali.piggyland.EndPoint.ApiInterface;
import com.example.ghazanfarali.piggyland.EndPoint.DataResponse.ForgotPsswordResponse;
import com.example.ghazanfarali.piggyland.R;
import com.example.ghazanfarali.piggyland.Views.Activities.BaseMasterActivity.MasterActivity;
import com.example.ghazanfarali.piggyland.Views.Activities.SignUp.Views.SignUpActivity;

import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Forget_PassActivity extends MasterActivity {

    TextInputLayout ed_email;
    TextInputEditText tie_email;
    Button btn_userforgetpassword;
    private static final String TAG = Forget_PassActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_pass);


        initUI();

        btn_userforgetpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tie_email.getText().length() > 0 && tie_email.getText().length() > 0) {
                    hideKeyBoard();
                    ShowProgress(Forget_PassActivity.this);
                    pDialog.show();
                    try {
                        ApiInterface apiService =
                                ApiClient.getClient().create(ApiInterface.class);

                        Call<ForgotPsswordResponse> call = apiService.forgetPassword(tie_email.getText().toString());
                        call.enqueue(new Callback<ForgotPsswordResponse>() {
                            @Override
                            public void onResponse(Call<ForgotPsswordResponse> call, Response<ForgotPsswordResponse> response) {
                                ForgotPsswordResponse statusCode = response.body();//code();
                                pDialog.dismiss();
                                if (statusCode.getStatus().contentEquals("success")) {

                                    Toast.makeText(Forget_PassActivity.this, "Email send", Toast.LENGTH_SHORT).show();

                                } else {
                                    Toast.makeText(Forget_PassActivity.this, "Service error", Toast.LENGTH_SHORT).show();
                                }
                              //  Profile profile = response.body().getProfile();
                                //recyclerView.setAdapter(new MoviesAdapter(movies, R.layout.list_item_movie, getApplicationContext()));
                            }

                            @Override
                            public void onFailure(Call<ForgotPsswordResponse> call, Throwable t) {
                                pDialog.dismiss();
                                Log.e(TAG,t.toString());
                            }


                        });
                    } catch (Exception e) {
                        pDialog.dismiss();
                        e.getLocalizedMessage();
                    }
                    //startActivity(new Intent(LoginActivity.this, UserProfileActivity.class));

                } else {
                    pDialog.dismiss();
                    new SweetAlertDialog(Forget_PassActivity.this, SweetAlertDialog.ERROR_TYPE)
                            .setTitleText("Alert")
                            .setContentText("Please Enter username & password")
                            .show();
                  //  Toast.makeText(Forget_PassActivity.this, "Please Enter username & password", Toast.LENGTH_LONG).show();
                }
            }
        });

    }


    @Override
    public void initUI() {
        super.initUI();
        ed_email = (TextInputLayout) findViewById(R.id.ed_email);
        tie_email = (TextInputEditText) findViewById(R.id.tie_email);
        btn_userforgetpassword = (Button) findViewById(R.id.btn_userforgetpassword);
    }
}
