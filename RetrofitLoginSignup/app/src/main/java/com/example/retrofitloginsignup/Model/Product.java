package com.example.retrofitloginsignup.Model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.lang.annotation.Annotation;

public class Product implements Serializable {

    @SerializedName("id")
    private int id;

    @SerializedName("strMeal")
    private String name;

    @SerializedName("strMealThumb")
    private String images;

    @SerializedName("idcategory")
    private String idCategory;

    public Product(int id, String name, String images, String idCategory) {
        this.id = id;
        this.name = name;
        this.images = images;
        this.idCategory = idCategory;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public String getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(String idCategory) {
        this.idCategory = idCategory;
    }


}
