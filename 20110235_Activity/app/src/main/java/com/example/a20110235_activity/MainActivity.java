package com.example.a20110235_activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btnSend;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnSend = findViewById(R.id.btnSend);
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SendActivity();
            }
        });
    }
    public void SendActivity(){
        //Khong su dung Bundle
        Intent intent = new Intent(this,SecondActivity.class);
        intent.putExtra("String_key", "Truyen 1 string");

        //Su sung Bundle
        Bundle bundle = new Bundle();
        bundle.putInt("Int_key", 123);

        Person person = new Person(1,"nhu");
        bundle.putSerializable("t1",person);

        intent.putExtras(bundle);
        startActivity(intent);
    }
}