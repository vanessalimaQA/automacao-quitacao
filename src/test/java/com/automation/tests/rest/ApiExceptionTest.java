package com.automation.tests.rest;

import com.automation.core.exceptions.ApiException;
import com.automation.integrations.rest.client.ApiClient;
import com.automation.integrations.rest.config.ApiConfig;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ApiExceptionTest {

    @Test
    void deveLancarExcecaoQuandoApiRetornarErro() {
        ApiClient apiClient =
                new ApiClient(ApiConfig.baseUrl());

        assertThatThrownBy(() ->
                apiClient.get("/rota-inexistente")
        )
                .isInstanceOf(ApiException.class)
                .hasMessageContaining("Erro ao executar requisição REST");
    }
}