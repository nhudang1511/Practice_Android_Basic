package com.example.exercise1.model;

import java.io.Serializable;

public class Category implements Serializable {
    private String id;
    private String name;
    private String images;
    private String description;

    public Category(){}

    public Category(String id, String name, String images, String description) {
        this.id = id;
        this.name = name;
        this.images = images;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
