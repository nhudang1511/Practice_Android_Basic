package com.example.exercise1.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UploadMessage {
    private boolean success;
    private String message;
    @SerializedName("result")
    private List<User> user;

    public UploadMessage() { }

    public UploadMessage(boolean success, String message, List<User> user) {
        this.success = success;
        this.message = message;
        this.user = user;
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

    public List<User> getUser() {
        return user;
    }

    public void setUser(List<User> user) {
        this.user = user;
    }
}
