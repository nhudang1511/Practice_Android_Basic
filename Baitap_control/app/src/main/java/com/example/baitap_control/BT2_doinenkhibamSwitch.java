package com.example.baitap_control;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import java.util.Random;

public class BT2_doinenkhibamSwitch extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bt2_doinenkhibam_switch);
        ConstraintLayout bg = (ConstraintLayout) findViewById(R.id.constraintLayout1);
        Switch sw = (Switch) findViewById(R.id.switch1);
        bg.setBackgroundResource(R.drawable.bg2);
        sw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){ //isChecked = true
                    Toast.makeText(BT2_doinenkhibamSwitch.this,"Wifi đang bật",Toast.LENGTH_LONG).show();
                    bg.setBackgroundResource(R.drawable.bg3);
                }else{
                    Toast.makeText(BT2_doinenkhibamSwitch.this,"Wifi đang tắt",Toast.LENGTH_LONG).show();
                    bg.setBackgroundResource(R.drawable.bg2);


                }
            }
        });
    }
}