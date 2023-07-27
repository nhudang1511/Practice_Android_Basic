package com.example.retrofitdemo.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.retrofitdemo.R;
import com.example.retrofitdemo.adapter.CategoryAdapter;
import com.example.retrofitdemo.adapter.ProductAdapter;
import com.example.retrofitdemo.api.APIService;
import com.example.retrofitdemo.model.CategoryModel;
import com.example.retrofitdemo.model.ProductModel;
import com.example.retrofitdemo.model.UserModel;
import com.example.retrofitdemo.retrofitClient.RetrofitClient;
import com.example.retrofitdemo.util.SharedPrefManager;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    TextView tvUserName;
    ImageView imgUserImage;

    RecyclerView recyclerViewCategory;
    RecyclerView recyclerViewProduct;

    CategoryAdapter categoryAdapter;
    ProductAdapter productAdapter;

    UserModel userModel;

    APIService apiService;

    List<CategoryModel> categoryModels;
    List<ProductModel> productModels;

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AnhXa();
        GetInfo();
        GetCategory();
        GetProduct();
    }

    private void AnhXa(){
        tvUserName = (TextView) findViewById(R.id.tvUserName);
        imgUserImage = (ImageView) findViewById(R.id.imgUserImage);
        recyclerViewCategory = (RecyclerView) findViewById(R.id.recyclerViewCategory);
        recyclerViewProduct =(RecyclerView) findViewById(R.id.recyclerViewProduct);
    }

    private void GetInfo() {
        userModel = SharedPrefManager.getInstance(getApplicationContext()).getUser();
        tvUserName.setText(userModel.getUsername());
        Glide.with(MainActivity.this)
                .load(userModel.getImages())
                .into(imgUserImage);
    }

    private void GetCategory() {
        //Gọi Interface trong API
        apiService = RetrofitClient.getRetrofitCategory().create(APIService.class);
        apiService.getCategoryAll().enqueue(new Callback<List<CategoryModel>>() {
            @Override
            public void onResponse(Call<List<CategoryModel>> call, Response<List<CategoryModel>> response) {
                if (response.isSuccessful()) {
                    categoryModels = response.body();

                    //Khởi tạo Adapter
                    categoryAdapter = new CategoryAdapter(categoryModels, MainActivity.this);
                    recyclerViewCategory.setHasFixedSize(true);
                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(),
                            LinearLayoutManager.HORIZONTAL, false);
                    recyclerViewCategory.setLayoutManager(layoutManager);
                    recyclerViewCategory.setAdapter(categoryAdapter);
                    categoryAdapter.notifyDataSetChanged();
                }
                else {
                    int statusCode = response.code();
                }
            }

            @Override
            public void onFailure(Call<List<CategoryModel>> call, Throwable t)  {
                Log.d("logg", t.getMessage());
            }
        });
    }

    private void GetProduct() {
        apiService = RetrofitClient.getRetrofitProduct().create(APIService.class);
        apiService.getProductAll().enqueue(new Callback<List<ProductModel>>() {
            @Override
            public void onResponse(Call<List<ProductModel>> call, Response<List<ProductModel>> response) {
                if (response.isSuccessful()) {
                    productModels = response.body();

                    //Khởi tạo Adapter
                    categoryAdapter = new CategoryAdapter(categoryModels, MainActivity.this);
                    recyclerViewCategory.setHasFixedSize(true);

                    productAdapter = new ProductAdapter(productModels, MainActivity.this);
                    recyclerViewProduct.setHasFixedSize(true);
                    RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), 3);
                    recyclerViewProduct.setLayoutManager(layoutManager);
                    recyclerViewProduct.setAdapter(productAdapter);
                    productAdapter.notifyDataSetChanged();
                } else {
                    int statusCode = response.code();
                }
            }

            @Override
            public void onFailure(Call<List<ProductModel>> call, Throwable t) {
                Log.d("logg", t.getMessage());
            }
        });
    }
}