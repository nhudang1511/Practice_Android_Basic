package com.example.foodapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.foodapp.model.User;

public class   Profile extends AppCompatActivity {

    TextView txt_id, txt_username, txt_email, txt_gender;
    Button btn_logout;
    ImageView imv_Avatar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Mapping();

        if (SharedPrefManager.getInstance(this).isLoggedIn()){
            User user = SharedPrefManager.getInstance(this).getUser();
            txt_id.setText(String.valueOf(user.getId()));
            txt_username.setText(user.getUsername());
            txt_email.setText(user.getEmail());
            txt_gender.setText(user.getGender());
            Glide.with(Profile.this).load(user.getImages()).into(imv_Avatar);

            btn_logout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SharedPrefManager.getInstance(getApplicationContext()).logout();
                }
            });

            imv_Avatar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Profile.this, Upload_Image_Profile.class);
                    startActivity(intent);
                }
            });
        } else {
            Intent intent = new Intent(Profile.this, LoginActivity.class);
            startActivity(intent);
            finish();
        }
    }

    private void Mapping() {
        txt_id = findViewById(R.id.tv_idUser);
        txt_username = findViewById(R.id.tv_userName);
        txt_email = findViewById(R.id.tv_email);
        txt_gender = findViewById(R.id.tv_gender);
        btn_logout = findViewById(R.id.btn_Logout);
        imv_Avatar = findViewById(R.id.img_avatarProfile);
    }
}