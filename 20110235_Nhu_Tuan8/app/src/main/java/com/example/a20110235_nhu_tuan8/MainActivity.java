package com.example.a20110235_nhu_tuan8;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    CategoryAdapter categoryAdapter;
    ProductAdapter productAdapter;
    RecyclerView rcCate;
    RecyclerView rcProduct;
    APIService apiService;
    List<Category> categoryList;
    List<Product> productList;
    TextView id, username, userEmail, gender;
    Button btnlogout, btnCategory;
    ImageView imageViewProfile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AnhXa();
        GetCategory();
        GetProduct();
        Log.d("logg", "onCreate:"+ SharePrefManager.getInstance(this).isLoggedIn());
        if(SharePrefManager.getInstance(this).isLoggedIn()){

            username = findViewById(R.id.tvUserName);
            userEmail = findViewById(R.id.textView6);

            btnlogout = findViewById(R.id.btn_logout);
            imageViewProfile = findViewById(R.id.imgUserImage);

            User user = SharePrefManager.getInstance(this).getUser();
            username.setText(user.getUsername());
            userEmail.setText(user.getEmail());
            Glide.with(this).load(user.getImages()).into(imageViewProfile);
            btnlogout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(view.equals(btnlogout)){
                        SharePrefManager.getInstance(getApplicationContext()).logout();
                    }
                }
            });
            imageViewProfile.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(MainActivity.this,Profile.class);
                    startActivity(intent);
                }
            });


        }
        else{
            Intent intent = new Intent(MainActivity.this,Login.class);
            startActivity(intent);
            finish();
        }
    }
    private void AnhXa(){
        rcCate=(RecyclerView) findViewById(R.id.recyclerViewCategory);
        rcProduct=(RecyclerView) findViewById(R.id.recyclerViewProduct);
    }
    private void GetCategory(){
        apiService=RetrofitClient.getInstance().getRetrofit(constants.URL_CATEGORY).create(APIService.class);
        apiService.getCategoryAll().enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
                if(response.isSuccessful()){
                    categoryList=response.body();
                    categoryAdapter=new CategoryAdapter(categoryList,MainActivity.this);
                    rcCate.setHasFixedSize(true);
                    RecyclerView.LayoutManager layoutManager=new LinearLayoutManager((getApplicationContext()),LinearLayoutManager.HORIZONTAL,false);
                    rcCate.setLayoutManager(layoutManager);
                    rcCate.setAdapter(categoryAdapter);
                    categoryAdapter.notifyDataSetChanged();
                }else{
                    int statusCode=response.code();
                }
            }

            @Override
            public void onFailure(Call<List<Category>> call, Throwable t) {
                Log.d("logg",t.getMessage());
            }
        });
    }
    private void GetProduct() {
        apiService=RetrofitClient.getInstance().getRetrofit(constants.URL_CATEGORY).create(APIService.class);
        apiService.getProductAll().enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if(response.isSuccessful()){
                    productList=response.body();
                    productAdapter=new ProductAdapter(productList,MainActivity.this);
                    rcProduct.setHasFixedSize(true);
                    GridLayoutManager layoutManager=new GridLayoutManager(getApplicationContext(),3);
                    rcProduct.setLayoutManager(layoutManager);
                    rcProduct.setAdapter(productAdapter);
                }else{
                    int statusCode=response.code();
                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Log.d("logg",t.getMessage());
            }
        });
    }
}