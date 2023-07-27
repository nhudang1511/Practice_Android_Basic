package com.example.exercise1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.exercise1.adapter.ProductAdapter;
import com.example.exercise1.api.FoodApi;
import com.example.exercise1.model.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductsOfCategory extends AppCompatActivity {

    private RecyclerView rcv_listProduct;
    private List<Product> productList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products_of_category);
        rcv_listProduct = findViewById(R.id.rcv_listProduct);
        Intent intent = getIntent();
        int id =intent.getIntExtra("idCategory", 0);
        getProductList(id);
    }

    private void getProductList(int id) {
        FoodApi.apiService.getListProducts(id).enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                Toast.makeText(ProductsOfCategory.this, "Call List Product Success", Toast.LENGTH_SHORT).show();
                productList = response.body();
                ProductAdapter productAdapter = new ProductAdapter(productList, ProductsOfCategory.this);
                GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(),3);
                rcv_listProduct.setLayoutManager(gridLayoutManager);
                rcv_listProduct.setAdapter(productAdapter);
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Toast.makeText(ProductsOfCategory.this, "Call List Product Error", Toast.LENGTH_SHORT).show();
            }
        });
    }
}