package com.project.springboot_api_test.dto;

public class LoginRequest {
    private String tax_id;
    private String password;

    public LoginRequest(String tax_id, String password) {
        this.tax_id = tax_id;
        this.password = password;
    }

    public String getTax_id() {
        return tax_id;
    }

    public void setTax_id(String tax_id) {
        this.tax_id = tax_id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
