package com.example.exercise1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.exercise1.model.User;

public class ProfileActivity extends AppCompatActivity {
    TextView id, userName, userEmail, gender;
    Button btnBack;
    ImageView imageViewprofile;
    private User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        initView();
        Bundle bundleRecieve = getIntent().getExtras();
        if(bundleRecieve != null){
            user = (User) bundleRecieve.get("userObject");
            if(user != null){
                id.setText(String.valueOf(user.getId()));
                userName.setText(user.getName());
                userEmail.setText(user.getEmail());
                gender.setText(user.getGender());
                Glide.with(getApplicationContext()).load(user.getImages()).into(imageViewprofile);
            }
        }

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        imageViewprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProfileActivity.this, UpdateImageActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("userObject", user);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

    private void initView() {
        id = findViewById(R.id.textViewId);
        userName = findViewById(R.id.textViewUsername);
        userEmail = findViewById(R.id.textViewEmail);
        gender = findViewById(R.id.textViewGender);
        btnBack = findViewById(R.id.btnBack);
        imageViewprofile = findViewById(R.id.imageViewProfile);
    }


}