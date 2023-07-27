package com.example.retrofitdemo.api;

import com.example.retrofitdemo.model.CategoryModel;
import com.example.retrofitdemo.model.MessageModel;
import com.example.retrofitdemo.model.ProductModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface APIService {
    @GET("categories.php")
    Call<List<CategoryModel>> getCategoryAll();

    @GET("lastproduct.php")
    Call<List<ProductModel>> getProductAll();

    @POST("registrationapi.php?apicall=login")
    @FormUrlEncoded
    Call<MessageModel> login(@Field("username") String username,
                             @Field("password") String password);
}
