package com.automation.integrations.rest.client;

import com.automation.core.exceptions.ApiException;
import com.automation.integrations.rest.config.RequestSpecFactory;
import com.automation.integrations.rest.config.ResponseSpecFactory;
import io.restassured.filter.log.LogDetail;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class ApiClient {

    private final RequestSpecification requestSpecification;

    public ApiClient(String baseUrl) {
        this.requestSpecification = RequestSpecFactory.create(baseUrl);
    }

    private RequestSpecification request() {
        return given()
                .spec(requestSpecification)
                .log()
                .ifValidationFails(LogDetail.ALL);
    }

    private RequestSpecification request(Map<String, String> headers) {
        return request().headers(headers);
    }

    private RequestSpecification authenticatedRequest(String token) {
        return request()
                .header("Authorization", "Bearer " + token);
    }

    public Response get(String endpoint) {
        return validateResponse(
                request().when().get(endpoint)
        );
    }

    public Response get(String endpoint, Map<String, String> headers) {
        return validateResponse(
                request(headers).when().get(endpoint)
        );
    }

    public Response getWithQueryParams(
            String endpoint,
            Map<String, ?> queryParams
    ) {
        return validateResponse(
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
        return validateResponse(
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
        return validateResponse(
                authenticatedRequest(token)
                        .when()
                        .get(endpoint)
        );
    }

    public Response post(String endpoint, Object body) {
        return validateResponse(
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
        return validateResponse(
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
        return validateResponse(
                authenticatedRequest(token)
                        .body(body)
                        .when()
                        .post(endpoint)
        );
    }

    public Response put(String endpoint, Object body) {
        return validateResponse(
                request()
                        .body(body)
                        .when()
                        .put(endpoint)
        );
    }

    public Response delete(String endpoint) {
        return validateResponse(
                request()
                        .when()
                        .delete(endpoint)
        );
    }

    private Response validateResponse(Response response) {
        int statusCode = response.statusCode();

        if (statusCode >= 400) {
            throw new ApiException(
                    "Erro ao executar requisição REST.",
                    statusCode,
                    response.body().asString()
            );
        }

        return response
                .then()
                .spec(ResponseSpecFactory.jsonResponse())
                .log()
                .ifValidationFails(LogDetail.ALL)
                .extract()
                .response();
    }
}