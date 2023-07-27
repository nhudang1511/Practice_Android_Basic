package com.mopr.retrofit2authentication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.mopr.retrofit2authentication.models.User;

public class Profile extends AppCompatActivity {
    TextView idTv, usernameTv, emailTv, genderTv;
    Button logoutBtn;
    ImageView avatarImv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        if (SharedPrefManager.getInstance(this).isLoggedIn()) {
            idTv = findViewById(R.id.idTv);
            usernameTv = findViewById(R.id.usernameTv);
//            emailTv = findViewById(R.id.emailTv);
//            genderTv = findViewById(R.id.genderTv);
            avatarImv = findViewById(R.id.avatarImv);

            User user = SharedPrefManager.getInstance(this).getUser();
            idTv.setText(String.valueOf(user.getId()));
            usernameTv.setText(user.getName());
            emailTv.setText(user.getEmail());
            genderTv.setText(user.getGender());

            Glide.with(getApplicationContext()).load(user.getImages()).into(avatarImv);
            logoutBtn.setOnClickListener(view -> {
                if (view.equals(logoutBtn)) {
                    SharedPrefManager.getInstance(getApplicationContext()).logout();
                }
            });
        }
        else {
            startActivity(new Intent(Profile.this, SignIn.class));
            finish();
        }
    }
}