package com.example.exercise1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.exercise1.api.FoodApi;
import com.example.exercise1.model.StateMessage;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class WelcomeActivity extends AppCompatActivity {
    EditText editTextEmail;
    EditText editTextPassword;
    ImageButton btnLogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        initView();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                testLogin();
            }
        });

    }

    private void testLogin() {
        final String username = editTextEmail.getText().toString();
        final String password = editTextPassword.getText().toString();

        if (TextUtils.isEmpty(username)) {
            editTextEmail.setError("Please enter your username");
            editTextEmail.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            editTextPassword.setError("Please enter your password");
            editTextPassword.requestFocus();
            return;
        }


        FoodApi.apiService.getAccount(username, password).enqueue(new Callback<StateMessage>() {
            @Override
            public void onResponse(Call<StateMessage> call, Response<StateMessage> response) {
                Toast.makeText(WelcomeActivity.this, "Call User api success", Toast.LENGTH_SHORT).show();
                StateMessage stateMessage = response.body();

                if(stateMessage != null){
                    Log.e("User",stateMessage.getUser().toString());
                    Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("userObject", stateMessage.getUser());
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(WelcomeActivity.this, "Wrong Account", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<StateMessage> call, Throwable t) {
                Toast.makeText(WelcomeActivity.this, "Call api error", Toast.LENGTH_SHORT).show();
            }
        });



    }

    private void initView(){
        editTextEmail = findViewById(R.id.edittext_email);
        editTextPassword =  findViewById(R.id.edittext_password);
        btnLogin = findViewById(R.id.imgBtn_Login);
    }

}