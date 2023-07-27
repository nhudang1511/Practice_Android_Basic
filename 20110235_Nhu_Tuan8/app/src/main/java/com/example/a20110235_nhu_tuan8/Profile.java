package com.example.a20110235_nhu_tuan8;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class Profile extends AppCompatActivity {

    TextView id, username, userEmail, gender,name;
    Button btnLogout,btnUpload;
    ImageView imageViewProfile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Log.d("logg", "onCreate:"+ SharePrefManager.getInstance(this).isLoggedIn());
        if(SharePrefManager.getInstance(this).isLoggedIn()){


            username = findViewById(R.id.tv_username);
            id =findViewById(R.id.tv_Id);
            userEmail = findViewById(R.id.tv_email);
            name=findViewById(R.id.tv_name);
            gender=findViewById(R.id.tv_gender);
            btnLogout = findViewById(R.id.btnLogout);
            imageViewProfile = findViewById(R.id.imgUserImage2);
            btnUpload=findViewById(R.id.btnUpload);
            User user = SharePrefManager.getInstance(this).getUser();

            username.setText(user.getUsername());
            userEmail.setText(user.getEmail());
            gender.setText(user.getGender());
            name.setText(user.getFname());
            id.setText(String.valueOf(user.getId()));
            Glide.with(getApplicationContext()).load(user.getImages()).into(imageViewProfile);
            btnLogout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(view.equals(btnLogout)){
                        SharePrefManager.getInstance(getApplicationContext()).logout();
                    }
                }
            });
            btnUpload.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(Profile.this, Upload.class));
                }
            });


        }
        else{
           Intent intent = new Intent(Profile.this,Login.class);
            startActivity(intent);
            finish();
        }
    }
}