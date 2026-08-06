package com.automation.tests.rest;

import com.automation.integrations.rest.client.ApiClient;
import com.automation.integrations.rest.config.ApiConfig;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class ApiParametersTest {

    @Test
    void deveConsultarComQueryParams() {
        ApiClient apiClient =
                new ApiClient(ApiConfig.baseUrl());

        Response response =
                apiClient.getWithQueryParams(
                        "/posts",
                        Map.of("userId", 1)
                );

        assertThat(response.statusCode())
                .isEqualTo(200);

        assertThat(response.jsonPath().getList("$"))
                .isNotEmpty();
    }

    @Test
    void deveConsultarComPathParams() {
        ApiClient apiClient =
                new ApiClient(ApiConfig.baseUrl());

        Response response =
                apiClient.getWithPathParams(
                        "/posts/{id}",
                        Map.of("id", 1)
                );

        assertThat(response.statusCode())
                .isEqualTo(200);

        assertThat(response.jsonPath().getInt("id"))
                .isEqualTo(1);
    }
}