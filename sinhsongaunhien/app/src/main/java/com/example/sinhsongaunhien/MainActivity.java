package com.example.sinhsongaunhien;

import static java.lang.Math.random;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.service.controls.actions.ControlAction;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private Button btnRnd;
    private TextView txtSoN;
    private EditText editTextSotn;
    private EditText editTextSoth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.example_imageview);
         //bai 2
        ImageView img1 = (ImageView) findViewById(R.id.imageView1);
        //img1.setImageResource(R.drawable.ic_launcher_background);
        ConstraintLayout bg = (ConstraintLayout) findViewById(R.id.constraintLayout1);
        //bg.setBackgroundColor(Color.BLUE);
        //bg.setBackgroundResource(R.drawable.bg3);

        ArrayList<Integer> arrayList = new ArrayList<>();
        arrayList.add(R.drawable.bg1);
        arrayList.add(R.drawable.bg2);
        //arrayList.add(R.drawable.bg3);
        arrayList.add(R.drawable.bg4);
        Random random = new Random();
        int vitri = random.nextInt(arrayList.size());
        bg.setBackgroundResource(arrayList.get(vitri));

        ImageButton img2 = (ImageButton) findViewById(R.id.imageButton1);
        img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivities(new Intent[]{getIntent()});
            }
        });

        //Switch

        Switch sw = (Switch) findViewById(R.id.switch1);
        sw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){ //isChecked = true
                    Toast.makeText(MainActivity.this,"Wifi đang bật",Toast.LENGTH_LONG).show();
                    //bg.setBackgroundResource(arrayList.get(vitri));
                    Random random1 = new Random();
                    int vitri1 = random1.nextInt(arrayList.size());
                    bg.setBackgroundResource(arrayList.get(vitri1));
                }else{
                    Toast.makeText(MainActivity.this,"Wifi đang tắt",Toast.LENGTH_LONG).show();
                    // bg.setBackgroundResource(R.drawable.bg3);
                    bg.setBackgroundColor(Color.GREEN);

                }
            }
        });
        //Check book
        CheckBox ck1 = (CheckBox) findViewById(R.id.checkBox);
        ck1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {
                if(isChecked){
                    bg.setBackgroundResource(R.drawable.bg4);
                }else{
                    bg.setBackgroundResource(R.drawable.bg3);
                }
            }
        });

        //progress bar
        ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar2);
        img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               /* int current = progressBar.getProgress();
                progressBar.setProgress(current + 10);*/
                int current = progressBar.getProgress();
              //chạy đều đều >100 quay về 0
                if (current>= progressBar.getMax()){
                    current=0;
                }
                progressBar.setProgress(current + 10);
                CountDownTimer countDownTimer = new CountDownTimer(10000, 1000) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                        int current = progressBar.getProgress();
                        //chạy đều đều >100 quay về 0
                        if (current>= progressBar.getMax()){
                            current=0;
                        }
                        progressBar.setProgress(current + 10);
                    }

                    @Override
                    public void onFinish() {
                        Toast.makeText(MainActivity.this,"Hết giờ",Toast.LENGTH_LONG).show();
                    }
                    };
                countDownTimer.start();
                }
        });



    }
}