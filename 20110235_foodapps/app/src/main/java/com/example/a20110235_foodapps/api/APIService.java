package com.example.a20110235_foodapps.api;


import com.example.a20110235_foodapps.model.Category;
import com.example.a20110235_foodapps.model.DetailProductMessage;
import com.example.a20110235_foodapps.model.Product;
import com.example.a20110235_foodapps.model.UserLogin;
import com.example.a20110235_foodapps.model.UserRegister;
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

public interface APIService {

    public static  final String BASE_URL ="http://app.iotstar.vn/appfoods/";
    Gson gson = new GsonBuilder().setDateFormat("yyyy MM dd HH:mm:ss").create();

    APIService apiservice =new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(APIService.class);

    @FormUrlEncoded
    @POST("registrationapi.php?apicall=login")
    Call<UserLogin> login(@Field("username") String username, @Field("password") String password);

    @FormUrlEncoded
    @POST("registrationapi.php?apicall=signup")
    Call<UserRegister> register(@Field("username") String username, @Field("fname") String fname,
                                @Field("email") String email,
                                @Field("gender") String gender,
                                @Field("password") String password);

    @Multipart
    @POST("updateimages.php")
    Call<UserLogin> upload1(@Part("id") RequestBody id,
                              @Part MultipartBody.Part avatar);

    @FormUrlEncoded
    @POST("getcategory.php")
    Call<List<Product>> getProductofCate(@Field("idcategory") int idcategory);

    @FormUrlEncoded
    @POST("newmealdetail.php")
    Call<DetailProductMessage> getDetailProduct(@Field("id") int id);


    @GET("categories.php")
    Call<List<Category>> getCategories();

    @GET("lastproduct.php")
    Call<List<Product>> getLastProduct();


}
