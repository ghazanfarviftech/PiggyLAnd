package com.example.ghazanfarali.piggyland.Views.Fragments.BaseMasterFragment.UserProfile;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.ghazanfarali.piggyland.EndPoint.ApiClient;
import com.example.ghazanfarali.piggyland.EndPoint.ApiInterface;
import com.example.ghazanfarali.piggyland.EndPoint.DataResponse.EditProfileResponse;
import com.example.ghazanfarali.piggyland.EndPoint.DataResponse.GetUserProfile.GetUserProfileResponse;
import com.example.ghazanfarali.piggyland.R;
import com.example.ghazanfarali.piggyland.Utils.GlobalUtils;
import com.example.ghazanfarali.piggyland.Views.Fragments.BaseMasterFragment.MasterFragment;

import java.io.File;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Amir.jehangir on 1/12/2017.
 */
public class UserProfileFragment extends MasterFragment  {
    private View view;
    EditText ed_username,tv_email,ed_phone;
    ImageView img_profile;
    Button btn_createnew;

    String username,email,image,imgName,contact,mac;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if (view == null) {
            view = inflater.inflate(R.layout.userprofilefragment, container, false);
            getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
            initUI();

            if(sharedPrefrencesManger.getEmail() != null) {
                GetUserProfile(sharedPrefrencesManger.getEmail());
            }
            btn_createnew = (Button) userProfileActivity.findViewById(R.id.btn_createnew);
            btn_createnew.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    UpdateUserProfile();
                }
            });
            img_profile.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showAttachmentDialog();
                }
            });

            tv_email.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    tv_email.setFocusable(true);
                }
            });

        } else {
            if (view != null)
              //  userProfileActivity.hideHeaderLayout();
            userProfileActivity.setHeaderTitle("");
        }
        return view;

    }

    @Override
    public void initUI() {
        super.initUI();

        ed_username = (EditText)view.findViewById(R.id.ed_username);
        tv_email = (EditText)view.findViewById(R.id.tv_email);
        ed_phone = (EditText)view.findViewById(R.id.ed_phone);
        img_profile = (ImageView) view.findViewById(R.id.img_profile);

      //  ed_username.setFocusable(false);
        tv_email.setFocusable(false);
     //   ed_phone.setFocusable(false);

//        updateProfileData();
    }

    @Override
    public void onResume() {
        super.onResume();

      //  GetUserProfile(sharedPrefrencesManger.getEmail());
    }

    private void GetUserProfile(String UserEmail){

        if (UserEmail != null) {
            hideKeyBoard();
            ShowProgress(getActivity());
            StartProgressLoading();
            try {
                ApiInterface apiService =
                        ApiClient.getClient().create(ApiInterface.class);

                Call<GetUserProfileResponse> call = apiService.responseprofile(UserEmail);
                call.enqueue(new Callback<GetUserProfileResponse>() {
                    @Override
                    public void onResponse(Call<GetUserProfileResponse> call, Response<GetUserProfileResponse> response) {
                        GetUserProfileResponse statusCode = response.body();//code();
                        StopProgressLoading();
                        if (statusCode.getUsers()[0].getEmail().contentEquals(sharedPrefrencesManger.getEmail())) {
                            updateProfileData(statusCode);
                            GlobalUtils.showToast(getActivity(),"Updated");
                            Toast.makeText(getActivity(), "Updated", Toast.LENGTH_SHORT).show();

                        } else {
                            Toast.makeText(getActivity(), "error", Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onFailure(Call<GetUserProfileResponse> call, Throwable t) {
                        StopProgressLoading();
                        Toast.makeText(getActivity(), "Failed", Toast.LENGTH_SHORT).show();
                    }



                });
            } catch (Exception e) {
                StopProgressLoading();
                e.getLocalizedMessage();
            }
            //startActivity(new Intent(LoginActivity.this, UserProfileActivity.class));

        } else {
            StopProgressLoading();
            Toast.makeText(getActivity(), "Please Enter username & password", Toast.LENGTH_LONG).show();
        }




    }




   public void updateProfileData(GetUserProfileResponse getUserProfileResponse){






       if(getUserProfileResponse.getUsers()[0].getUsername() != null){
           ed_username.setText(getUserProfileResponse.getUsers()[0].getUsername());
       }
       if(getUserProfileResponse.getUsers()[0].getEmail() != null){
           tv_email.setText(getUserProfileResponse.getUsers()[0].getEmail());
       }

       if(getUserProfileResponse.getUsers()[0].getContact() != null){
           ed_phone.setText(getUserProfileResponse.getUsers()[0].getContact());
       }

    }



    private void setUserProfileData(){
        username = ed_username.getText().toString();
        email = sharedPrefrencesManger.getEmail();
        image = "";
        imgName = "user.jpg";
        contact = ed_phone.getText().toString();
        mac = sharedPrefrencesManger.getuserMac();


        sharedPrefrencesManger.setuserName(username);
        sharedPrefrencesManger.setuserPhoneNumber(contact);
    }

//@Field("username") String username, @Field("email") String email, @Field("image") String image, @Field("imgName") String imgName, @Field("contact") String contact, @Field("mac") String mac

    public void UpdateUserProfile(){
        setUserProfileData();
        if (ed_username.getText().length() > 0 && ed_username.getText().length() > 0) {
            hideKeyBoard();
            try {
                ApiInterface apiService =
                        ApiClient.getClient().create(ApiInterface.class);

                Call<EditProfileResponse> call = apiService.editProfile(username,email,image,imgName,contact,mac);
                call.enqueue(new Callback<EditProfileResponse>() {
                    @Override
                    public void onResponse(Call<EditProfileResponse> call, Response<EditProfileResponse> response) {
                        EditProfileResponse statusCode = response.body();//code();
                        if (statusCode.getStatus().contentEquals("Success")) {

                            Toast.makeText(getActivity(), "Updated", Toast.LENGTH_SHORT).show();

                        } else {
                            Toast.makeText(getActivity(), "error", Toast.LENGTH_SHORT).show();
                        }
                        //  Profile profile = response.body().getProfile();
                        //recyclerView.setAdapter(new MoviesAdapter(movies, R.layout.list_item_movie, getApplicationContext()));
                    }

                    @Override
                    public void onFailure(Call<EditProfileResponse> call, Throwable t) {

                    }




                });
            } catch (Exception e) {
                e.getLocalizedMessage();
            }
            //startActivity(new Intent(LoginActivity.this, UserProfileActivity.class));

        } else {
            Toast.makeText(getActivity(), "Please Enter username & password", Toast.LENGTH_LONG).show();
        }

    }





    public void showAttachmentDialog() {
        final Dialog addImageDialog;
        // popup..
        View view = getActivity().getLayoutInflater().inflate(R.layout.dialog_chooser, null);
        addImageDialog = new Dialog(getActivity());

        addImageDialog.setTitle("Image Picker");
        addImageDialog.setContentView(getActivity().getLayoutInflater().inflate(
                R.layout.dialog_chooser, null));
        addImageDialog.show();

        ImageButton ibCamera = (ImageButton) addImageDialog
                .findViewById(R.id.add_suggestion_dialog_ib_camera);
        ImageButton ibGallery = (ImageButton) addImageDialog
                .findViewById(R.id.add_suggestion_dialog_ib_gallery);

        ibCamera.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                addImageDialog.dismiss();
                //startCameraOrGallery("camera");
                Intent takePicture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                takePicture.putExtra(MediaStore.EXTRA_OUTPUT, setImageUri());
                startActivityForResult(takePicture, 0);
            }
        });
        ibGallery.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                addImageDialog.dismiss();
                // startCameraOrGallery("gallery");
                Intent pickPhoto = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(pickPhoto, 1);//one can be replaced with any action code

            }
        });

    }




    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 0:
                if (resultCode == Activity.RESULT_OK) {

                    selectedImagePath = getImagePath();


                    Glide
                            .with( getActivity() ) // safer!
                            .load( selectedImagePath )
                            .asBitmap()
                            .into( img_profile );


//
//                    Bitmap bitmap = BitmapFactory.decodeFile(selectedImagePath);
//                    int w = bitmap.getWidth();
//                    int h = bitmap.getHeight();
//                    for(int i =  0; i < w; i++){
//                        for(int j = 0; j < h; j++) {
//                            int pixel =  bitmap.getPixel(i, j);
//
//                            if(pixel == Color.WHITE) {
//                                GlobalUtils.showToast(getActivity(),"change color");
//                            }
//                        }
//                    }

                }

                break;
            case 1:
                if (resultCode == Activity.RESULT_OK) {
                    Uri selectedImage = data.getData();

                    Glide
                            .with(getActivity() ) // safer!
                            .load( selectedImage )
                            .asBitmap()
                            .into( img_profile );
                    /* Bitmap bitmap = null;
                    try {
                        bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImage);
                        this.canvas.drawBitmap(bitmap);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }*/

                }
                break;


        }

    }

    String imgPath;
    String selectedImagePath;
    public Uri setImageUri() {
        // Store image in dcim
        File file = new File(Environment.getExternalStorageDirectory() + "/DCIM/", "image" + new Date().getTime() + ".png");
        Uri imgUri = Uri.fromFile(file);
        this.imgPath = file.getAbsolutePath();
        return imgUri;
    }


    public String getImagePath() {
        return imgPath;
    }


}
