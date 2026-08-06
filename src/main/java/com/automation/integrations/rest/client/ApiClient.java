package com.automation.integrations.rest.client;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.Map;

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

    private RequestSpecification request(Map<String, String> headers) {
        return request()
                .headers(headers);
    }

    private RequestSpecification authenticatedRequest(String token) {
        return request()
                .header("Authorization", "Bearer " + token);
    }

    public Response get(String endpoint) {
        return request()
                .when()
                .get(endpoint);
    }

    public Response get(String endpoint, Map<String, String> headers) {
        return request(headers)
                .when()
                .get(endpoint);
    }

    public Response getAuthenticated(String endpoint, String token) {
        return authenticatedRequest(token)
                .when()
                .get(endpoint);
    }

    public Response post(String endpoint, Object body) {
        return request()
                .body(body)
                .when()
                .post(endpoint);
    }

    public Response post(String endpoint,
                         Object body,
                         Map<String, String> headers) {

        return request(headers)
                .body(body)
                .when()
                .post(endpoint);
    }

    public Response postAuthenticated(String endpoint,
                                      Object body,
                                      String token) {

        return authenticatedRequest(token)
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