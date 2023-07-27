package com.example.foodapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.foodapp.api.ApiService;
import com.example.foodapp.api.RetrofitClient;
import com.example.foodapp.model.User;
import com.example.foodapp.model.UserLogin;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpActivity extends AppCompatActivity {

    private  EditText edituserName, editPassword, editemail;

    private  RadioGroup genderGroup;

    private RadioButton btnGender;
    private ApiService apiService;

    UserLogin userLogin;

    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        Mapping();

        genderGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                btnGender = (RadioButton) findViewById(checkedId);
            }
        });

        findViewById(R.id.btnSignup).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SignUp();
            }
        });
    }

    private void Mapping() {
        edituserName = (EditText) findViewById(R.id.editName);
        editPassword = (EditText) findViewById(R.id.editPassword);
        editemail = (EditText) findViewById(R.id.editEmail);
        genderGroup = (RadioGroup) findViewById(R.id.rdGroup);
    }

    private void SignUp() {
        String username = edituserName.getText().toString();
        String password = editPassword.getText().toString();
        String email = editemail.getText().toString();
        String gender = btnGender.getText().toString();

        if (TextUtils.isEmpty(username)){
            edituserName.setError("Vui lòng nhập Username");
            edituserName.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(password)){
            editPassword.setError("Vui lòng nhập Password");
            editPassword.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(email)){
            editemail.setError("Vui lòng nhập Email");
            editemail.requestFocus();
            return;
        }

        if (btnGender == null){
            Toast.makeText(getApplicationContext(), "Vui lòng chọn giới tính!", Toast.LENGTH_SHORT).show();
            return;
        }

        apiService = RetrofitClient.getRetrofit2().create(ApiService.class);
        apiService.signup(username, password, email, gender).enqueue(new Callback<UserLogin>() {
            @Override
            public void onResponse(Call<UserLogin> call, Response<UserLogin> response) {
                userLogin = response.body();
                if (!userLogin.getError()){
                    Toast.makeText(getApplicationContext(), userLogin.getMessage(), Toast.LENGTH_SHORT).show();
                    user = userLogin.getUser();
                    SharedPrefManager.getInstance(getApplicationContext()).userSignup(user);
                    Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), userLogin.getMessage(), Toast.LENGTH_SHORT).show();
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