package com.example.a20110235_foodapps;

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
import com.example.a20110235_foodapps.adapter.CategoryAdaper;
import com.example.a20110235_foodapps.adapter.ProductAdapter;
import com.example.a20110235_foodapps.api.APIService;
import com.example.a20110235_foodapps.model.Category;
import com.example.a20110235_foodapps.model.Product;
import com.example.a20110235_foodapps.model.User;
import com.example.a20110235_foodapps.model.UserLogin;
import com.example.a20110235_foodapps.sharedpreference.SharePrefManager;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    TextView username, userEmail;
    ImageView imageViewProfile,imageViewUpload;
    RecyclerView rvCate;
    RecyclerView rvProduct;
    List<Category> categoryList;

    List<Product> productList;
    CategoryAdaper categoryAdaper;
    ProductAdapter productAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AnhXa();
        getCategory();
        getLastProduct();
        if(SharePrefManager.getInstance(this).isLoggedIn()) {
            User user = SharePrefManager.getInstance(this).getUser();
            username.setText(user.getUsername());
            userEmail.setText(user.getEmail());
            Glide.with(this).load(user.getImages()).into(imageViewProfile);
            imageViewUpload.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent =  new Intent(MainActivity.this, Profile.class);
                    startActivity(intent);
                }
            });
        }
        else {

        }
    }
    public void AnhXa(){
        username = findViewById(R.id.tvUserName);
        userEmail = findViewById(R.id.textView6);
        imageViewProfile = findViewById(R.id.imgUserImage);
        rvCate = findViewById(R.id.recyclerViewCategory);
        rvProduct=findViewById(R.id.recyclerViewProduct);
        imageViewUpload = findViewById(R.id.imv_Profile);
    }
    public void getCategory(){
        APIService.apiservice.getCategories().enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
                if(response.isSuccessful()){
                    categoryList=response.body();
                    categoryAdaper=new CategoryAdaper(categoryList,MainActivity.this);
                    rvCate.setHasFixedSize(true);
                    RecyclerView.LayoutManager layoutManager=new LinearLayoutManager((getApplicationContext()),LinearLayoutManager.HORIZONTAL,false);
                    rvCate.setLayoutManager(layoutManager);
                    rvCate.setAdapter(categoryAdaper);
                    categoryAdaper.notifyDataSetChanged();
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
    public void getLastProduct(){
        APIService.apiservice.getLastProduct().enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if(response.isSuccessful()){
                    productList=response.body();
                    productAdapter=new ProductAdapter(productList,MainActivity.this);
                    rvProduct.setHasFixedSize(true);
                    GridLayoutManager layoutManager=new GridLayoutManager(getApplicationContext(),3);
                    rvProduct.setLayoutManager(layoutManager);
                    rvProduct.setAdapter(productAdapter);
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