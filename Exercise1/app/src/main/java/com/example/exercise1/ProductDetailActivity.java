package com.example.exercise1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.exercise1.api.FoodApi;
import com.example.exercise1.model.DetailMessage;
import com.example.exercise1.model.DetailProduct;
import com.example.exercise1.model.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductDetailActivity extends AppCompatActivity {
    private TextView tv_ProductName, tvPrice, tvDetail;
    private ImageView img_ProductDetail;
    List<DetailProduct> productDetailList;
    private String TAG = ProductDetailActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        initView();

        Intent intent = getIntent();
        String id = intent.getStringExtra("idProduct");
        getDetailProduct(id);
    }

    private void getDetailProduct(String id) {
        Toast.makeText(this, id, Toast.LENGTH_SHORT).show();
       FoodApi.apiService.getDetailProduct(id).enqueue(new Callback<DetailMessage>() {
           @Override
           public void onResponse(Call<DetailMessage> call, Response<DetailMessage> response) {
               Toast.makeText(ProductDetailActivity.this, "Call Detail Success", Toast.LENGTH_SHORT).show();
               DetailMessage detailMessage = response.body();
               if(detailMessage.isSuccess()){
                   productDetailList = detailMessage.getDetailProduct();
                   DetailProduct detailProduct = productDetailList.get(0);
                   tv_ProductName.setText(detailProduct.getMeal());
                   tvPrice.setText(detailProduct.getPrice());
                   tvDetail.setText(detailProduct.getInstructions());
                   Glide.with(getApplicationContext()).load(detailProduct.getStrmealthumb()).into(img_ProductDetail);
               }
           }

           @Override
           public void onFailure(Call<DetailMessage> call, Throwable t) {
               Toast.makeText(ProductDetailActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
               Log.e(TAG, t.getMessage());

           }
       });
    }

    private void initView() {
        tv_ProductName = findViewById(R.id.tv_ProductName);
        tvPrice = findViewById(R.id.tvPrice);
        tvDetail = findViewById(R.id.tvDetail);
        img_ProductDetail = findViewById(R.id.imgProductDetail);
    }
}