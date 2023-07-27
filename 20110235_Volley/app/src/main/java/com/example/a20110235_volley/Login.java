package com.example.a20110235_volley;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Login extends AppCompatActivity {

    EditText editTextName,editTextPass;
    ImageView imgLogin;
    TextView txtRegister;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        AnhXa();
        if(SharedPrefManager.getInstance(this).isLoggedIn()){
            finish();

        }
        imgLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userLogin();
            }
        });
        txtRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                startActivity(new Intent(getApplicationContext(), Register.class));

            }
        });

    }
    public void AnhXa(){
        editTextName=findViewById(R.id.editTextemail);
        editTextPass=findViewById(R.id.editTextPass);
        imgLogin=findViewById(R.id.imgLogin);
        txtRegister=findViewById(R.id.textViewRegister);
    }
    public void userLogin(){
        String username=editTextName.getText().toString();
        String password=editTextPass.getText().toString();
        if(TextUtils.isEmpty(username)){
            editTextName.setError("Please enter your username");
            editTextName.requestFocus();
            return;
        }
        if(TextUtils.isEmpty(password)){
            editTextPass.setError("Please enter your pass");
            editTextPass.requestFocus();
            return;
        }
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Contant.URL_LOGIN,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject obj = new JSONObject(response);
                            if(!obj.getBoolean("error")){
                                //lấy object user từ IJONObject
                                JSONObject userJson = obj.getJSONObject("user");
                                //đưa các giá trị lấy được từ JSOn vào user
                                User user = new User(
                                        userJson.getInt("id"),
                                        userJson.getString("username"),
                                        userJson.getString("email"),
                                        userJson.getString("gender"),
                                        userJson.getString("images")
                                );
                                SharedPrefManager.getInstance(getApplicationContext()).userLogin(user);
                                finish();
                                Intent intent = new Intent(Login.this, Profile.class);
                                startActivity(intent);
                            }
                            else{
                                Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();
                            }
                        }
                        catch (JSONException e){
                            e.printStackTrace();

                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }

        )
        {

            @Override
            protected Map<String, String> getParams () throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("username", username);
                params.put("password", password);
                return params;
            }
        } ;
        VolleySingle.getInstance(this).addToRequestQueue(stringRequest);
    }

}