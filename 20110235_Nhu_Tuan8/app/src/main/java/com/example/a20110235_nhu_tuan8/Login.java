package com.example.a20110235_nhu_tuan8;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends AppCompatActivity {
    EditText etName,etPassword;
    User user;
    APIService apiService;
    UserLogin userLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        if(SharePrefManager.getInstance(this).isLoggedIn()){
            finish();
            //startActivity(new Intent(this,MainActivity.class));
            startActivity(new Intent(this,MainActivity.class));
        }
        etName = findViewById(R.id.editTextemail);
        etPassword = findViewById(R.id.editTextPass);
        findViewById(R.id.imageView4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userLogin();
            }
        });
    }
    private void userLogin() {
        final String username = etName.getText().toString();
        final String password = etPassword.getText().toString();
        if (TextUtils.isEmpty(username)) {
            etName.setError("Please enter username");
            etName.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(password)) {
            etPassword.setError("Please enter password");
            etPassword.requestFocus();
            return;
        }
        Log.d("log", "userLogin: " + constants.URL_REGISTRATION);
        apiService = RetrofitClient.getInstance().getRetrofit(constants.URL_REGISTRATION).create(APIService.class);
        apiService.login(username, password).enqueue(new Callback<UserLogin>() {
            @Override
            public void onResponse(Call<UserLogin> call, Response<UserLogin> response) {
                userLogin = response.body();
                if (!userLogin.isError()){
                    Toast.makeText(getApplicationContext(), userLogin.getMessage(), Toast.LENGTH_SHORT).show();
                    user = userLogin.getUser();
                    SharePrefManager.getInstance(getApplicationContext()).userLogin(user);
                    Intent intent = new Intent(Login.this, MainActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), userLogin.getMessage(), Toast.LENGTH_SHORT).show();
                }
              /*  try {
                    if (response.isSuccessful()) {
                        Log.i("logg", "res: " + response.raw());
                        Toast.makeText(getApplicationContext(), response.message(), Toast.LENGTH_SHORT).show();
                        UserLogin userLogin = response.body();
                        assert userLogin != null;
                        User user = new User(
                                userLogin.getUser().getId(),
                                userLogin.getUser().getUsername(),
                                userLogin.getUser().getFname(),
                                userLogin.getUser().getEmail(),
                                userLogin.getUser().getGender(),
                                userLogin.getUser().getImages()

                        );
                        SharePrefManager.getInstance(getApplicationContext()).userLogin(user);
                        finish();

                    } else {
                        Toast.makeText(getApplicationContext(), response.message(), Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }*/
            }

            @Override
            public void onFailure(Call<UserLogin> call, Throwable t) {
                Log.d("logg", t.getMessage());
            }
        });
    }
}