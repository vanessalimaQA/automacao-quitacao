package com.automation.integrations.rest.client;

import com.automation.integrations.rest.config.RequestSpecFactory;
import io.restassured.filter.log.LogDetail;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class ApiClient {

    private final RequestSpecification requestSpecification;

    public ApiClient(String baseUrl) {
        this.requestSpecification =
                RequestSpecFactory.create(baseUrl);
    }

    private RequestSpecification request() {
        return given()
                .spec(requestSpecification)
                .log()
                .ifValidationFails(LogDetail.ALL);
    }

    private RequestSpecification request(
            Map<String, String> headers
    ) {
        return request()
                .headers(headers);
    }

    private RequestSpecification authenticatedRequest(
            String token
    ) {
        return request()
                .header(
                        "Authorization",
                        "Bearer " + token
                );
    }

    public Response get(String endpoint) {
        return extractResponse(
                request()
                        .when()
                        .get(endpoint)
        );
    }

    public Response get(
            String endpoint,
            Map<String, String> headers
    ) {
        return extractResponse(
                request(headers)
                        .when()
                        .get(endpoint)
        );
    }

    public Response getWithQueryParams(
            String endpoint,
            Map<String, ?> queryParams
    ) {
        return extractResponse(
                request()
                        .queryParams(queryParams)
                        .when()
                        .get(endpoint)
        );
    }

    public Response getWithPathParams(
            String endpoint,
            Map<String, ?> pathParams
    ) {
        return extractResponse(
                request()
                        .pathParams(pathParams)
                        .when()
                        .get(endpoint)
        );
    }

    public Response getAuthenticated(
            String endpoint,
            String token
    ) {
        return extractResponse(
                authenticatedRequest(token)
                        .when()
                        .get(endpoint)
        );
    }

    public Response post(
            String endpoint,
            Object body
    ) {
        return extractResponse(
                request()
                        .body(body)
                        .when()
                        .post(endpoint)
        );
    }

    public Response post(
            String endpoint,
            Object body,
            Map<String, String> headers
    ) {
        return extractResponse(
                request(headers)
                        .body(body)
                        .when()
                        .post(endpoint)
        );
    }

    public Response postAuthenticated(
            String endpoint,
            Object body,
            String token
    ) {
        return extractResponse(
                authenticatedRequest(token)
                        .body(body)
                        .when()
                        .post(endpoint)
        );
    }

    public Response put(
            String endpoint,
            Object body
    ) {
        return extractResponse(
                request()
                        .body(body)
                        .when()
                        .put(endpoint)
        );
    }

    public Response delete(String endpoint) {
        return extractResponse(
                request()
                        .when()
                        .delete(endpoint)
        );
    }

    private Response extractResponse(
            Response response
    ) {
        return response
                .then()
                .log()
                .ifValidationFails(LogDetail.ALL)
                .extract()
                .response();
    }
}