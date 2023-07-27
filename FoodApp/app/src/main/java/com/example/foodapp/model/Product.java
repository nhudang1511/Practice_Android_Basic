package com.example.foodapp.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Product implements Serializable {
    @SerializedName("id")
    private String id;

    @SerializedName("strMeal")
    private String nameMeal;

    @SerializedName("strMealThumb")
    private String imgMeal;

    @SerializedName("idMeal")
    private String idMeal;

    @SerializedName("idcategory")
    private String idcategory;

    public Product(String id, String nameMeal, String imgMeal, String idMeal, String idcategory) {
        this.id = id;
        this.nameMeal = nameMeal;
        this.imgMeal = imgMeal;
        this.idMeal = idMeal;
        this.idcategory = idcategory;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNameMeal() {
        return nameMeal;
    }

    public void setNameMeal(String nameMeal) {
        this.nameMeal = nameMeal;
    }

    public String getImgMeal() {
        return imgMeal;
    }

    public void setImgMeal(String imgMeal) {
        this.imgMeal = imgMeal;
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
