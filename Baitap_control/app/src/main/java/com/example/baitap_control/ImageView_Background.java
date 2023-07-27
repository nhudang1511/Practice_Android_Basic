package com.example.baitap_control;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.ImageView;

public class ImageView_Background extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_view_background);
        ImageView img1= (ImageView) findViewById(R.id.imageView2);

        ConstraintLayout bg = (ConstraintLayout) findViewById(R.id.constraintLayout1);
        bg.setBackgroundColor(Color.BLUE);

    }
}