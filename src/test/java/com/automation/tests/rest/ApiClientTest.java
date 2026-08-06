package com.automation.tests.rest;

import com.automation.integrations.rest.client.ApiClient;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ApiClientTest {

    @Test
    void deveRealizarGetComSucesso() {
        ApiClient apiClient =
                new ApiClient("https://jsonplaceholder.typicode.com");

        Response response =
                apiClient.get("/users/1");

        assertThat(response.statusCode())
                .isEqualTo(200);

        assertThat(response.jsonPath().getInt("id"))
                .isEqualTo(1);

        assertThat(response.jsonPath().getString("name"))
                .isNotBlank();
    }
}