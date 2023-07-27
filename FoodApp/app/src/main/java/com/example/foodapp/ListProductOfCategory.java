package com.example.foodapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.foodapp.adapter.ProductAdapter;
import com.example.foodapp.api.ApiService;
import com.example.foodapp.api.RetrofitClient;
import com.example.foodapp.model.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListProductOfCategory extends AppCompatActivity {

    List<Product> productList;

    ApiService apiService;

    RecyclerView recyclerView_product;

    ProductAdapter productAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_product_of_category);

        recyclerView_product = findViewById(R.id.rcv_listProduct);
        Intent intent = getIntent();
        int cateId = intent.getIntExtra("idCategory", 0);
        getProducts(cateId);
    }

    private void getProducts(int cateId) {
        apiService = RetrofitClient.getRetrofit2().create(ApiService.class);
        apiService.getProductofCate(cateId).enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if (response.isSuccessful()){
                    productList = response.body();
                    productAdapter = new ProductAdapter(productList, ListProductOfCategory.this);
                    GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), 3);
                    recyclerView_product.setLayoutManager(gridLayoutManager);
                    recyclerView_product.setAdapter(productAdapter);
                }else {
                    int statusCode = response.code();
                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Log.d("ProductEror",t.getMessage());
            }
        });
    }
}