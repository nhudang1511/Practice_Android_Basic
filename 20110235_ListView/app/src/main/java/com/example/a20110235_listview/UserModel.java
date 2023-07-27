package com.example.a20110235_listview;

public class UserModel {
    private String name;
    private String address;

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public UserModel(String name, String address) {
        this.name = name;
        this.address = address;
    }
}
