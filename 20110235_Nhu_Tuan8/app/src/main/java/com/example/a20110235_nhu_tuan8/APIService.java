package com.example.a20110235_nhu_tuan8;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface APIService {
    @GET("categories.php")
    Call<List<Category>> getCategoryAll();

    @GET("category.php")
    Call<Category> getCategory();

    @GET("lastproduct.php")
    Call<List<Product>> getProductAll();

    @FormUrlEncoded
    @POST("registrationapi.php?apicall=login")
    Call<UserLogin> login(@Field("username") String username, @Field("password") String password);
    @Multipart
    @POST("updateimages.php")
    Call<UserLogin> updateImages(@Part("id") RequestBody id, @Part MultipartBody.Part images);

}
