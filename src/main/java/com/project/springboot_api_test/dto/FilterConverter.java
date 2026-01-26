package com.project.springboot_api_test.dto;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import static com.project.springboot_api_test.dto.Filter.*;

@Component
public class FilterConverter implements Converter<String, Filter> {

    @Override
    public Filter convert(String params) {
        String[] parts = params.split(" ", 3);
        if (parts.length != 3) {
            throw new IllegalArgumentException("Invalid parameter format. Expected format: [email|id|name|phone|tax_id|created_at] : [co|eq|sw|ew] : [value] ");
        }

        keyValue key;
        comparisonValue comparison;

        try {
            key = keyValue.valueOf(parts[0].toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid key value: " + parts[0]);
        }

        try {
            comparison = comparisonValue.valueOf(parts[1].toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid comparison value: " + parts[1]);
        }

        String query = parts[2];

        return new Filter(key, comparison, query);
    }

}
