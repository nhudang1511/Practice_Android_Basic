package com.example.foodapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.foodapp.api.ApiService;
import com.example.foodapp.api.RetrofitClient;
import com.example.foodapp.model.User;
import com.example.foodapp.model.UserLogin;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class LoginActivity extends AppCompatActivity {

    ApiService apiService;
    User user;
    UserLogin userLogin;

    EditText edUsername, edPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        if (SharedPrefManager.getInstance(this).isLoggedIn()){
            finish();
            Log.d("message", "Success");
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
        }

        edUsername = findViewById(R.id.etUserName);
        edPassword = findViewById(R.id.etUserPassword);

        findViewById(R.id.btnLogin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserLogin();
            }
        });

        //Đăng ký
        findViewById(R.id.txtRegister).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SignUpActivity.class);
                startActivity(intent);
            }
        });
    }

    public void UserLogin(){
        String username = edUsername.getText().toString();
        String password = edPassword.getText().toString();

        if (TextUtils.isEmpty(username)){
            edUsername.setError("Username không được để trống");
            edUsername.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(password)){
            edPassword.setError("Password không được để trống");
            edPassword.requestFocus();
            return;
        }

        apiService = RetrofitClient.getRetrofit2().create(ApiService.class);

        Call<UserLogin> call = apiService.login(username, password);
        call.enqueue(new Callback<UserLogin>() {
            @Override
            public void onResponse(Call<UserLogin> call, Response<UserLogin> response) {
                userLogin = response.body();
                if (!userLogin.getError()){
                    Toast.makeText(getApplicationContext(), userLogin.getMessage(), Toast.LENGTH_SHORT).show();
                    user = userLogin.getUser();
                    SharedPrefManager.getInstance(getApplicationContext()).userLogin(user);
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), userLogin.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<UserLogin> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "Error", Toast.LENGTH_SHORT).show();
                Log.d("Error API:",t.getMessage());
            }
        });
    }
}