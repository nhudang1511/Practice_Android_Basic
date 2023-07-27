package com.example.a20110235_foodapps;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.a20110235_foodapps.model.User;
import com.example.a20110235_foodapps.sharedpreference.SharePrefManager;

public class Profile extends AppCompatActivity {

    TextView tv_id,tv_user,tv_name,tv_email,tv_gender;
    ImageView imageViewAvatar;
    Button btnLogout;
    User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        AnhXa();
        if(SharePrefManager.getInstance(this).isLoggedIn()){
            user = SharePrefManager.getInstance(this).getUser();
            tv_id.setText(String.valueOf(user.getId()));
            tv_user.setText(user.getUsername());
            tv_name.setText(user.getFullname());
            tv_email.setText(user.getEmail());
            tv_gender.setText(user.getGender());
            Glide.with(this).load(user.getImages()).into(imageViewAvatar);
            btnLogout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(view.equals(btnLogout)){
                        SharePrefManager.getInstance(getApplicationContext()).logout();
                    }
                }
            });
            imageViewAvatar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(Profile.this, UploadImage.class);
                    startActivity(intent);
                }
            });
        }
    }
    public void AnhXa(){
        tv_id= findViewById(R.id.tv_Id);
        tv_user=findViewById(R.id.tv_username);
        tv_name=findViewById(R.id.tv_name);
        tv_email=findViewById(R.id.tv_email);
        tv_gender=findViewById(R.id.tv_gender);
        imageViewAvatar= findViewById(R.id.img_update);
        btnLogout=findViewById(R.id.btnLogout);
    }
}