package com.project.springboot_api_test.dto;

import com.project.springboot_api_test.api.model.User;

import java.util.function.Function;

public class Filter {
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

    public enum comparisonValue {
        CO, // contains
        EQ, // equals
        SW, // starts with
        EW; // ends with
    }

    private final keyValue key;
    private final comparisonValue comparison;
    private final  String query;

    public Filter(keyValue key, comparisonValue comparison, String query) {
        this.key = key;
        this.comparison = comparison;
        this.query = query;
    }

    public keyValue getKey() {
        return key;
    }
    public comparisonValue getComparison() {
        return comparison;
    }
    public String getQuery() {
        return query;
    }
}
