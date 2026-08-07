package com.automation.tests;

import com.automation.config.EnvironmentConfig;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class EnvironmentConfigTest {

    @Test
    void deveCarregarAmbienteEBaseUrl() {

        String environment =
                EnvironmentConfig.getEnvironment();

        String baseUrl =
                EnvironmentConfig.getBaseUrl();

        System.out.println("ENVIRONMENT = " + environment);
        System.out.println("BASE_URL    = " + baseUrl);

        assertThat(environment)
                .isEqualTo("hml");

        assertThat(baseUrl)
                .isNotNull()
                .isNotBlank()
                .startsWith("file:///");
    }
}