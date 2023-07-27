package com.example.sharedpreferences;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sharedpreferences.api.UserApi;
import com.example.sharedpreferences.domain.UserModel;
import com.example.sharedpreferences.retrofit.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    EditText editTextUsername, editTextPassword;
    CheckBox checkBoxRemember;
    Button loginButton, registerButton; // buttonLogin
    SharedPreferences sharedPreferences;

    UserApi userApi;

    private final String TAG = MainActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Thông tin sẽ được luu trữ vào bộ nhớ của ứng dụng
        sharedPreferences = getSharedPreferences("dataLogin", MODE_PRIVATE);
        // initialize Components
        initComponents();

        // Auto fill info;
        autoFillInfo();

        // handle login button
        handleLoginButton();

        // Handle register button
        goToRegisterForm();
    }

    private void autoFillInfo() {
        editTextUsername.setText(sharedPreferences.getString("username",""));
        editTextPassword.setText(sharedPreferences.getString("password", ""));
        checkBoxRemember.setChecked(sharedPreferences.getBoolean("checked", false));
    }

    private void handleLoginButton() {

        loginButton.setOnClickListener(v -> {
            String username = editTextUsername.getText().toString().trim();
            String password = editTextPassword.getText().toString().trim();

            if(!username.isEmpty() & !password.isEmpty()) {
                UserModel userModel = new UserModel(username, password);
                userApi.loginUser(userModel).enqueue(new Callback<UserModel>() {
                    @Override
                    public void onResponse(Call<UserModel> call, Response<UserModel> response) {
                        Toast.makeText(MainActivity.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                        goToHomeActivity();
                    }

                    @Override
                    public void onFailure(Call<UserModel> call, Throwable t) {
                        Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                        Log.d(TAG, t.getMessage());
                    }
                });
//                    Nếu có checked thì mới lưu thông tin vào
                SharedPreferences.Editor editor = sharedPreferences.edit();

                if(checkBoxRemember.isChecked()) {
                    editor.putString("username", username);
                    editor.putString("password", password);
                    editor.putBoolean("checked", checkBoxRemember.isChecked());
                    editor.apply();
                } else {
                    editor.remove("username");
                    editor.remove("password");
                    editor.remove("checked");
                    editor.apply();
                }
            } else {
                Toast.makeText(MainActivity.this, "Đăng nhập thất bại", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void goToHomeActivity() {
        Intent intent = new Intent(MainActivity.this, HomeActivity.class);
        startActivity(intent);
        finish();
    }

    private  void goToRegisterForm() {
        registerButton.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
            startActivity(intent);
            finish();
        });
    }

    public void initComponents() {
        editTextUsername = findViewById(R.id.editTextUsername);
        editTextPassword =  findViewById(R.id.editTextPassword);
        checkBoxRemember = findViewById(R.id.checkBoxRemember);
        loginButton =  findViewById(R.id.buttonControl);
        registerButton = findViewById(R.id.registerButton);
        userApi = RetrofitClient.getRetrofit().create(UserApi.class);
    }
}