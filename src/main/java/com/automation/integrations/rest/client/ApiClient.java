package com.automation.integrations.rest.client;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class ApiClient {

    private final String baseUrl;

    public ApiClient(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    private RequestSpecification request() {
        return given()
                .baseUri(baseUrl)
                .contentType("application/json")
                .accept("application/json");
    }

    public Response get(String endpoint) {
        return request()
                .when()
                .get(endpoint);
    }

    public Response post(String endpoint, Object body) {
        return request()
                .body(body)
                .when()
                .post(endpoint);
    }

    public Response put(String endpoint, Object body) {
        return request()
                .body(body)
                .when()
                .put(endpoint);
    }

    public Response delete(String endpoint) {
        return request()
                .when()
                .delete(endpoint);
    }
}