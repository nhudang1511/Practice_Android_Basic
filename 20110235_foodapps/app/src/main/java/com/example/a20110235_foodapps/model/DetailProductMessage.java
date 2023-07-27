package com.example.a20110235_foodapps.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DetailProductMessage {
    @SerializedName("success")
    private boolean success;

    @SerializedName("message")
    private String message;

    @SerializedName("result")
    private List<DetailProductModel> productDetailList;

    public DetailProductMessage(boolean success, String message, List<DetailProductModel> productDetailList) {
        this.success = success;
        this.message = message;
        this.productDetailList = productDetailList;
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

    public List<DetailProductModel> getProductDetailList() {
        return productDetailList;
    }

    public void setProductDetailList(List<DetailProductModel> productDetailList) {
        this.productDetailList = productDetailList;
    }
}
