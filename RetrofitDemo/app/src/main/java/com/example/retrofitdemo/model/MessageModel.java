package com.example.retrofitdemo.model;

import java.io.Serializable;

public class MessageModel  implements Serializable {
    private boolean error;
    private String message;
    private UserModel user;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }
}
