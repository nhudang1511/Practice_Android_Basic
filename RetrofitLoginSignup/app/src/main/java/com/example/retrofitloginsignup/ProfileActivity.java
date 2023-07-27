package com.example.retrofitloginsignup;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

import com.example.retrofitloginsignup.SharedPrefManager;
import com.example.retrofitloginsignup.Model.User;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener {
    TextView id, userName, userEmail, gender;
    Button btnLogout;
    ImageView imageViewpprofile;

    @Override
    protected void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_profile);
        if(SharedPrefManager.getInstance(this).isLoggedIn()){
            id = findViewById((R.id.id));
            userName = findViewById((R.id.username));
            userEmail = findViewById((R.id.email));
            gender = findViewById((R.id.gender));
            imageViewpprofile = findViewById((R.id.imageView2));
            User user = SharedPrefManager.getInstance(this).getUser();
            id.setText(String.valueOf(user.getId()));
            userEmail.setText(user.getEmail());
            userName.setText(user.getName());
            gender.setText(user.getGender());
//            Glide.with(getApplicationContext()).load(user.getImages())
//                    .into(imageViewpprofile);
            btnLogout = findViewById(R.id.btnLogout);
            btnLogout.setOnClickListener(this);
        }
        else {
            Intent intent = new Intent(ProfileActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        }

        imageViewpprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProfileActivity.this, UpdateImage.class);
                startActivity(intent);
                finish();
            }
        });
    }

    public void onClick(View view){
        if(view.equals(btnLogout)){
            SharedPrefManager.getInstance(getApplicationContext()).logout();
        }
    }
}
