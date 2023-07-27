package com.example.a20110235_foodapps.model;

import com.google.gson.annotations.SerializedName;

public class DetailProductModel {
    @SerializedName("id")
    private String id;

    @SerializedName("meal")
    private String meal;

    @SerializedName("area")
    private String area;

    @SerializedName("category")
    private String category;

    @SerializedName("instructions")
    private String instructions;

    @SerializedName("strmealthumb")
    private String imageProduct;

    @SerializedName("price")
    private String price;

    public DetailProductModel(String id, String meal, String area, String category, String instructions, String imageProduct, String price) {
        this.id = id;
        this.meal = meal;
        this.area = area;
        this.category = category;
        this.instructions = instructions;
        this.imageProduct = imageProduct;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMeal() {
        return meal;
    }

    public void setMeal(String meal) {
        this.meal = meal;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public String getImageProduct() {
        return imageProduct;
    }

    public void setImageProduct(String imageProduct) {
        this.imageProduct = imageProduct;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
