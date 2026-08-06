package com.automation.integrations.rest.config;

public final class ApiConfig {

    private static final String BASE_URL =
            "https://jsonplaceholder.typicode.com";

    private ApiConfig() {
    }

    public static String baseUrl() {
        return BASE_URL;
    }
}