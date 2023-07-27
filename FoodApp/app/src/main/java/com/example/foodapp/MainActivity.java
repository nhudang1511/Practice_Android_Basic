package com.example.foodapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.foodapp.adapter.CategoryAdapter;
import com.example.foodapp.adapter.ProductAdapter;
import com.example.foodapp.api.ApiService;
import com.example.foodapp.api.RetrofitClient;
import com.example.foodapp.model.Category;
import com.example.foodapp.model.Product;
import com.example.foodapp.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {


    List<Category> categoryList;

    List<Product> productList;
    TextView txt_UserName;
    ImageView img_avatar, imv_profile;

    RecyclerView rv_categories, rv_products;

    ApiService apiService;

    CategoryAdapter categoryAdapter;

    ProductAdapter productAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Mapping();

        if(SharedPrefManager.getInstance(this).isLoggedIn()){
            User user = SharedPrefManager.getInstance(this).getUser();
            txt_UserName.setText(user.getUsername());
            Glide.with(getApplicationContext()).load(user.getImages())
                    .into(img_avatar);
        }
        else {
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        }
        
        getAllCategories();
        getLastproduct();

        imv_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent(MainActivity.this, Profile.class);
                startActivity(intent);
            }
        });

    }

    private void getLastproduct() {
        apiService = RetrofitClient.getRetrofit2().create(ApiService.class);

        apiService.getLastProduct().enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if (response.isSuccessful()){
                    productList = response.body();
                    productAdapter = new ProductAdapter(productList, MainActivity.this);
                    rv_products.setHasFixedSize(true);
                    RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), 3);
                    rv_products.setLayoutManager(layoutManager);
                    rv_products.setAdapter(productAdapter);
                    productAdapter.notifyDataSetChanged();
                } else {
                    int statusCode =  response.code();
                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Log.d("Fail",t.getMessage());
            }
        });
    }

    private void getAllCategories() {
        apiService = RetrofitClient.getRetrofit2().create(ApiService.class);

        apiService.getCategories().enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
                if (response.isSuccessful()){
                    categoryList = response.body();
                    categoryAdapter = new CategoryAdapter(categoryList, MainActivity.this);
                    rv_categories.setHasFixedSize(true);
                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
                    rv_categories.setLayoutManager(layoutManager);
                    rv_categories.setAdapter(categoryAdapter);
                    categoryAdapter.notifyDataSetChanged();
                } else {
                    int statusCode =  response.code();
                }

            }

            @Override
            public void onFailure(Call<List<Category>> call, Throwable t) {
                Log.d("Fail",t.getMessage());
            }
        });
    }

    private void Mapping() {
        txt_UserName = findViewById(R.id.tv_Username);
        img_avatar = findViewById(R.id.img_profile);
        rv_categories = findViewById(R.id.rcv_category);
        rv_products = findViewById(R.id.rcv_product);
        imv_profile = findViewById(R.id.imv_Profile);
    }
}