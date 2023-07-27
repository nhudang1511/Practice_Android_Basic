package com.example.retrofitloginsignup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.bumptech.glide.Glide;
import com.example.retrofitloginsignup.API.APIService;
import com.example.retrofitloginsignup.API.RetrofitClient;
import com.example.retrofitloginsignup.Adapter.CategoryAdapter;
import com.example.retrofitloginsignup.Adapter.ProductAdapter;
import com.example.retrofitloginsignup.Model.Category;
import com.example.retrofitloginsignup.Model.Product;
import com.example.retrofitloginsignup.Model.User;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity {
    List<Category> categories;

    List<Product> products;
    APIService apiService;
    RecyclerView rvCate;
    RecyclerView rvProduct;
    CategoryAdapter categoryAdapter;
    ProductAdapter productAdapter;

    ViewFlipper viewFlipper;

    TextView userName;
    ImageView imgAvt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Mapping();

        if(SharedPrefManager.getInstance(this).isLoggedIn()){
            User user = SharedPrefManager.getInstance(this).getUser();
            userName.setText(user.getName());
            Glide.with(getApplicationContext()).load(user.getImages())
                    .into(imgAvt);
        }
        else {
            Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        }



        getCategory();
        getProduct();
        viewFiliper();

        imgAvt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, ProfileActivity.class);
                startActivity(intent);
                finish();
            }
        });


    }

    public void viewFiliper(){
        ArrayList<String> listAd = new ArrayList<>();

        listAd.add("https://vietadv.vn/wp-content/uploads/2020/08/thiet-ke-poster-vietadv-4.jpg");

        listAd.add("https://images-wixmp-ed30a86b8c4ca887773594c2.wixmp.com/f/ae0cb409-06da-4558-a0d0-2bbd164699c7/dfs8487-3f3face9-a3f1-43c8-a694-b92ad1319596.png/v1/fill/w_1024,h_574,q_80,strp/intergalactic_scenery_desktop_wallpaper___4_by_artfulbits_dfs8487-fullview.jpg?token=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1cm46YXBwOjdlMGQxODg5ODIyNjQzNzNhNWYwZDQxNWVhMGQyNmUwIiwiaXNzIjoidXJuOmFwcDo3ZTBkMTg4OTgyMjY0MzczYTVmMGQ0MTVlYTBkMjZlMCIsIm9iaiI6W1t7InBhdGgiOiJcL2ZcL2FlMGNiNDA5LTA2ZGEtNDU1OC1hMGQwLTJiYmQxNjQ2OTljN1wvZGZzODQ4Ny0zZjNmYWNlOS1hM2YxLTQzYzgtYTY5NC1iOTJhZDEzMTk1OTYucG5nIiwiaGVpZ2h0IjoiPD01NzQiLCJ3aWR0aCI6Ijw9MTAyNCJ9XV0sImF1ZCI6WyJ1cm46c2VydmljZTppbWFnZS53YXRlcm1hcmsiXSwid21rIjp7InBhdGgiOiJcL3dtXC9hZTBjYjQwOS0wNmRhLTQ1NTgtYTBkMC0yYmJkMTY0Njk5YzdcL2FydGZ1bGJpdHMtNC5wbmciLCJvcGFjaXR5Ijo5NSwicHJvcG9ydGlvbnMiOjAuNDUsImdyYXZpdHkiOiJjZW50ZXIifX0.s-sbhhDmyCqCd4_Gm90sIvzeD7EWU1qmvD4NfwMIx1s");

        listAd.add("https://intphcm.com/data/upload/poster-giay.jpg");
        listAd.add("https://inanaz.com.vn/wp-content/uploads/2020/02/mau-poster-ca-nhac-5.jpg");



        for (int i=0; i < listAd.size(); i++){

            ImageView imageView = new ImageView(getApplicationContext());

            Glide.with(getApplicationContext()).load(listAd.get(i)).into(imageView);

            imageView.setScaleType(ImageView.ScaleType.FIT_XY); // Chỉnh kích thước vừa đủ viewFlipper

            viewFlipper.addView(imageView);

        }

        viewFlipper.setFlipInterval(4000); // Thời gian thay đổi, giống như delay vậy

        viewFlipper.setAutoStart(true);		// Tự động chạy khi mở màn hình



        Animation slidein = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_in_right);

        Animation slideout = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_out_left);

        viewFlipper.setInAnimation(slidein);

        viewFlipper.setOutAnimation(slideout);
    }
    public void Mapping(){
        userName = findViewById((R.id.txtName));
        imgAvt = findViewById((R.id.imgAvt));
        rvCate = findViewById(R.id.rvCategory);
        rvProduct = findViewById(R.id.rvProduct);
        viewFlipper = findViewById(R.id.viewFliper);
    }
    public void getProduct(){
        apiService = RetrofitClient.getAppfood().create(APIService.class);
        apiService.getAllProduct().enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if(response.isSuccessful())
                {
                    products = response.body();
                    productAdapter = new ProductAdapter(HomeActivity.this,products);
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

    public void getCategory(){
        apiService = RetrofitClient.getAppfood().create(APIService.class);

        apiService.getCategories().enqueue(new Callback<List<Category>>() {

            @Override
            public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
                if(response.isSuccessful()){

                    categories=response.body();
                    categoryAdapter = new CategoryAdapter(HomeActivity.this,categories);
                    rvCate.setHasFixedSize(true);
                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
                    rvCate.setLayoutManager(layoutManager);
                    rvCate.setAdapter(categoryAdapter);
                    categoryAdapter.notifyDataSetChanged();
                }
                else{
                   int statusCode =  response.code();
                }
            }

            @Override
            public void onFailure(Call<List<Category>> call, Throwable t) {
                Log.d("Fail",t.getMessage());
            }
        });
    }


}