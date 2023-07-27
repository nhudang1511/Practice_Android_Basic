package com.example.retrofitloginsignup;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;

import com.example.retrofitloginsignup.API.APIService;
import com.example.retrofitloginsignup.API.RetrofitClient;
import com.example.retrofitloginsignup.Model.User;
import com.example.retrofitloginsignup.Model.UserLogin;

import org.json.JSONObject;

import java.net.NoRouteToHostException;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LoginActivity extends AppCompatActivity {

    EditText etName, etPassword;
    APIService apiService;
    User user;

    UserLogin userLoginParam;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        if (SharedPrefManager.getInstance(this).isLoggedIn()){
            finish();
            Log.d("message","Success");
            startActivity(new Intent(this, ProfileActivity.class));
        }

        etName = findViewById(R.id.etUserName);
        etPassword = findViewById(R.id.etUserPassword);
        findViewById(R.id.btnLogin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userLogin();
            }
        });
        findViewById(R.id.txtRegister).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(getApplicationContext(), SignUpActivity.class));
            }
        });
    }

    private void userLogin(){
        final String username = etName.getText().toString();
        final String password = etPassword.getText().toString();
        if(TextUtils.isEmpty(username)){
            etName.setError("Please enter your username");
            etName.requestFocus();
            return;
        }
        if(TextUtils.isEmpty(password)){
            etPassword.setError("Please enter your password");
            etPassword.requestFocus();
            return;
        }

        apiService = RetrofitClient.getAppfood().create(APIService.class);

        Call<UserLogin> call = apiService.getUser(username,password);
        call.enqueue(new Callback<UserLogin>() {
            @Override
            public void onResponse(Call<UserLogin> call, Response<UserLogin> response) {
                userLoginParam = response.body();
                    if(!userLoginParam.getError()) {
                        Toast.makeText(getApplicationContext(), userLoginParam.getMessage(), Toast.LENGTH_SHORT).show();
                        user = userLoginParam.getUser();
                        SharedPrefManager.getInstance(getApplicationContext()).userLogin(user);
                        Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                        startActivity(intent);
                    }
                    else {
                        Toast.makeText(getApplicationContext(), userLoginParam.getMessage(), Toast.LENGTH_SHORT).show();
                    }
            }

            @Override
            public void onFailure(Call<UserLogin> call, Throwable t) {
                Toast.makeText(LoginActivity.this,"Error", Toast.LENGTH_SHORT).show();
                Log.d("Error API:",t.getMessage());
            }
        });
    }



}
