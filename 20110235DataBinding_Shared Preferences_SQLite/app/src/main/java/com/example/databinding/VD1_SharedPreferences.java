package com.example.databinding;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class VD1_SharedPreferences extends AppCompatActivity {

    Button buttonTxt;
    EditText usernameTxt, passwordTxt;
    CheckBox cbRememberMe;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vd1_shared_preferences);
        AnhXa();
        buttonTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = usernameTxt.getText().toString().trim();
                String password = passwordTxt.getText().toString().trim();
                if(username.equals("admin")&&password.equals("admin")){
                    Toast.makeText(VD1_SharedPreferences.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                    if(cbRememberMe.isChecked()){
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("taikhoan", username);
                        editor.putString("matkhau", password);
                        editor.putBoolean("trangthai",true);
                        editor.commit();
                    }
                    else {
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.remove("taikhoan");
                        editor.remove("matkhau");
                        editor.remove("trangthai");
                        editor.commit();
                    }
                }
                else {
                    Toast.makeText(VD1_SharedPreferences.this, "Đăng nhập thất bại", Toast.LENGTH_SHORT).show();
                }
            }

        });
        sharedPreferences = getSharedPreferences("dataLogin",MODE_PRIVATE);
        usernameTxt.setText(sharedPreferences.getString("taikhoan",""));
        passwordTxt.setText(sharedPreferences.getString("matkhau",""));
        cbRememberMe.setChecked(sharedPreferences.getBoolean("trangthai",false));
    }
    private void AnhXa(){
        buttonTxt = (Button) findViewById(R.id.buttonTxt);
        usernameTxt = (EditText) findViewById(R.id.usernameTxt);
        passwordTxt = (EditText) findViewById(R.id.passwordTxt);
        cbRememberMe = (CheckBox) findViewById(R.id.cbmemberme);
    }
}