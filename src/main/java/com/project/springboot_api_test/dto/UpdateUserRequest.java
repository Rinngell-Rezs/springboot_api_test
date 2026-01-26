package com.project.springboot_api_test.dto;

public class UpdateUserRequest {
    private Attribute attribute;
    private String new_value;

    public UpdateUserRequest( String attribute, String new_value) {
        try{
            this.attribute = new Attribute(Attribute.keyValue.valueOf(attribute.toUpperCase()));
            this.new_value = new_value;
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid key value: " + attribute);
        }
    }

    public Attribute getAttribute() {
        return attribute;
    }

    public void setAttribute(Attribute attribute) {
        this.attribute = attribute;
    }

    public String getNew_value() {
        return new_value;
    }

    public void setNew_value(String new_value) {
        this.new_value = new_value;
    }
}
