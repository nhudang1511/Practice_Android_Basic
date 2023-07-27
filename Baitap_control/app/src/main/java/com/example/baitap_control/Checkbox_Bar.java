package com.example.baitap_control;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Toast;

public class Checkbox_Bar extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkbox_bar);
        ConstraintLayout bg = (ConstraintLayout) findViewById(R.id.constraintLayout1);
        CheckBox ck1 = (CheckBox) findViewById(R.id.checkBox);
        ck1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    bg.setBackgroundResource(R.drawable.bg1);
                } else {
                    bg.setBackgroundResource(R.drawable.bg3);
                }
            }
        });
        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radioGroup1);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.radioButton:
                        bg.setBackgroundResource(R.drawable.bg2);
                        break;
                        case R.id.radioButton2:
                            bg.setBackgroundResource(R.drawable.bg4);
                            break;
                }
            }
        });
        ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar);
        ImageButton img2 = (ImageButton) findViewById(R.id.imageButton2);
        img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /*int current = progressBar.getProgress();
                progressBar.setProgress(current + 10);*/
              int current = progressBar.getProgress();
                //Khởi tạo đối tượng với tổng thời gian là 10000, thời gian đếm ngược giữa các lần gọi là 1000
                CountDownTimer countDownTimer = new CountDownTimer(10000, 1000) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                        //được gọi sau mỗi khoảng thời gian đếm ngược được xác định bởi tham số thứ hai
                        int current = progressBar.getProgress();
                        //chạy đều đều >100 quay về 0
                        if (current>= progressBar.getMax()){
                            current=0;
                        }
                        progressBar.setProgress(current + 10);
                    }

                    @Override
                    public void onFinish() {
                        //được gọi khi thời gian đếm ngược đã kết thúc
                        Toast.makeText(Checkbox_Bar.this,"Hết giờ",Toast.LENGTH_LONG).show();
                    }
                    };
                countDownTimer.start();
                }
        });
        SeekBar seekBar = (SeekBar) findViewById(R.id.seekBar);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
               //progress: giá trị của seekbar
                Log.d("AAA","Giá trị:" + progress);
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                Log.d("AAA","Start");
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Log.d("AAA","Stop");
            }});
    }
}