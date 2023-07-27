package com.example.foodapp.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class MessageProduct implements Serializable {
    @SerializedName("success")
    private boolean success;

    @SerializedName("message")
    private String message;

    @SerializedName("result")
    private List<DetailProduct> productDetailList;

    public MessageProduct(boolean success, String message, List<DetailProduct> productDetailList) {
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

    public List<DetailProduct> getProductDetailList() {
        return productDetailList;
    }

    public void setProductDetailList(List<DetailProduct> productDetailList) {
        this.productDetailList = productDetailList;
    }
}
