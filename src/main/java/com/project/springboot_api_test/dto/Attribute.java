package com.project.springboot_api_test.dto;

import com.project.springboot_api_test.api.model.User;

import java.util.function.Function;

public class Attribute {
    public enum keyValue {
        EMAIL(User::getEmail),
        ID(User::getId),
        NAME(User::getName),
        PHONE(User::getPhone),
        TAX_ID(User::getTax_id),
        CREATED_AT(User::getCreated_at);

        private final Function<User, String> extractor;

        keyValue(Function<User, String> extractor) {
            this.extractor = extractor;
        }

        public String extract(User user) {
            return extractor.apply(user);
        }
    }

    private final Attribute.keyValue key;

    public Attribute(Attribute.keyValue key) {
        this.key = key;
    }

    public Attribute.keyValue getKey() {
        return key;
    }
}
