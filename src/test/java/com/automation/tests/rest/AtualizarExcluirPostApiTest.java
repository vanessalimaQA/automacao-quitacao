package com.automation.tests.rest;

import com.automation.integrations.rest.client.ApiClient;
import com.automation.integrations.rest.config.ApiConfig;
import com.automation.integrations.rest.request.CriarPostRequest;
import com.automation.integrations.rest.response.CriarPostResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class AtualizarExcluirPostApiTest {

    @Test
    void deveAtualizarPostComSucesso() {

        ApiClient apiClient =
                new ApiClient(ApiConfig.baseUrl());

        CriarPostRequest request = CriarPostRequest.builder()
                .title("Post atualizado")
                .body("Conteúdo atualizado via PUT")
                .userId(1)
                .build();

        Response response =
                apiClient.put("/posts/1", request);

        CriarPostResponse responseBody =
                response.as(CriarPostResponse.class);

        assertThat(response.statusCode())
                .isEqualTo(200);

        assertThat(responseBody.getId())
                .isEqualTo(1);

        assertThat(responseBody.getTitle())
                .isEqualTo(request.getTitle());

        assertThat(responseBody.getBody())
                .isEqualTo(request.getBody());

        assertThat(responseBody.getUserId())
                .isEqualTo(request.getUserId());
    }

    @Test
    void deveExcluirPostComSucesso() {

        ApiClient apiClient =
                new ApiClient(ApiConfig.baseUrl());

        Response response =
                apiClient.delete("/posts/1");

        assertThat(response.statusCode())
                .isEqualTo(200);

        assertThat(response.body().asString())
                .isEqualTo("{}");
    }
}