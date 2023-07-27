package com.example.a20110235_nhu_tuan8;

import android.util.Log;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Product implements Serializable {
    APIService apiService;

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("strMeal")
    @Expose
    private String strMeal;
    @SerializedName("strMealThumb")
    @Expose
    private String strMealThumb;
    @SerializedName("idMeal")
    @Expose
    private int idMeal;
    @SerializedName("idcategory")
    @Expose
    private int idcategory;

    private Category productCategory;
    public Product(int id, String strMeal, String strMealThumb, int idMeal, int idcategory) {
        this.id = id;
        this.strMeal = strMeal;
        this.strMealThumb = strMealThumb;
        this.idMeal = idMeal;
        this.idcategory = idcategory;
        GetCategoryFromAPI();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStrMeal() {
        return strMeal;
    }

    public void setStrMeal(String strMeal) {
        this.strMeal = strMeal;
    }

    public String getStrMealThumb() {
        return strMealThumb;
    }

    public void setStrMealThumb(String strMealThumb) {
        this.strMealThumb = strMealThumb;
    }

    public int getIdMeal() {
        return idMeal;
    }

    public void setIdMeal(int idMeal) {
        this.idMeal = idMeal;
    }

    public int getIdcategory() {
        return idcategory;
    }

    public void setIdcategory(int idcategory) {
        this.idcategory = idcategory;
    }

    public Category getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(Category productCategory) {
        this.productCategory = productCategory;
    }
    private void GetCategoryFromAPI(){
        apiService=RetrofitClient.getInstance().getRetrofit(constants.URL_CATEGORY).create(APIService.class);
        apiService.getCategory().enqueue(new Callback<Category>()  {
            @Override
            public void onResponse(Call<Category> call, Response<Category> response) {
                if(response.isSuccessful()){
                    setProductCategory(response.body());
                }else{
                    int statusCode=response.code();
                }
            }
            @Override
            public void onFailure(Call<Category> call, Throwable t) {
                Log.d("log",t.getMessage());
            }
        });
    }
}
