package com.example.ghazanfarali.piggyland.Views.Fragments.StartMainFragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.example.ghazanfarali.piggyland.EndPoint.ApiClient;
import com.example.ghazanfarali.piggyland.EndPoint.ApiInterface;
import com.example.ghazanfarali.piggyland.EndPoint.DataResponse.SaveToGalleryResponse;
import com.example.ghazanfarali.piggyland.R;
import com.example.ghazanfarali.piggyland.Views.Activities.Drawing.Drawing2Activity;
import com.example.ghazanfarali.piggyland.Views.Activities.Drawing.Views.AllSharedActivity;
import com.example.ghazanfarali.piggyland.Views.Activities.MyGallery.Views.MyGalleryMultiSelect;
import com.example.ghazanfarali.piggyland.Views.Fragments.BaseMasterFragment.MasterFragment;
import com.example.ghazanfarali.piggyland.Views.Fragments.DrawingFragment.Drawing2Fragment;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Amir.jehangir on 2/3/2017.
 */
public class StartScreenFragment extends MasterFragment {
    private View view;
    Button create_new ;
    LinearLayout gallery;
    LinearLayout order;
    LinearLayout share;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if (view == null) {
            view = inflater.inflate(R.layout.startscreen_fragment, container, false);
//            userProfileActivity.headerLayoutID.setVisibility(View.VISIBLE);
            // mContext.getApplicationContext();
            initUI();


//            startService();
        } else {
            if (view != null)
                //  userProfileActivity.hideHeaderLayout();
                userProfileActivity.headerLayoutID.setVisibility(View.VISIBLE);
                userProfileActivity.setHeaderTitle("");
        }

        return view;

    }



    public void Listner(){

        create_new = (Button)view.findViewById(R.id.create_new);
        create_new.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(),Drawing2Activity.class);
                startActivity(i);

                //userProfileActivity.headerLayoutID.setVisibility(View.GONE);
                //userProfileActivity.replaceFragmnet(new Drawing2Fragment(), R.id.frameLayout, true);
            }
        });

        gallery = (LinearLayout)view.findViewById(R.id.gallery);
        gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(),MyGalleryMultiSelect.class);
                startActivity(i);
            }
        });


        order = (LinearLayout)view.findViewById(R.id.order);
        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              /*  Intent i = new Intent(StartActivity.this,MembersActivity.class);
                startActivity(i);*/
            }
        });

        share = (LinearLayout)view.findViewById(R.id.share);
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent i = new Intent(getActivity(), AllSharedActivity.class);
//                startActivity(i);
                userProfileActivity.replaceFragmnet(new AllSharedActivity(), R.id.frameLayout, true);
            }
        });
    }

    @Override
    public void initUI() {
        super.initUI();

        Listner();
    }


  /*public void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);
        switch (requestCode) {
            case 0:
                if (requestCode == 0) {
                    // Uri selectedImage = imageReturnedIntent.getData();
                    //try {
                    selectedImagePath = getImagePath();
                    //imgUser.setImageBitmap(decodeFile(selectedImagePath));
                   *//* BitmapFactory.Options bmOptions = new BitmapFactory.Options();
                    Bitmap bitmap = BitmapFactory.decodeFile(selectedImagePath, bmOptions);
                    // Bitmap bitmap = null;//MediaStore.Images.Media.getBitmap(this.getContentResolver(),selectedImage);
                    this.canvas.drawBitmap(bitmap);*//*
                    SimpleTarget target2 = new SimpleTarget<Bitmap>( Drawing2Fragment.canvas.getWidth(), canvas.getHeight() ) {
                        @Override
                        public void onResourceReady(Bitmap bitmap, GlideAnimation glideAnimation) {
                            //imageView2.setImageBitmap( bitmap );
                            canvas.drawBitmap(bitmap);
                        }
                    };
                    Glide
                            .with( getActivity() ) // safer!
                            .load( selectedImagePath )
                            .asBitmap()
                            .into( target2 );


                }
                //imageview.setImageURI(selectedImage);


                break;
            case 1:
                if (requestCode == 1) {
                    Uri selectedImage = imageReturnedIntent.getData();
                    //imageview.setImageURI(selectedImage);
                    SimpleTarget target2 = new SimpleTarget<Bitmap>( canvas.getWidth(), canvas.getHeight() ) {
                        @Override
                        public void onResourceReady(Bitmap bitmap, GlideAnimation glideAnimation) {
                            //imageView2.setImageBitmap( bitmap );
                            canvas.drawBitmap(bitmap);
                        }
                    };
                    Glide
                            .with( Drawing2Activity.this ) // safer!
                            .load( selectedImage )
                            .asBitmap()
                            .into( target2 );
                    *//* Bitmap bitmap = null;
                    try {
                        bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImage);
                        this.canvas.drawBitmap(bitmap);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }*//*

                }
                break;
            case 3:
                if(imageReturnedIntent != null)
                {
                    Bundle bundle = imageReturnedIntent.getExtras();
                    if(bundle.isEmpty())
                    {

                    }else {
                        String imageTitle = bundle.getString("Title");
                        String Description = bundle.getString("Description");
                        String FullPath = imageName;//bundle.getString("FullPath");


                        try {
                            ApiInterface apiService =
                                    ApiClient.getClient().create(ApiInterface.class);
                           *//* *//**//*Login task = new Login("fazila", "fazila", "123444");*//**//*
                            Gson gson = new Gson();
                            gson.toJson(task);*//*



                            Call<SaveToGalleryResponse> call = apiService.saveImageToServer(encodedImage,FullPath,Description,imageTitle,"1");

                            call.enqueue(new Callback<SaveToGalleryResponse>() {
                                @Override
                                public void onResponse(Call<SaveToGalleryResponse> call, Response<SaveToGalleryResponse> response) {
                                    SaveToGalleryResponse statusCode = response.body();//code();
                                    if(statusCode.getStatus().contentEquals("Success"))
                                    {
                                        //startActivity(new Intent(LoginActivity.this, StartActivity.class));
                                    }else{
                                        // Toast.makeText(Drawing2Activity.this,"UserName/Password Incorrect",Toast.LENGTH_SHORT).show();
                                    }

                                    //recyclerView.setAdapter(new MoviesAdapter(movies, R.layout.list_item_movie, getApplicationContext()));
                                }

                                @Override
                                public void onFailure(Call<SaveToGalleryResponse> call, Throwable t) {
                                    // Log error here since request failed
                                    Log.e("", t.toString());
                                }
                            });
                        } catch (Exception e) {
                            e.getLocalizedMessage();
                        }
                    }
                    *//*String[] MainName =FullPathimageName.split("\\.");
                    String image = MainName[0]+"_"+imageName;
                    //byte[] bytes = canvas.getBitmapAsByteArray(Bitmap.CompressFormat.PNG, 100);
                    File file = new File(android.os.Environment.getExternalStorageDirectory()+"/","PiggyLandShare");
                    File f = null;
                    if(!file.exists()) {
                        file.mkdirs();
                        f = new File(MainName[0], image + ".jpg");
                    }else{
                        f = new File(MainName[0], image + ".jpg");
                    }*//*
                    *//*FileOutputStream fos = null;
                    try {
                        fos = new FileOutputStream(f);
                        fos.write(bytes);
                        fos.flush();
                        fos.close();
                        Toast.makeText(Drawing2Activity.this,"File Saved",Toast.LENGTH_SHORT).show();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }*//*
                    finish();
                }
                break;
            case 4:

                if(imageReturnedIntent != null)
                {
                    Bundle bundle = imageReturnedIntent.getExtras();
                    if(bundle.isEmpty())
                    {

                    }else {
                        String imageTitle = bundle.getString("Title");
                        String Description = bundle.getString("Description");
                        String FullPath = imageName;//bundle.getString("FullPath");


                        try {
                            ApiInterface apiService =
                                    ApiClient.getClient().create(ApiInterface.class);
                           *//* *//**//*Login task = new Login("fazila", "fazila", "123444");*//**//*
                            Gson gson = new Gson();
                            gson.toJson(task);*//*



                            Call<SaveToGalleryResponse> call = apiService.saveImageToServer(encodedImage2,FullPath,Description,imageTitle,"1");

                            call.enqueue(new Callback<SaveToGalleryResponse>() {
                                @Override
                                public void onResponse(Call<SaveToGalleryResponse> call, Response<SaveToGalleryResponse> response) {
                                    SaveToGalleryResponse statusCode = response.body();//code();
                                    if(statusCode.getStatus().contentEquals("Success"))
                                    {
                                        //startActivity(new Intent(LoginActivity.this, StartActivity.class));
                                    }else{
                                        // Toast.makeText(Drawing2Activity.this,"UserName/Password Incorrect",Toast.LENGTH_SHORT).show();
                                    }

                                    //recyclerView.setAdapter(new MoviesAdapter(movies, R.layout.list_item_movie, getApplicationContext()));
                                }

                                @Override
                                public void onFailure(Call<SaveToGalleryResponse> call, Throwable t) {
                                    // Log error here since request failed
                                    Log.e("", t.toString());
                                }
                            });
                        } catch (Exception e) {
                            e.getLocalizedMessage();
                        }
                    }
                    *//*String[] MainName =FullPathimageName.split("\\.");
                    String image = MainName[0]+"_"+imageName;
                    //byte[] bytes = canvas.getBitmapAsByteArray(Bitmap.CompressFormat.PNG, 100);
                    File file = new File(android.os.Environment.getExternalStorageDirectory()+"/","PiggyLandShare");
                    File f = null;
                    if(!file.exists()) {
                        file.mkdirs();
                        f = new File(MainName[0], image + ".jpg");
                    }else{
                        f = new File(MainName[0], image + ".jpg");
                    }*//*
                    *//*FileOutputStream fos = null;
                    try {
                        fos = new FileOutputStream(f);
                        fos.write(bytes);
                        fos.flush();
                        fos.close();
                        Toast.makeText(Drawing2Activity.this,"File Saved",Toast.LENGTH_SHORT).show();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }*//*
                    finish();
                }

               *//* if(imageReturnedIntent.hasExtra("Title"))
                {

                    Bundle bundle = imageReturnedIntent.getExtras();
                    if(bundle.isEmpty())
                    {

                    }else {
                        String imageName = bundle.getString("Title");
                        String Description = bundle.getString("Description");
                        String FullPath = bundle.getString("FullPath");
                    }
                    *//**//*String[] MainName =FullPathimageName.split("\\.");
                    String image = MainName[0]+"_"+imageName;
                    //byte[] bytes = canvas.getBitmapAsByteArray(Bitmap.CompressFormat.PNG, 100);
                    File file = new File(android.os.Environment.getExternalStorageDirectory()+"/","PiggyLand");
                    File f = null;
                    if(!file.exists()) {
                        file.mkdirs();
                        f = new File(MainName[0], image + ".jpg");
                    }else{
                        f = new File(MainName[0], image + ".jpg");
                    }*//**//*
                    *//**//*FileOutputStream fos = null;
                    try {
                        fos = new FileOutputStream(f);
                        fos.write(bytes);
                        fos.flush();
                        fos.close();
                        Toast.makeText(Drawing2Activity.this,"File Saved",Toast.LENGTH_SHORT).show();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }*//**//*
                    finish();
                }*//*
                break;
        }
    }

*/
}