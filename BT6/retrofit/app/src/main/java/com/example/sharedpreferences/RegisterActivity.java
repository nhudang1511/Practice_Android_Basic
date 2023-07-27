package com.example.sharedpreferences;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sharedpreferences.api.UserApi;
import com.example.sharedpreferences.domain.UserModel;
import com.example.sharedpreferences.retrofit.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    //
    private final String TAG = RegisterActivity.class.getName();
    EditText editTextUsername, editTextPassword, editTextEmail, editTextGender, editTextImage;
    Button registerButton, goToLoginButton;

    UserApi userApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initComponents();
        handleRegisterUser();
        handleGotoLoginButton();
    }

    private void handleRegisterUser() {
        // Listen event
        registerButton.setOnClickListener(view -> {
            // Get data
            String username = editTextUsername.getText().toString();
            String email = editTextEmail.getText().toString();
            String password = editTextPassword.getText().toString();
            String gender = editTextGender.getText().toString();

            // Create user instance
            UserModel userModel = new UserModel(username, password, email, gender);

            // Call API
            userApi.registerUser(userModel).enqueue(new Callback<UserModel>() {
                @Override
                public void onResponse(Call<UserModel> call, Response<UserModel> response) {
                    Toast.makeText(RegisterActivity.this, "Success", Toast.LENGTH_SHORT).show();
                    goToLoginForm();
                }

                @Override
                public void onFailure(Call<UserModel> call, Throwable t) {
                    Toast.makeText(RegisterActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                    Log.d(TAG, t.getMessage());
                }
            });
        });
    }

    private void handleGotoLoginButton() {
       goToLoginButton.setOnClickListener(view -> {
            goToLoginForm();
       });
    }

    private  void goToLoginForm() {
        Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void initComponents() {
        editTextUsername = findViewById(R.id.editTextUsername);
        editTextPassword = findViewById(R.id.editTextPassword);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextGender = findViewById(R.id.editTextGender);
        editTextImage = findViewById(R.id.editTextImage);
        registerButton = findViewById(R.id.registerButton);
        goToLoginButton = findViewById(R.id.loginButton);
        userApi = RetrofitClient.getRetrofit().create(UserApi.class);
    }
}