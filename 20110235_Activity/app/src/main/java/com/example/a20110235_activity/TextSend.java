package com.example.a20110235_activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class TextSend extends AppCompatActivity {

    TextView edtInput;
    Button btnSendtoMain;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_send);
        edtInput = findViewById(R.id.edt_send);
        btnSendtoMain=findViewById(R.id.btnSend2);
        btnSendtoMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = edtInput.getText().toString();
                Intent resultIntent = new Intent();
                resultIntent.putExtra("text", text);
                setResult(RESULT_OK, resultIntent);
                finish();
            }
        });
    }
}