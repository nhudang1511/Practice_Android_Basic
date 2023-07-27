package com.example.a20110235_viewshortwithfirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    EditText etName,etPassword;
    ImageView imgLogin;
    TextView txtViewRegister;
    protected FirebaseAuth mAuth = FirebaseAuth.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        AnhXa();
        txtViewRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });
        imgLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickLogin();
            }
        });
    }
    public void onClickLogin(){
        final String email = etName.getText().toString();
        final String password = etPassword.getText().toString();
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this,new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                       if(task.isSuccessful()){
                           //Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                           //startActivity(intent);
                           if(mAuth.getCurrentUser().isEmailVerified()){
                               Toast.makeText(LoginActivity.this, "Da xac thuc.",
                                       Toast.LENGTH_SHORT).show();
                           }
                           else {
                               Toast.makeText(LoginActivity.this, "Chua xac thuc",
                                       Toast.LENGTH_SHORT).show();
                           }

                       }
                       else {
                           Toast.makeText(LoginActivity.this, "Login that bai", Toast.LENGTH_SHORT).show();
                       }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(LoginActivity.this, "Login failed.",
                                Toast.LENGTH_SHORT).show();
                    }
                });
    }
    public void AnhXa(){
        etName = findViewById(R.id.editTextemail);
        etPassword = findViewById(R.id.editTextPass);
        imgLogin=findViewById(R.id.imageView4);
        txtViewRegister =findViewById(R.id.textViewRegister);
    }
}