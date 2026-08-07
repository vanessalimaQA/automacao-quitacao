package com.automation.tests.rest;

import com.automation.integrations.rest.client.ApiClient;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Tag;

import static org.assertj.core.api.Assertions.assertThat;

@Tag("external")
class ApiAuthenticationTest {

    @Test
    void deveEnviarBearerTokenComSucesso() {

        ApiClient apiClient =
                new ApiClient("https://httpbin.org");

        String token = "token-automacao-teste";

        Response response =
                apiClient.getAuthenticated(
                        "/bearer",
                        token
                );

        assertThat(response.statusCode())
                .isEqualTo(200);

        assertThat(response.jsonPath().getBoolean("authenticated"))
                .isTrue();

        assertThat(response.jsonPath().getString("token"))
                .isEqualTo(token);
    }
}