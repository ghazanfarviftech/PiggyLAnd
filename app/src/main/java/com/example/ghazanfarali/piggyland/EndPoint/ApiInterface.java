package com.example.ghazanfarali.piggyland.EndPoint;

/**
 * Created by ghazanfarali on 23/01/2017.
 */


import com.example.ghazanfarali.piggyland.EndPoint.DataResponse.LoginResponse;
import com.example.ghazanfarali.piggyland.EndPoint.DataResponse.SaveToGalleryResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;


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
    Call<String> editProfile(@Field("username") String username, @Field("email") String email, @Field("image") String image, @Field("imgName") String imgName, @Field("contact") String contact, @Field("mac") String mac);


}
