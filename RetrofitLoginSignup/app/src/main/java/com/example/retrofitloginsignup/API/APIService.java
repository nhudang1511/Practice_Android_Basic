package com.example.retrofitloginsignup.API;

import com.example.retrofitloginsignup.Model.Category;
import com.example.retrofitloginsignup.Model.Product;
import com.example.retrofitloginsignup.Model.User;
import com.example.retrofitloginsignup.Model.UserLogin;

import java.io.File;
import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface APIService {


    @FormUrlEncoded
    @POST("registrationapi.php?apicall=login")
    Call<UserLogin> getUser(@Field("username") String username, @Field("password") String password);


    @FormUrlEncoded
    @POST("registrationapi.php?apicall=signup")
    Call<UserLogin> createUser(@Field("username") String username, @Field("password") String password,@Field("email") String email,@Field("gender") String gender);

    @Multipart
    @POST("updateimages.php")
    Call<UserLogin> updateImages(@Part("id") RequestBody id, @Part MultipartBody.Part images);

    @GET("categories.php")
    Call<List<Category>> getCategories();

    @GET("lastproduct.php")
    Call<List<Product>> getAllProduct();

    @FormUrlEncoded
    @POST("getcategory.php")
    Call<List<Product>> getCategory(@Field("idcategory") int id);


}
