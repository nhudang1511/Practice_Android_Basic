package com.example.retrofitloginsignup;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


import com.example.retrofitloginsignup.API.APIService;
import com.example.retrofitloginsignup.API.RetrofitClient;
import com.example.retrofitloginsignup.Model.User;
import com.example.retrofitloginsignup.Model.UserLogin;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpActivity extends AppCompatActivity {
    private TextView txtUserName;
    private TextView txtEmail;
    private TextView txtPassword;

    private RadioButton btnRadio;

    private RadioGroup grRadio;

    APIService apiService;
    User user;

    UserLogin userLoginParam;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        txtUserName = findViewById(R.id.editTxtName);
        txtEmail = findViewById(R.id.editTxtEmail);
        txtPassword = findViewById(R.id.editTxtPass);

        grRadio = (RadioGroup) findViewById(R.id.rdGroup);

        grRadio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int check) {
                btnRadio = (RadioButton) findViewById(check);
            }
        });


        findViewById(R.id.btnConfirm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
            Signup();
            }
        });
    }

    private void Signup(){
        final String username = txtUserName.getText().toString();
        final String email = txtEmail.getText().toString();
        final String password = txtPassword.getText().toString();

        if(TextUtils.isEmpty(username)){
            txtUserName.setError("Please enter your username");
            txtUserName.requestFocus();
            return;
        }
        if(TextUtils.isEmpty(email)){
            txtEmail.setError("Please enter your password");
            txtEmail.requestFocus();
            return;
        }
        if(TextUtils.isEmpty(password)){
            txtPassword.setError("Please enter your username");
            txtPassword.requestFocus();
            return;
        }

        if(btnRadio==null){
            Toast.makeText(getApplicationContext(),"Vui long chon gioi tinh",Toast.LENGTH_SHORT).show();
            return;
        }

        final String gender = (String)  btnRadio.getText();

        apiService = RetrofitClient.getRetrofit().create(APIService.class);

        Call<UserLogin> call = apiService.createUser(username,password,email,gender);
        call.enqueue(new Callback<UserLogin>() {
            @Override
            public void onResponse(Call<UserLogin> call, Response<UserLogin> response) {
                userLoginParam = response.body();
                if(!userLoginParam.getError()) {
                    Toast.makeText(getApplicationContext(), userLoginParam.getMessage(), Toast.LENGTH_SHORT).show();
                    user = userLoginParam.getUser();
                    SharedPrefManager.getInstance(getApplicationContext()).userSignUp(user);
                    Intent intent = new Intent(SignUpActivity.this, HomeActivity.class);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(getApplicationContext(), userLoginParam.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<UserLogin> call, Throwable t) {
                Toast.makeText(SignUpActivity.this,"Error", Toast.LENGTH_SHORT).show();
                Log.d("Error API:",t.getMessage());
            }
        });


    }
}