package com.automation.tests.rest;

import com.automation.integrations.rest.client.ApiClient;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Tag;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@Tag("external")
class ApiHeadersTest {

    @Test
    void deveEnviarHeadersPersonalizados() {

        ApiClient apiClient =
                new ApiClient("https://httpbin.org");

        Map<String, String> headers = Map.of(
                "X-Test-Environment", "hml",
                "X-Automation-Framework", "java-rest-assured"
        );

        Response response =
                apiClient.get("/headers", headers);

        assertThat(response.statusCode())
                .isEqualTo(200);

        assertThat(
                response.jsonPath()
                        .getString("headers.X-Test-Environment")
        ).isEqualTo("hml");

        assertThat(
                response.jsonPath()
                        .getString("headers.X-Automation-Framework")
        ).isEqualTo("java-rest-assured");
    }
}