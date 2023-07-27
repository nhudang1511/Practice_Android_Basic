package com.example.a20110235_nhu_tuan8;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ImageUpLoad {

    private Boolean success;
    private String message;
    @SerializedName("result")
    private User user;

    public ImageUpLoad(Boolean success, String message, User user) {
        this.success = success;
        this.message = message;
        this.user = user;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
