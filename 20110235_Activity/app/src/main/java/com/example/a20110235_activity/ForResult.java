package com.example.a20110235_activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ForResult extends AppCompatActivity {

    TextView txtNhan;
    Button btnSend1;
    private static final int MY_REQUEST_CODE = 100;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_for_result);
        txtNhan = findViewById(R.id.edt_infor);
        btnSend1 = findViewById(R.id.btnSend1);

        btnSend1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ForResult.this, TextSend.class);
                startActivityForResult(intent, MY_REQUEST_CODE);
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (MY_REQUEST_CODE == requestCode && resultCode == RESULT_OK){
            String text = data.getStringExtra("text");
            txtNhan.setText(text);
        }
    }
}