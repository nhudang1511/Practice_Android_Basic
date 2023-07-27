package com.example.retrofitloginsignup.Model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class UserLogin implements Serializable {

    @SerializedName("error")
    private Boolean error;

    @SerializedName("message")
    private String message;

    @SerializedName("user")
    private User user;

    @SerializedName("result")
    private User result;

    public UserLogin(Boolean error, String message, User user, User result) {
        this.error = error;
        this.message = message;
        this.user = user;
        this.result = result;
    }


    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
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

    public User getResult() {
        return result;
    }

    public void setResult(User result) {
        this.result = result;
    }
}
