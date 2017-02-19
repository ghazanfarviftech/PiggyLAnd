package com.example.ghazanfarali.piggyland.Views.Activities.Login;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ghazanfarali.piggyland.EndPoint.ApiClient;
import com.example.ghazanfarali.piggyland.EndPoint.ApiInterface;
import com.example.ghazanfarali.piggyland.EndPoint.DataResponse.LoginResponse;
import com.example.ghazanfarali.piggyland.EndPoint.DataResponse.Profile;
import com.example.ghazanfarali.piggyland.MainActivity;
import com.example.ghazanfarali.piggyland.R;
import com.example.ghazanfarali.piggyland.Utils.MarshmallowPermissions;
import com.example.ghazanfarali.piggyland.Utils.SharedPrefrencesManger;
import com.example.ghazanfarali.piggyland.Views.Activities.BaseMasterActivity.MasterActivity;
import com.example.ghazanfarali.piggyland.Views.Activities.Forget_PassActivity;
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
import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterApiClient;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterLoginButton;
import com.twitter.sdk.android.core.models.User;
import com.twitter.sdk.android.core.services.AccountService;

import io.fabric.sdk.android.Fabric;
import retrofit2.Call;
import retrofit2.Response;


public class LoginActivity extends MasterActivity implements
        View.OnClickListener, GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener {

    private GoogleApiClient mGoogleApiClient;
    private ProgressDialog mProgressDialog;

    private SignInButton btnSignIn;
    private Button btnSignOut, btnRevokeAccess, btn_userlogin, btn_signUp;
    private static final int RC_SIGN_IN = 007;
    private static final String TAG = MainActivity.class.getSimpleName();
    ImageView imgProfilePic;
    CallbackManager callbackManager;
    LoginButton loginButton;

    TextInputLayout til_username, til_password;
    TextInputEditText tie_username, tei_password;
    MarshmallowPermissions mp;
    String UserName = "";
    TextView forgotpassword;

    //This is your KEY and SECRET
    //And it would be added automatically while the configuration
    private static final String TWITTER_KEY = "10RB4PJO61CCxnZfWiUMYoPbR";
    private static final String TWITTER_SECRET = "ttlz9vipR07Dd46ToAqnL9mrXQkkYxmiq3ejDKUMOfuMWvJA0t0";

    //Tags to send the username and image url to next activity using intent
    public static final String KEY_USERNAME = "username";
    public static final String KEY_PROFILE_IMAGE_URL = "image_url";

    //Twitter Login Button
    TwitterLoginButton twitterLoginButton;

    public SharedPrefrencesManger sharedPrefrencesManger;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Initializing TwitterAuthConfig, these two line will also added automatically while configuration we did
        TwitterAuthConfig authConfig = new TwitterAuthConfig(TWITTER_KEY, TWITTER_SECRET);
        Fabric.with(this, new Twitter(authConfig));

        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_login);
        //Initializing twitter login button
        sharedPrefrencesManger = new SharedPrefrencesManger(this);



        twitterLoginButton = (TwitterLoginButton) findViewById(R.id.twitterLogin);
        //Adding callback to the button
        twitterLoginButton.setCallback(new com.twitter.sdk.android.core.Callback<TwitterSession>() {
            @Override
            public void success(Result<TwitterSession> result) {
                login(result);
            }

            @Override
            public void failure(TwitterException exception) {

            }
        });
        initUI();
        initListner();

        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1) {
            mp = new MarshmallowPermissions(LoginActivity.this);
            mp.CheckPermission();
        }


    }

private void OpenMainActivity(){
    startActivity(new Intent(LoginActivity.this, UserProfileActivity.class));
}
    @Override
    public void initUI() {
        super.initUI();

        sharedPrefrencesManger = new SharedPrefrencesManger(this);

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
        btn_signUp = (Button) findViewById(R.id.btn_signUp);
        forgotpassword = (TextView)findViewById(R.id.forgotpassword);
        // btnSignIn.setOnClickListener(this);
        //  btnSignOut.setOnClickListener(this);
    }


    public void initListner() {

        forgotpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,Forget_PassActivity.class));
            }
        });

        btn_signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, SignUpActivity.class));
            }
        });


        btn_userlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(LoginActivity.this, UserProfileActivity.class));
                finish();

                if (tie_username.getText().length() > 0 && tei_password.getText().length() > 0) {
                    hideKeyBoard();
                    try {
                        ApiInterface apiService =
                                ApiClient.getClient().create(ApiInterface.class);


                        WifiManager manager = (WifiManager) getSystemService(Context.WIFI_SERVICE);
                        WifiInfo info = manager.getConnectionInfo();
                        String address = info.getMacAddress();

                        Call<LoginResponse> call = apiService.getLogin(tie_username.getText().toString(), tei_password.getText().toString(), address);
                        call.enqueue(new retrofit2.Callback<LoginResponse>() {
                            @Override
                            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                                LoginResponse statusCode = response.body();//code();
                                if (statusCode.getStatus().contentEquals("success")) {
                                    userName = tie_username.getText().toString();
                                    userPassword = tei_password.getText().toString();
                                    sharedPrefrencesManger.setEmail(userName);
                                    sharedPrefrencesManger.setuserID(response.body().getProfile().getId());
                                    sharedPrefrencesManger.setPassword(userPassword);
                                    sharedPrefrencesManger.setuserAuth(response.body().getAuth());
                                    sharedPrefrencesManger.setuserLogin("true");
                                    OpenMainActivity();
                                    finish();
                                } else {
                                    Toast.makeText(LoginActivity.this, "UserName/Password Incorrect", Toast.LENGTH_SHORT).show();
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

                } else {
                    Toast.makeText(LoginActivity.this, "Please Enter username & password", Toast.LENGTH_LONG).show();
                }

            }
        });


// facebook login
        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                OpenMainActivity();
            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        });


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
        twitterLoginButton.onActivityResult(requestCode, resultCode, data);
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


    //The login function accepting the result object
    public void login(Result<TwitterSession> result) {
        TwitterSession   session = result.data;
        Twitter          twitter = Twitter.getInstance();
        TwitterApiClient api     = twitter.core.getApiClient(session);
        AccountService   service = api.getAccountService();
        Call<User>       user    = service.verifyCredentials(true, true);
        user.enqueue(new com.twitter.sdk.android.core.Callback<User>()
        {
            @Override
            public void success(Result<User> userResult)
            {
                User user = userResult.data;
                sharedPrefrencesManger.setEmail(user.email);
                sharedPrefrencesManger.setuserName(user.name);
                sharedPrefrencesManger.setUserProfileImage(user.profileImageUrl.replace("_normal", ""));

sharedPrefrencesManger.setuserLoginTwitter(true);
                OpenMainActivity();
            }

            @Override
            public void failure(TwitterException exc)
            {
                Log.d("TwitterKit", "Verify Credentials Failure", exc);
            }
        });
    }




}
