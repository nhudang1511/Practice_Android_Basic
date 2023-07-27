package com.example.foodapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.foodapp.api.ApiService;
import com.example.foodapp.api.RetrofitClient;
import com.example.foodapp.model.DetailProduct;
import com.example.foodapp.model.MessageProduct;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductDetail extends AppCompatActivity {

    TextView txt_productName, txt_price, txt_info;

    ImageView img_product;

    List<DetailProduct> productDetailList;

    ApiService apiService;

    MessageProduct messageProduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        Mapping();

        Intent intent = getIntent();
        String idProduct = intent.getStringExtra("idProduct");
        getDetailProduct(idProduct);
    }

    private void getDetailProduct(String idProduct) {
        apiService = RetrofitClient.getRetrofit2().create(ApiService.class);
        apiService.getDetailProduct(idProduct).enqueue(new Callback<MessageProduct>() {
            @Override
            public void onResponse(Call<MessageProduct> call, Response<MessageProduct> response) {
                if (response.isSuccessful()){
                    messageProduct = response.body();
                    productDetailList = messageProduct.getProductDetailList();
                    DetailProduct product = productDetailList.get(0);
                    txt_productName.setText(product.getMeal());
                    txt_price.setText(product.getPrice());
                    txt_info.setText(product.getInstructions());
                    Glide.with(getApplicationContext()).load(product.getImageProduct()).into(img_product);
                }else {
                    int statusCode = response.code();
                }
            }

            @Override
            public void onFailure(Call<MessageProduct> call, Throwable t) {
                Log.d("ProductEror",t.getMessage());
            }
        });
    }

    private void Mapping() {
        txt_productName = findViewById(R.id.tv_productName);
        txt_price = findViewById(R.id.tv_price);
        txt_info = findViewById(R.id.tv_detail);
        img_product = findViewById(R.id.img_productDetail);
    }
}