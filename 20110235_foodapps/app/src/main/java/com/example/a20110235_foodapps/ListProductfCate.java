package com.example.a20110235_foodapps;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.a20110235_foodapps.adapter.ProductAdapter;
import com.example.a20110235_foodapps.api.APIService;
import com.example.a20110235_foodapps.model.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListProductfCate extends AppCompatActivity {

    RecyclerView recyclerView;
    List<Product> productList;
    ProductAdapter productAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_productf_cate);
        AnhXa();
        Intent intent = getIntent();
        int cateId = intent.getIntExtra("idCategory", 0);
        getProductofCate(cateId);
    }
    public void AnhXa(){
        recyclerView=findViewById(R.id.rvListProductofCate);
    }
    public void getProductofCate(int cateId){
        APIService.apiservice.getProductofCate(cateId).enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if(response.isSuccessful()){
                    productList=response.body();
                    productAdapter=new ProductAdapter(productList,ListProductfCate.this);
                    recyclerView.setHasFixedSize(true);
                    GridLayoutManager layoutManager=new GridLayoutManager(getApplicationContext(),2);
                    recyclerView.setLayoutManager(layoutManager);
                    recyclerView.setAdapter(productAdapter);
                }else{
                    int statusCode=response.code();
                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Log.d("Error",t.getMessage());
            }
        });
    }
}