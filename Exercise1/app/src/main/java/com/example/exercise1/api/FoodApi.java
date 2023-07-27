package com.example.exercise1.api;

import com.example.exercise1.model.Category;
import com.example.exercise1.model.Const;
import com.example.exercise1.model.DetailMessage;
import com.example.exercise1.model.Product;
import com.example.exercise1.model.StateMessage;
import com.example.exercise1.model.UploadMessage;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface FoodApi {
    Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd HH:mm:ss")
            .create();

    FoodApi apiService = new Retrofit.Builder()
            .baseUrl("http://app.iotstar.vn/appfoods/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(FoodApi.class);

    @FormUrlEncoded
    @POST("registrationapi.php?apicall=login")
    Call<StateMessage> getAccount(@Field("username") String username, @Field("password") String password);

    @GET("categories.php")
    Call<List<Category>> getCategories();

    @GET("lastproduct.php")
    Call<List<Product>> getLastestProduct();

    @FormUrlEncoded
    @POST("newmealdetail.php")
    Call<DetailMessage> getDetailProduct(@Field("id") String id);

    @FormUrlEncoded
    @POST("getcategory.php")
    Call<List<Product>> getListProducts(@Field("idcategory") int id);

    @Multipart
    @POST("updateimages.php")
    Call<UploadMessage> uploadImage(@Part(Const.MY_ID) RequestBody id, @Part MultipartBody.Part avt);
}
