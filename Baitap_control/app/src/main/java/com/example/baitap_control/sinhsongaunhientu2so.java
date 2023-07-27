package com.example.baitap_control;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Random;

public class sinhsongaunhientu2so extends AppCompatActivity {

    private Button btnRnd;
    private TextView txtSoN;
    private EditText editTextSotn;
    private EditText editTextSoth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sinhsongaunhientu2so);
        txtSoN = (TextView) findViewById (R.id.textViewSo);
        btnRnd = (Button) findViewById(R.id.buttonRnd);
        editTextSotn =(EditText) findViewById(R.id.editTextSotn);
        editTextSoth=(EditText) findViewById(R.id.editTextSoth);
        btnRnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int max=Integer.parseInt(editTextSotn.getText().toString());
                int min=Integer.parseInt(editTextSoth.getText().toString());;
                //Math.random sẽ trả ra 1 số tù [0,1) nhân với khoảng + min số sẽ nằm từ [min,max)
                int number = (int)(Math.random() * (max -min + 1) + min);
                txtSoN.setText(number+"");
            }
        });
    }
}