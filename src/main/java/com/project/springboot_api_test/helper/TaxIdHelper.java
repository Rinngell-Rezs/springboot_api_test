package com.project.springboot_api_test.helper;

public class TaxIdHelper {
    public static boolean isValidTaxId(String taxId) {
        String regex = "^[A-Za-zÑñ&]{4}\\d{6}[A-Za-z0-9]{3}$";
        return taxId != null && taxId.matches(regex);
    }
}
