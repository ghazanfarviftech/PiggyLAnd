package com.example.ghazanfarali.piggyland.Views.Activities;

import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import com.example.ghazanfarali.piggyland.R;
import com.facebook.login.widget.LoginButton;

public class Forget_PassActivity extends AppCompatActivity {

    TextInputLayout ed_email;
    TextInputEditText tie_email;
    Button btn_userforgetpassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_pass);



        ed_email = (TextInputLayout) findViewById(R.id.ed_email);

        tie_email = (TextInputEditText) findViewById(R.id.tie_email);

        btn_userforgetpassword = (Button)findViewById(R.id.btn_userforgetpassword);
    }
}
