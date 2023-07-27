package com.example.exercise1.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class DetailMessage implements Serializable {
    private boolean success;
    private String message;
    @SerializedName("result")
    private List<DetailProduct> detailProduct;


    public DetailMessage(boolean success, String message, List<DetailProduct> detailProduct) {
        this.success = success;
        this.message = message;
        this.detailProduct = detailProduct;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<DetailProduct> getDetailProduct() {
        return detailProduct;
    }

    public void setDetailProduct(List<DetailProduct> detailProduct) {
        this.detailProduct = detailProduct;
    }
}
