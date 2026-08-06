package com.automation.integrations.rest.config;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.ResponseSpecification;

public final class ResponseSpecFactory {

    private ResponseSpecFactory() {
    }

    public static ResponseSpecification jsonResponse() {
        return new ResponseSpecBuilder()
                .expectContentType(ContentType.JSON)
                .build();
    }
}