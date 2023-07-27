package com.example.sinhsongaunhien;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Random;

public class Bai1_SinhSoNgauNgien extends AppCompatActivity {

    private Button btnRnd;
    private TextView txtSoN;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bai1);
        txtSoN = findViewById (R.id.textViewSo);
        btnRnd = findViewById(R.id.buttonRnd);
        btnRnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Random random = new Random();
                int number = random.nextInt(10);
                txtSoN.setText(number+"");
            }
        });
    }
}