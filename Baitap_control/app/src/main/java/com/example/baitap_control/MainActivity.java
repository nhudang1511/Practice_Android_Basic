package com.example.baitap_control;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private Button btnRnd;
    private TextView txtSoN;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtSoN = (TextView) findViewById (R.id.textViewSo);
        btnRnd = (Button) findViewById(R.id.buttonRnd);
        btnRnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Random random = new Random();
                // sẽ nhận giá trị của một số nguyên ngẫu nhiên trong khoảng từ 0 đến 9
                int number = random.nextInt(10);
                txtSoN.setText(number+"");
            }
        });
        
    }
}