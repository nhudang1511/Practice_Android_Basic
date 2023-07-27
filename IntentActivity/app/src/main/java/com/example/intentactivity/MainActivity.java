package com.example.intentactivity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText editEmail;
    private Button btnSend;

    private static final int MY_REQUEST_CODE = 10;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setTitle("Activity 1");

        editEmail = findViewById(R.id.edt_email);
        btnSend = findViewById(R.id.btn_send);

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextActivity();
            }
        });
    }

    private void nextActivity() {
        String strEmail = editEmail.getText().toString().trim();
        /*AppUlti.mEnail = strEmail;*/
        Intent intent = new Intent(MainActivity.this, IntentActivity.class);
        intent.putExtra("key_email", strEmail);
        /*startActivity(intent);*/
        startActivityForResult(intent, MY_REQUEST_CODE);
    }

    /*@Override
    protected void onResume() {
        super.onResume();
        *//*editEmail.setText(AppUlti.mEnail);*//*
        editEmail.setText(getIntent().getStringExtra("key_email"));
    }*/

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (MY_REQUEST_CODE == requestCode && resultCode == Activity.RESULT_OK){
            editEmail.setText(data.getStringExtra("key_email"));
        }
    }
}