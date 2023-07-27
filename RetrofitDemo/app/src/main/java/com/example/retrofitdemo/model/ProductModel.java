package com.example.retrofitdemo.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ProductModel implements Serializable {
    @SerializedName("strMeal")
    private String strMeal;
    @SerializedName("strMealThumb")
    private String strMealThumb;
    @SerializedName("idMeal")
    private String idMeal;
    @SerializedName("idcategory")
    private String idcategory;

    public ProductModel() {
    }

    public ProductModel(String strMeal, String strMealThumb, String idMeal, String idcategory) {
        this.strMeal = strMeal;
        this.strMealThumb = strMealThumb;
        this.idMeal = idMeal;
        this.idcategory = idcategory;
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

    public String getIdMeal() {
        return idMeal;
    }

    public void setIdMeal(String idMeal) {
        this.idMeal = idMeal;
    }

    public String getIdcategory() {
        return idcategory;
    }

    public void setIdcategory(String idcategory) {
        this.idcategory = idcategory;
    }
}
