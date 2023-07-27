package com.mopr.retrofit2authentication;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUp extends AppCompatActivity {
    EditText usernameEdt, passwordEdt, emailEdt, genderEdt;
    ApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        usernameEdt = findViewById(R.id.usernameEdt);
        passwordEdt = findViewById(R.id.passwordEdt);
        emailEdt = findViewById(R.id.emailEdt);
        genderEdt = findViewById(R.id.genderEdt);

        findViewById(R.id.submitBtn).setOnClickListener(view -> {
            userRegister();
        });

        findViewById(R.id.signInTv).setOnClickListener(view -> {
            startActivity(new Intent(getApplicationContext(), SignIn.class));
            finish();
        });
    }

    private void userRegister() {
        final String username = usernameEdt.getText().toString();
        final String password = passwordEdt.getText().toString();
        final String email = emailEdt.getText().toString();
        final String gender = genderEdt.getText().toString();

        // Validate inputs
        if (TextUtils.isEmpty(username)) {
            usernameEdt.setError("Please enter your username");
            usernameEdt.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(password)) {
            passwordEdt.setError("Please enter your password");
            passwordEdt.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(email)) {
            emailEdt.setError("Please enter your email");
            emailEdt.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(gender)) {
            genderEdt.setError("Please enter your gender");
            genderEdt.requestFocus();
            return;
        }

        apiService = new ApiService();
        Call<ResponseBody> call = apiService.register(username, password, email, gender);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    String responseBodyString;
                    try {
                        assert response.body() != null;
                        responseBodyString = response.body().string();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }

                    try {
                        JSONObject object = new JSONObject(responseBodyString);
                        if (!object.getBoolean("error")) {
                            Toast.makeText(SignUp.this, object.getString("message"), Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(SignUp.this, "Api error: " + object.getString("message"), Toast.LENGTH_SHORT).show();
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    Toast.makeText(SignUp.this, "Failed to get response", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable t) {
                Toast.makeText(SignUp.this, "On failure: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}