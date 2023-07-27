package com.example.a20110235_foodapps;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.a20110235_foodapps.api.APIService;
import com.example.a20110235_foodapps.model.User;
import com.example.a20110235_foodapps.model.UserLogin;
import com.example.a20110235_foodapps.model.UserRegister;
import com.example.a20110235_foodapps.sharedpreference.SharePrefManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Register extends AppCompatActivity {

    EditText editTextUsername,editTextFullname,editTextEmail,editTextPass,editTextGender;
    ImageView imgRegister;
    UserRegister userLogin;
    User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        AnhXa();
        imgRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UserRegister();
            }
        });
    }
    public void AnhXa(){
        editTextUsername = findViewById(R.id.editTextUsername);
        editTextFullname = findViewById(R.id.editTextFullName);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPass = findViewById(R.id.editTextPass);
        editTextGender = findViewById(R.id.editTextGender);
        imgRegister=findViewById(R.id.imgRegister);
    }
    public void UserRegister(){
        final String username = editTextUsername.getText().toString();
        final String fullname=editTextFullname.getText().toString();
        final String email = editTextEmail.getText().toString();
        final String password = editTextPass.getText().toString();
        final String gender = editTextGender.getText().toString();
        if (TextUtils.isEmpty(username)) {
            editTextUsername.setError("Please enter username");
            editTextUsername.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(fullname)) {
            editTextFullname.setError("Please enter full name");
            editTextFullname.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(email)) {
            editTextEmail.setError("Please enter email");
            editTextEmail.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(password)) {
            editTextPass.setError("Please enter password");
            editTextPass.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(gender)) {
            editTextGender.setError("Please enter gender");
            editTextGender.requestFocus();
            return;
        }
        APIService.apiservice.register(username,fullname,email,gender,password).enqueue(new Callback<UserRegister>() {
            @Override
            public void onResponse(Call<UserRegister> call, Response<UserRegister> response) {
                try {
                    if (response.isSuccessful()) {
                        userLogin = response.body();
                        if (!userLogin.isError()){
                            Toast.makeText(Register.this, "Đăng ký thành công", Toast.LENGTH_SHORT).show();
                           /* user = userLogin.getUser();
                            //SharePrefManager.getInstance(getApplicationContext()).userLogin(user);
                            Intent intent = new Intent(Register.this, Login.class);
                            startActivity(intent);*/
                        } else {
                            Toast.makeText(Register.this, "Thất bại", Toast.LENGTH_SHORT).show();
                        }

                    } else {
                        Toast.makeText(Register.this, "Error", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<UserRegister> call, Throwable t) {

            }
        });
    }

}