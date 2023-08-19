package com.loginpage.loginapi.domain;

public class User {
    private int userId;
    private String name;
    private String address;
    private String city;
    private String state;
    private String postCode;
    private String phone;
    private String cell;
    private String email;
    private String password;

    public User(int userId, String name, String address, String city, String state,
            String postCode, String phone, String cell, String email, String password) {
        this.userId = userId;
        this.name = name;
        this.address = address;
        this.city = city;
        this.state = state;
        this.postCode = postCode;
        this.phone = phone;
        this.cell = cell;
        this.email = email;
        this.password = password;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCell() {
        return cell;
    }

    public void setCell(String cell) {
        this.cell = cell;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
