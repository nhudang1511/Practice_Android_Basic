package com.example.sharedpreferences.api;

import com.example.sharedpreferences.domain.UserModel;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserApi {

    // Register user
    @POST("/registrationapi.php?apicall=signup")
    Call<UserModel> registerUser(@Body UserModel user);

    // Login User
    @POST("/registrationapi.php?apicall=login")
    Call<UserModel> loginUser(@Body UserModel user);

}
