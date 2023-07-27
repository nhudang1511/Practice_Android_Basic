package com.example.a20110235_activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        String stringValue = getIntent().getStringExtra("String_key");
        TextView txt = findViewById(R.id.textView);
        txt.setText(stringValue);

        Bundle bundle = getIntent().getExtras();
        int myInt = bundle.getInt("Int_key");
        TextView txtInt = findViewById(R.id.txtInt);
        txtInt.setText(String.valueOf(myInt));

        Person t1 = (Person) bundle.getSerializable("t1");
        TextView txtObject = findViewById(R.id.txtObject);
        txtObject.setText(t1.toString());
    }
}