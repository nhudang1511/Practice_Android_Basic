package com.example.exercise1.model;



import com.google.gson.annotations.SerializedName;

import java.io.Serializable;



/*
"user": {
        "id": 2,
        "username": "trung2",
        "fname": "Huu Trung",
        "email": "trung2@gmail.com",
        "gender": "nam",
        "images": "http://app.iotstar.vn/shoppingapp/trung.jpg"
    }
* */

public class User implements Serializable {
    @SerializedName("id")
    private int id;
    @SerializedName("username")
    private String username;
    @SerializedName("fname")
    private String name;
    @SerializedName("email")
    private String email;
    @SerializedName("gender")
    private String gender;
    @SerializedName("images")
    private String images;



    public User(int id, String username, String name, String email, String gender, String images) {
        this.id = id;
        this.username = username;
        this.name = name;
        this.email = email;
        this.gender = gender;
        this.images = images;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getGender() {
        return gender;
    }

    public String getImages() {
        return images;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setImages(String images) {
        this.images = images;
    }

    @Override
    public String toString() {
        return name;
    }
}

