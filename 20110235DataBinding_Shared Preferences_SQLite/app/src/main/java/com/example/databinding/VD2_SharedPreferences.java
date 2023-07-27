package com.example.databinding;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class VD2_SharedPreferences extends AppCompatActivity {

    private EditText mEmailView;
    private EditText mPasswordView;
    private CheckBox checkBoxRememberMe;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vd2_shared_preferences);
        mEmailView = (EditText) findViewById(R.id.email);
        mPasswordView=(EditText) findViewById(R.id.password);
        mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if( i ==R.id.login || i == EditorInfo.IME_NULL){
                    attemptLogin();
                    return true;
                }
                return false;
            }
        });
        Button mEmailSignInButton = (Button) findViewById(R.id.email_sign_in_button);
        mEmailSignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin();
            }
        });
        checkBoxRememberMe = (CheckBox) findViewById(R.id.checkBoxRemember);
        if(!new PreManager(this).isUserLogedOut()){
            startHomeActivity();
        }
    }

    private void startHomeActivity() {
        Intent intent = new Intent(this,HomeActivity.class);
        startActivity(intent);
        finish();
    }

    private void attemptLogin() {
        mEmailView.setError(null);
        mPasswordView.setError(null);
        String email=mEmailView.getText().toString();
        String password = mPasswordView.getText().toString();
        boolean cancel = false;
        View focusView = null;
        if(!TextUtils.isEmpty(password) && !isPasswordVaild(password)){
            mPasswordView.setError(getString(R.string.error_invalis_password));
            focusView=mPasswordView;
            cancel=true;
        }
        if(TextUtils.isEmpty(email)){
            mEmailView.setError(getString(R.string.error_field_required));
            focusView=mEmailView;
            cancel = true;
        }
        else if(!isEmailVaild(email)){
            mEmailView.setError(getString(R.string.error_invalid_email));
            focusView= mEmailView;
            cancel=true;
        }

        if(cancel){
            focusView.requestFocus();
        }
        else {
            if(checkBoxRememberMe.isChecked())
                saveLoginDetails(email,password);
            startHomeActivity();
        }
    }

    private void saveLoginDetails(String email, String password) {
        new PreManager(this).saveLoginDetails(email,password);
    }

    private boolean isEmailVaild(String email) {
        return email.contains("@");
    }

    private boolean isPasswordVaild(String password) {
        return password.length()>4;
    }
}