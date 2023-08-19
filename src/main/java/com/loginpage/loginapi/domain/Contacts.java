package com.loginpage.loginapi.domain;

public class Contacts {
    private int id;
    private int user_id;
    private String name;
    private String address;
    private String city;
    private String state;
    private String postcode;
    private String phone;
    private String cell;
    private String email;

    public Contacts(int contactId, int userId, String name, String address, String city, String state,
            String postcode, String phone, String cell, String email) {
        this.id = contactId;
        this.user_id = userId;
        this.name = name;
        this.address = address;
        this.city = city;
        this.state = state;
        this.postcode = postcode;
        this.phone = phone;
        this.cell = cell;
        this.email = email;
    }

    // Getters
    public int getId() {
        return id;
    }

    public int getUserId() {
        return user_id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getPostcode() {
        return postcode;
    }

    public String getPhone() {
        return phone;
    }

    public String getCell() {
        return cell;
    }

    public String getEmail() {
        return email;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setUserId(int user_id) {
        this.user_id = user_id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setCell(String cell) {
        this.cell = cell;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // Other contact-related getters and setters
}
