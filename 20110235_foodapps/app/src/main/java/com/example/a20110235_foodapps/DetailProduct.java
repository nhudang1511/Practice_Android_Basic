package com.example.a20110235_foodapps;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.a20110235_foodapps.api.APIService;
import com.example.a20110235_foodapps.model.DetailProductMessage;
import com.example.a20110235_foodapps.model.DetailProductModel;
import com.example.a20110235_foodapps.model.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailProduct extends AppCompatActivity {

    TextView tv_product_name, tv_price,tv_detail;
    ImageView img_profile;
    DetailProductMessage detailProductMessage;
    List<DetailProductModel> detailProductModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_product);
        AnhXa();
        Intent intent = getIntent();
        int productId = intent.getIntExtra("idProduct", 0);
        getDetailProduct(productId);
    }
    public void AnhXa(){
        tv_product_name = findViewById(R.id.tv_productName);
        tv_price=findViewById(R.id.tv_price);
        tv_detail=findViewById(R.id.tv_detail);
        img_profile=findViewById(R.id.img_productDetail);
    }
    public void getDetailProduct(int productId){
        APIService.apiservice.getDetailProduct(productId).enqueue(new Callback<DetailProductMessage>() {
            @Override
            public void onResponse(Call<DetailProductMessage> call, Response<DetailProductMessage> response) {
                if (response.isSuccessful()){
                    detailProductMessage = response.body();
                    detailProductModel = detailProductMessage.getProductDetailList();
                    DetailProductModel product = detailProductModel.get(0);
                    tv_product_name.setText(product.getMeal());
                    tv_price.setText(product.getPrice());
                    tv_detail.setText(product.getInstructions());
                    Glide.with(getApplicationContext()).load(product.getImageProduct()).into(img_profile);
                }else {
                    int statusCode = response.code();
                }
            }

            @Override
            public void onFailure(Call<DetailProductMessage> call, Throwable t) {

            }
        });
    }
}