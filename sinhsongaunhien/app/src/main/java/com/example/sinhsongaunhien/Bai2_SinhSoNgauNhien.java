package com.example.sinhsongaunhien;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Random;

public class Bai2_SinhSoNgauNhien extends AppCompatActivity {

    private Button btnRnd;
    private TextView txtSoN;
    private EditText editTextSotn;
    private EditText editTextSoth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bai2);
        txtSoN = (TextView) findViewById (R.id.buttonRnd);
        btnRnd = (Button) findViewById(R.id.buttonRnd);
        editTextSotn =(EditText) findViewById(R.id.editTextSotn);
        editTextSoth=(EditText) findViewById(R.id.editTextSoth);
        btnRnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Random random = new Random();
                int max=Integer.parseInt(editTextSotn.getText().toString());
                int min=Integer.parseInt(editTextSoth.getText().toString());;
                int number = (int)(Math.random() * (max -min + 1) + min);
                //int number = random.nextInt(10);
                txtSoN.setText(number+"");
            }
        });
    }
}