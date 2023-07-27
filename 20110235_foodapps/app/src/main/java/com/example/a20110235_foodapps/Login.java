package com.example.a20110235_foodapps;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a20110235_foodapps.api.APIService;
import com.example.a20110235_foodapps.model.User;
import com.example.a20110235_foodapps.model.UserLogin;
import com.example.a20110235_foodapps.sharedpreference.SharePrefManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends AppCompatActivity {

    EditText etName,etPassword;
    UserLogin userLogin;
    ImageView imgLogin;
    TextView txtViewRegister;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        AnhXa();
       if(SharePrefManager.getInstance(this).isLoggedIn()){
            finish();
            startActivity(new Intent(this,MainActivity.class));
        }
       imgLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userLogin();
            }
        });
       txtViewRegister.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               userRegister();
           }
       });
    }
    public void AnhXa(){
        etName = findViewById(R.id.editTextemail);
        etPassword = findViewById(R.id.editTextPass);
        imgLogin=findViewById(R.id.imageView4);
        txtViewRegister =findViewById(R.id.textViewRegister);
    }
    public void userLogin(){
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
        APIService.apiservice.login(username,password).enqueue(new Callback<UserLogin>() {
            @Override
            public void onResponse(Call<UserLogin> call, Response<UserLogin> response) {
                try {
                    if (response.isSuccessful()) {
                        userLogin = response.body();
                        if (!userLogin.isError()){
                            Toast.makeText(Login.this, "Thành công", Toast.LENGTH_SHORT).show();
                            user = userLogin.getUser();
                            SharePrefManager.getInstance(getApplicationContext()).userLogin(user);
                            Intent intent = new Intent(Login.this, MainActivity.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(Login.this, "Thông tin đăng nhập sai", Toast.LENGTH_SHORT).show();
                        }

                    } else {
                        Toast.makeText(Login.this, "Error", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<UserLogin> call, Throwable t) {
                Log.d("logg", t.getMessage());
            }
        });
    }
    public void userRegister(){
        Intent intent = new Intent(Login.this, Register.class);
        startActivity(intent);
    }
}