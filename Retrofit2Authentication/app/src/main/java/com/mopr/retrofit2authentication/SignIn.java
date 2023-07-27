package com.mopr.retrofit2authentication;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.mopr.retrofit2authentication.models.User;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignIn extends AppCompatActivity {
    EditText editTextName, editTextPassword;
    ProgressBar progressBar;
    ApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.GONE);
        editTextName = findViewById(R.id.usernameEditext);
        editTextPassword = findViewById(R.id.passwordEditext);

        findViewById(R.id.signInBtn).setOnClickListener(v -> {
            userLogin();
        });
        findViewById(R.id.signUpTView).setOnClickListener(v -> {
            startActivity(new Intent(getApplicationContext(), SignUp.class));
            finish();
        });
    }

    private void userLogin() {
        final String username = editTextName.getText().toString();
        final String password = editTextPassword.getText().toString();

        // Validate inputs
        if (TextUtils.isEmpty(username)) {
            editTextName.setError("Please enter your username");
            editTextName.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(password)) {
            editTextPassword.setError("Please enter your password");
            editTextPassword.requestFocus();
            return;
        }

        apiService = new ApiService();
        Call<ResponseBody> call = apiService.login(username, password);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    String responseBodyString = null;
                    try {
                        assert response.body() != null;
                        responseBodyString = response.body().string();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    progressBar.setVisibility(View.VISIBLE);
                    try {
                        JSONObject object = new JSONObject(responseBodyString);
                        if (!object.getBoolean("error")) {
                            Toast.makeText(getApplicationContext(), object.getString("message"), Toast.LENGTH_LONG).show();
                            JSONObject userJson = object.getJSONObject("user");

                            // Create a new user
                            User user = new User(
                                    userJson.getInt("id"),
                                    userJson.getString("username"),
                                    userJson.getString("email"),
                                    userJson.getString("gender"),
                                    userJson.getString("images")
                            );

                            // Storing user in shared preferences
                            SharedPrefManager.getInstance(getApplicationContext()).userLogin(user);
                            finish();
                            startActivity(new Intent(SignIn.this, MainActivity.class));
                        } else {
                            Toast.makeText(getApplicationContext(), object.getString("message"), Toast.LENGTH_LONG).show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Response is unsuccessful", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}