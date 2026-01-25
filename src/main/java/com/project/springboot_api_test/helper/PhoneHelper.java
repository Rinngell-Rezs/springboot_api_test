package com.project.springboot_api_test.helper;

public class PhoneHelper {
    public static boolean isValidPhoneNumber(String phoneNumber) {
        String regex = "^\\+\\d{1,3}([ -]?\\d){10}$";
        return phoneNumber != null && phoneNumber.matches(regex);
    }
}
