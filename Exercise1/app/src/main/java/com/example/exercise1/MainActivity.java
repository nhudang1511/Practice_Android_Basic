package com.example.exercise1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.bumptech.glide.Glide;
import com.example.exercise1.adapter.CategoryAdapter;

import com.example.exercise1.adapter.ProductAdapter;
import com.example.exercise1.api.FoodApi;

import com.example.exercise1.model.Category;
import com.example.exercise1.model.Product;
import com.example.exercise1.model.StateMessage;
import com.example.exercise1.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rcvCategory;
    private RecyclerView rcv_product;
    private TextView tvUsername;
    private ImageView imgProfile, imvProfile;
    private List<Category> categoryList;
    private List<Product> productList;

    private User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        Bundle bundleRecieve = getIntent().getExtras();
        if(bundleRecieve != null){
             user = (User) bundleRecieve.get("userObject");
            if(user != null){
                tvUsername.setText(user.getName());
                Glide.with(getApplicationContext()).load(user.getImages()).into(imgProfile);
            }
        }

        callApiCategory();
        callApiProduct();

        imvProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                profileClink();
            }
        });

    }

    private void profileClink() {
        Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("userObject", user);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    private void callApiProduct() {
        FoodApi.apiService.getLastestProduct().enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                productList = response.body();
                ProductAdapter productAdapter = new ProductAdapter(productList, MainActivity.this);
                rcv_product.setHasFixedSize(true);
                RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), 3);
                rcv_product.setLayoutManager(layoutManager);
                rcv_product.setAdapter(productAdapter);
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Call api error", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initView() {
        tvUsername = findViewById(R.id.tv_Username);
        imgProfile = findViewById(R.id.img_profile);
        rcvCategory = findViewById(R.id.rcv_category);
        rcv_product = findViewById(R.id.rcv_product);

        imvProfile = findViewById(R.id.imv_Profile);
    }

    public void callApiCategory(){
        FoodApi.apiService.getCategories().enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
                Toast.makeText(MainActivity.this, "Call category api success", Toast.LENGTH_SHORT).show();
                categoryList = response.body();
                CategoryAdapter categoryAdapter = new CategoryAdapter(categoryList, MainActivity.this);
                rcvCategory.setHasFixedSize(true);
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(),
                        LinearLayoutManager.HORIZONTAL, false);
                rcvCategory.setLayoutManager(layoutManager);
                rcvCategory.setAdapter(categoryAdapter);
            }

            @Override
            public void onFailure(Call<List<Category>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Call api error", Toast.LENGTH_SHORT).show();
            }
        });

    }


}