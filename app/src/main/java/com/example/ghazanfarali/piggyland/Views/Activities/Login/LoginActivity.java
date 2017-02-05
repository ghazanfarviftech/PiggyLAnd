package com.example.ghazanfarali.piggyland.Views.Activities.Login;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.ghazanfarali.piggyland.EndPoint.ApiClient;
import com.example.ghazanfarali.piggyland.EndPoint.ApiInterface;
import com.example.ghazanfarali.piggyland.EndPoint.DataResponse.LoginResponse;
import com.example.ghazanfarali.piggyland.EndPoint.DataResponse.Profile;
import com.example.ghazanfarali.piggyland.MainActivity;
import com.example.ghazanfarali.piggyland.R;
import com.example.ghazanfarali.piggyland.Views.Activities.BaseMasterActivity.MasterActivity;
import com.example.ghazanfarali.piggyland.Views.Activities.SignUp.Views.SignUpActivity;
import com.example.ghazanfarali.piggyland.Views.Activities.UserProfile.UserProfileActivity;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends MasterActivity implements
        View.OnClickListener, GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener {

    private GoogleApiClient mGoogleApiClient;
    private ProgressDialog mProgressDialog;

    private SignInButton btnSignIn;
    private Button btnSignOut, btnRevokeAccess, btn_userlogin,btn_signUp;
    private static final int RC_SIGN_IN = 007;
    private static final String TAG = MainActivity.class.getSimpleName();
    ImageView imgProfilePic;
    CallbackManager callbackManager;
    LoginButton loginButton;

    TextInputLayout til_username, til_password;
    TextInputEditText tie_username, tei_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_login);
        initUI();
        initListner();

    }


    @Override
    public void initUI() {
        super.initUI();

//        ActionBar actionBar = getSupportActionBar();
//        actionBar.hide();

        callbackManager = CallbackManager.Factory.create();
        loginButton = (LoginButton) findViewById(R.id.facebook_login_button);
        til_username = (TextInputLayout) findViewById(R.id.ed_username);
        til_password = (TextInputLayout) findViewById(R.id.ed_password);
        tie_username = (TextInputEditText) findViewById(R.id.tie_username);
        tei_password = (TextInputEditText) findViewById(R.id.tei_password);

        btn_userlogin = (Button) findViewById(R.id.btn_userlogin);
      //  btnSignIn = (SignInButton) findViewById(R.id.google_login_button);
//        btnSignOut = (Button) findViewById(R.id.btn_sign_out);
//        btnRevokeAccess = (Button) findViewById(R.id.btn_revoke_access);
//        imgProfilePic = (ImageView) findViewById(R.id.imgProfilePic);
        btn_signUp =  (Button) findViewById(R.id.btn_signUp);
       // btnSignIn.setOnClickListener(this);
      //  btnSignOut.setOnClickListener(this);
    }


    public void initListner() {

        btn_signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, SignUpActivity.class));
            }
        });




        btn_userlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(tie_username.getText().length() > 0  && tei_password.getText().length() > 0){
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

                       /* Call<LoginResponse> call = apiService.getLogin(
                                "test@gmail.com","test","123444");*/
                        Call<LoginResponse> call = apiService.getLogin(tie_username.getText().toString(),tei_password.getText().toString(),address);
                        call.enqueue(new Callback<LoginResponse>() {
                            @Override
                            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                                LoginResponse statusCode = response.body();//code();
                                if(statusCode.getStatus().contentEquals("success"))
                                {
                                    startActivity(new Intent(LoginActivity.this, UserProfileActivity.class));
                                    finish();
                                }else{
                                    Toast.makeText(LoginActivity.this,"UserName/Password Incorrect",Toast.LENGTH_SHORT).show();
                                }
                                Profile profile = response.body().getProfile();
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
                    //startActivity(new Intent(LoginActivity.this, UserProfileActivity.class));

                }else{
                    Toast.makeText(LoginActivity.this,"Please Enter username & password",Toast.LENGTH_LONG).show();
                }



                // startActivity(new Intent(LoginActivity.this, MainActivity.class));
              //  startActivity(new Intent(LoginActivity.this, UserProfileActivity.class));
//                if (tie_username.getText().toString().contains("admin") || tei_password.getText().toString().contains("admin")) {
//                    hideKeyBoard();
//                    startActivity(new Intent(LoginActivity.this, Main2Activity.class));
//
//                    finish();
//
//                } else {
//                  //  startActivity(new Intent(LoginActivity.this, UserProfileActivity.class));
//                    Toast.makeText(LoginActivity.this, "Please Enter username & password", Toast.LENGTH_LONG).show();
//                }


            }
        });


// facebook login
        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Intent i = new Intent(LoginActivity.this, UserProfileActivity.class);
                startActivity(i);
            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        });


// google login
//        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
//                .requestIdToken(LoginActivity.this.getResources().getString(R.string.server_client_id))
//                .requestEmail()
//                .build();
//
//        mGoogleApiClient = new GoogleApiClient.Builder(this)
//                //  .enableAutoManage(this, this)
//                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
//                .build();

        // Customizing G+ button
     //   btnSignIn.setSize(SignInButton.SIZE_STANDARD);
       // btnSignIn.setScopes(gso.getScopeArray());

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        switch (id) {
//            case R.id.google_login_button:
//                signIn();
//                break;

//            case R.id.btn_sign_out:
//                signOut();
//                break;

          /*  case R.id.btn_revoke_access:
                revokeAccess();
                break;*/
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
        }
    }

    @Override
    public void onStart() {
        super.onStart();

//        OptionalPendingResult<GoogleSignInResult> opr = Auth.GoogleSignInApi.silentSignIn(mGoogleApiClient);
//        if (opr.isDone()) {
//            // If the user's cached credentials are valid, the OptionalPendingResult will be "done"
//            // and the GoogleSignInResult will be available instantly.
//            Log.d(TAG, "Got cached sign-in");
//            GoogleSignInResult result = opr.get();
//            handleSignInResult(result);
//        } else {
//            // If the user has not previously signed in on this device or the sign-in has expired,
//            // this asynchronous branch will attempt to sign in the user silently.  Cross-device
//            // single sign-on will occur in this branch.
//            // showProgressDialog();
//            opr.setResultCallback(new ResultCallback<GoogleSignInResult>() {
//                @Override
//                public void onResult(GoogleSignInResult googleSignInResult) {
//                    // hideProgressDialog();
//                    handleSignInResult(googleSignInResult);
//                }
//            });
//        }
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        // An unresolvable error has occurred and Google APIs (including Sign-In) will not
        // be available.
        Log.d(TAG, "onConnectionFailed:" + connectionResult);
    }

    private void showProgressDialog() {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(this);
            mProgressDialog.setMessage("Please Wait");
            mProgressDialog.setIndeterminate(true);
        }

        mProgressDialog.show();
    }

    private void hideProgressDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.hide();
        }
    }

    private void updateUI(boolean isSignedIn) {
        if (isSignedIn) {
            btnSignIn.setVisibility(View.GONE);
            btnSignOut.setVisibility(View.VISIBLE);
            btnRevokeAccess.setVisibility(View.VISIBLE);
            // llProfileLayout.setVisibility(View.VISIBLE);
        } else {
            btnSignIn.setVisibility(View.VISIBLE);
            btnSignOut.setVisibility(View.GONE);
            btnRevokeAccess.setVisibility(View.GONE);
            //llProfileLayout.setVisibility(View.GONE);
        }
    }

    private void signIn() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }


    private void signOut() {
        Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(
                new ResultCallback<Status>() {
                    @Override
                    public void onResult(Status status) {
                        updateUI(false);
                    }
                });
    }

    private void revokeAccess() {
        Auth.GoogleSignInApi.revokeAccess(mGoogleApiClient).setResultCallback(
                new ResultCallback<Status>() {
                    @Override
                    public void onResult(Status status) {
                        updateUI(false);
                    }
                });
    }

    private void handleSignInResult(GoogleSignInResult result) {
        Log.d(TAG, "handleSignInResult:" + result.isSuccess());
        if (result.isSuccess()) {
            // Toast.makeText(this, "login success", Toast.LENGTH_LONG).show();
            // Signed in successfully, show authenticated UI.
            GoogleSignInAccount acct = result.getSignInAccount();

            Log.e(TAG, "display name: " + acct.getDisplayName());

            String personName = acct.getDisplayName();
            //String personPhotoUrl = acct.getPhotoUrl().toString();
            String email = acct.getEmail();

            //  Log.e(TAG, "Name: " + personName + ", email: " + email
            //        + ", Image: " + personPhotoUrl);

            // txtName.setText(personName);
            // txtEmail.setText(email);
            /*Glide.with(getApplicationContext()).load(personPhotoUrl)
                    .thumbnail(0.5f)
                    .crossFade()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(imgProfilePic);*/
            startActivity(new Intent(LoginActivity.this, UserProfileActivity.class));
            finish();
            updateUI(true);
        } else {
            // Signed out, show unauthenticated UI.
            // updateUI(false);
            startActivity(new Intent(LoginActivity.this, UserProfileActivity.class));
            finish();
        }
    }


    @Override
    public void onConnected(@Nullable Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

}