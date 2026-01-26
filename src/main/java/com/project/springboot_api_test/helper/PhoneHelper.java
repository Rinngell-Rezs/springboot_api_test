package com.project.springboot_api_test.helper;

public class PhoneHelper {
    public static boolean isValidPhoneNumber(String phoneNumber) {
        String regex = "^\\+\\d{1,3}([ -]?\\d){10}$";
        if(phoneNumber != null && phoneNumber.matches(regex)) {
            return true;
        }
        throw new IllegalArgumentException("Invalid phone number format: " + phoneNumber);
    }
}
