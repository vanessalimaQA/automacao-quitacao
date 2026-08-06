package com.automation.tests.rest;

import com.automation.integrations.rest.client.ApiClient;
import com.automation.integrations.rest.request.CriarPostRequest;
import com.automation.integrations.rest.response.CriarPostResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CriarPostApiTest {

    @Test
    void deveCriarPostComSucesso() {
        ApiClient apiClient =
                new ApiClient("https://jsonplaceholder.typicode.com");

        CriarPostRequest request = CriarPostRequest.builder()
                .title("Automação de testes")
                .body("Teste de API REST com Java")
                .userId(1)
                .build();

        Response response =
                apiClient.post("/posts", request);

        CriarPostResponse responseBody =
                response.as(CriarPostResponse.class);

        assertThat(response.statusCode())
                .isEqualTo(201);

        assertThat(responseBody.getId())
                .isPositive();

        assertThat(responseBody.getTitle())
                .isEqualTo(request.getTitle());

        assertThat(responseBody.getBody())
                .isEqualTo(request.getBody());

        assertThat(responseBody.getUserId())
                .isEqualTo(request.getUserId());
    }
}