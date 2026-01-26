package com.project.springboot_api_test.dto;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class AttributeConverter implements Converter<String, Attribute> {
    @Override
    public Attribute convert(String param) {
        Attribute.keyValue key;
        try {
            key = Attribute.keyValue.valueOf(param.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid key value: " + param);
        }

        return new Attribute(key);
    }
}
