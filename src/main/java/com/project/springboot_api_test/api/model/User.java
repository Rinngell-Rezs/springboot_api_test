package com.project.springboot_api_test.api.model;

import java.util.HashMap;

public class User {
    private int id;
    private String email;
    private String name;
    private String phone;
    private String password;
    private String tax_id;
    private String created_at;
    private HashMap<Integer, Address> addresses;

    public User(int id, String email, String name, String phone, String password, String tax_id, String created_at, HashMap<Integer, Address> addresses) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.phone = phone;
        this.password = password;
        this.tax_id = tax_id;
        this.created_at = created_at;
        this.addresses = addresses;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public String getTax_id() {
        return tax_id;
    }
    public void setTax_id(String tax_id) {
        this.tax_id = tax_id;
    }

    public String getCreated_at() {
        return created_at;
    }
    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public HashMap<Integer, Address> getAddresses() {
        return addresses;
    }
    public void setAddresses(HashMap<Integer, Address> addresses) {
        this.addresses = addresses;
    }


}
