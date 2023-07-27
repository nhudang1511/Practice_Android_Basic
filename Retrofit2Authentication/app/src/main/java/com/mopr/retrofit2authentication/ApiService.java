package com.mopr.retrofit2authentication;

import com.mopr.retrofit2authentication.interfaces.IApiService;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiService {
    private static final String BASE_URL = "http://app.iotstar.vn/shoppingapp/";
    private final IApiService apiServiceInterface;

    public ApiService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiServiceInterface = retrofit.create(IApiService.class);
    }

    public Call<ResponseBody> login(String username, String password) {
        return apiServiceInterface.login(username, password);
    }

    public Call<ResponseBody> register(String username, String password, String email, String gender) {
        return apiServiceInterface.register(username, password, email, gender);
    }
}
