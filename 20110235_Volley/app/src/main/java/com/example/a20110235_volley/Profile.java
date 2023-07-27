package com.example.a20110235_volley;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class Profile extends AppCompatActivity {

    TextView id,username,userEmail,gender;
    Button btnLogout;
    ImageView imageViewProfile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        AnhXa();
        if(SharedPrefManager.getInstance(this).isLoggedIn()){
            User user = SharedPrefManager.getInstance(this).getUser();
            id.setText(String.valueOf(user.getId()));
            userEmail.setText(user.getEmail());
            gender.setText(user.getGender());
            username.setText(user.getName());
            Glide.with(getApplicationContext()).load(user.getImages()).into(imageViewProfile);
            btnLogout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(view.equals(btnLogout)){
                        SharedPrefManager.getInstance(getApplicationContext()).logout();
                    }
                }
            });

        }
        else {
            Intent intent = new Intent(Profile.this, Login.class);
            startActivity(intent);
            finish();
        }
    }
    public void AnhXa(){
        id=findViewById(R.id.tv_Id);
        username=findViewById(R.id.tv_name);
        userEmail=findViewById(R.id.tv_email);
        gender=findViewById(R.id.tv_gender);
        btnLogout=findViewById(R.id.btnLogout);
        imageViewProfile=findViewById(R.id.img_update);
    }
}