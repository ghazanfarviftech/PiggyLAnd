package com.example.ghazanfarali.piggyland.EndPoint;

/**
 * Created by ghazanfarali on 23/01/2017.
 */


import com.example.ghazanfarali.piggyland.EndPoint.DataResponse.EditProfileResponse;
import com.example.ghazanfarali.piggyland.EndPoint.DataResponse.ForgotPsswordResponse;
import com.example.ghazanfarali.piggyland.EndPoint.DataResponse.GetUserProfile.GetUserProfileResponse;
import com.example.ghazanfarali.piggyland.EndPoint.DataResponse.GetUsers.GetUsersResponse;
import com.example.ghazanfarali.piggyland.EndPoint.DataResponse.LoginResponse;
import com.example.ghazanfarali.piggyland.EndPoint.DataResponse.SaveToGalleryResponse;
import com.example.ghazanfarali.piggyland.Views.Activities.Drawing.Bean.MyPojo;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface ApiInterface {
    @FormUrlEncoded
    @POST("index.php?menu=login")
    Call<LoginResponse> getLogin(@Field("email") String username, @Field("password") String password, @Field("mac") String mac);

    /*@GET("movie/{id}")
    Call<MoviesResponse> getMovieDetails(@Path("id") int id, @Query("api_key") String apiKey);*/
    @FormUrlEncoded
    @POST("index.php?menu=signUp")
    Call<LoginResponse> getSignUp(@Field("email") String username, @Field("password") String password, @Field("mac") String mac);
    //Call<LoginResponse> getSignUp(@Field("username") String username, @Field("password") String password, @Field("email") String email, @Field("contact") String contact, @Field("mac") String mac);

    @FormUrlEncoded
    @POST("index.php?menu=saveGallery")
    Call<SaveToGalleryResponse> saveImageToServer(@Field("image") String image, @Field("imgName") String imgName, @Field("title") String title, @Field("description") String description, @Field("user") String user);

//https://drawnow.000webhostapp.com/api/index.php?menu=editProfile
    @FormUrlEncoded
    @POST("index.php?menu=editProfile")
    Call<EditProfileResponse> editProfile(@Field("username") String username, @Field("email") String email, @Field("image") String image, @Field("imgName") String imgName, @Field("contact") String contact, @Field("mac") String mac);

    @FormUrlEncoded
    @POST("index.php?menu=forgetPassword")
    Call<ForgotPsswordResponse> forgetPassword(@Field("email") String username);


    @GET("index.php?menu=getProfile&email/{email}")
    Call<GetUserProfileResponse> GetProfile(@Path("email") String email);


    @GET("index.php?menu=getProfile")
    Call<GetUserProfileResponse> responseprofile(@Query("email") String pid);

    @GET("index.php?menu=getUsers")
    Call<GetUsersResponse> getUsers();


    @GET("index.php?menu=getPost")
    Call<MyPojo> getPost();


    @FormUrlEncoded
    @POST("index.php?menu=saveGallery")
    Call<SaveToGalleryResponse> saveImageToServersaveGallery(@Field("image") String image, @Field("imgName") String imgName, @Field("title") String title, @Field("description") String description, @Field("user") String user);

    @FormUrlEncoded
    @POST("index.php?menu=shareGallery")
    Call<SaveToGalleryResponse> saveImageToServershareGallery(@Field("image") String image, @Field("imgName") String imgName, @Field("title") String title, @Field("description") String description, @Field("user") String user);
}
