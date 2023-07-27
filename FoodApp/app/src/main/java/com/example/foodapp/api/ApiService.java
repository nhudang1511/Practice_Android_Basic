package com.example.foodapp.api;


import com.example.foodapp.model.Category;
import com.example.foodapp.model.MessageProduct;
import com.example.foodapp.model.Product;
import com.example.foodapp.model.UserLogin;

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

public interface ApiService {
    @FormUrlEncoded
    @POST("registrationapi.php?apicall=login")
    Call<UserLogin> login(@Field("username") String username,
                          @Field("password") String password);

    @FormUrlEncoded
    @POST("registrationapi.php?apicall=signup")
    Call<UserLogin> signup(@Field("username") String username,
                           @Field("password") String password,
                           @Field("email") String email,
                           @Field("gender") String gender);

    @GET("categories.php")
    Call<List<Category>> getCategories();

    @GET("lastproduct.php")
    Call<List<Product>> getLastProduct();

    @FormUrlEncoded
    @POST("getcategory.php")
    Call<List<Product>> getProductofCate(@Field("idcategory") int idcategory);

    @FormUrlEncoded
    @POST("newmealdetail.php")
    Call<MessageProduct> getDetailProduct(@Field("id") String id);

    @Multipart
    @POST("updateimages.php")
    Call<UserLogin> updateImages(@Part("id") RequestBody id, @Part MultipartBody.Part images);
}
