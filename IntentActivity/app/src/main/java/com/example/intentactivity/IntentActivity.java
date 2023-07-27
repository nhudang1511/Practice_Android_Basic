package com.example.intentactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class IntentActivity extends AppCompatActivity {
    private EditText editEmail;
    private Button btnUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent);

        getSupportActionBar().setTitle("Activity 2");

        editEmail = findViewById(R.id.edt_Email);
        btnUpdate = findViewById(R.id.btn_update);

        /*editEmail.setText(AppUlti.mEnail);*/

        editEmail.setText(getIntent().getStringExtra("key_email"));

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backActivity();
            }
        });
    }

    private void backActivity() {
        String strEmailUpdate = editEmail.getText().toString().trim();
        /*AppUlti.mEnail = strEmailUpdate;*/
        Intent returnIntent = new Intent();
        returnIntent.putExtra("key_email", strEmailUpdate);
        setResult(Activity.RESULT_OK, returnIntent);
        finish();
    }
}