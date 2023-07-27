package com.example.retrofitloginsignup;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.retrofitloginsignup.API.APIService;
import com.example.retrofitloginsignup.API.RetrofitClient;
import com.example.retrofitloginsignup.Adapter.CategoryAdapter;
import com.example.retrofitloginsignup.Adapter.ProductAdapter;
import com.example.retrofitloginsignup.Model.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductCategoryActivity extends AppCompatActivity {
    List<Product> products;
    APIService apiService;

    RecyclerView rvProduct;

    ProductAdapter productAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.productofcategory_activity);
        rvProduct = findViewById(R.id.rvProductOfCategory);
        Intent intent=getIntent();
        int id=intent.getIntExtra("id",0);
        getProduct(id);

    }
    public void getProduct(int id){
        apiService = RetrofitClient.getAppfood().create(APIService.class);
        apiService.getCategory(id).enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if(response.isSuccessful())
                {
                    products = response.body();
                    productAdapter = new ProductAdapter(ProductCategoryActivity.this,products);
                    GridLayoutManager gridLayoutManager =
                            new GridLayoutManager(getApplicationContext(),3);
                    rvProduct.setLayoutManager(gridLayoutManager);
                    rvProduct.setAdapter(productAdapter);
                    productAdapter.notifyDataSetChanged();

                }
                else {
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
