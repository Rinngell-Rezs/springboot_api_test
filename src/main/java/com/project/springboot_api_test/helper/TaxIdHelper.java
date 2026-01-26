package com.project.springboot_api_test.helper;

public class TaxIdHelper {
    public static boolean isValidTaxId(String taxId) {
        String regex = "^[A-Za-zÑñ&]{4}\\d{6}[A-Za-z0-9]{3}$";
        if(taxId != null && taxId.matches(regex)) {
            return true;
        }
        throw new IllegalArgumentException("Invalid tax ID format: " + taxId);
    }
}
