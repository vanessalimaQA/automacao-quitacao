package com.automation.tests.web;

import com.automation.core.BaseTest;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BrowserSmokeTest extends BaseTest {

    @Test
    void deveAbrirNavegadorComSucesso() {

        page.navigate("https://example.com");

        assertThat(page.title())
                .isEqualTo("Example Domain");
    }
}