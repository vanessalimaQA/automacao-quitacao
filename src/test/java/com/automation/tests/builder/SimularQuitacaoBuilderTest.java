package com.automation.tests.builder;

import com.automation.integrations.soap.builder.SimularQuitacaoBuilder;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SimularQuitacaoBuilderTest {

    @Test
    void deveMontarRequestComIdContaInformado() {

        SimularQuitacaoBuilder builder =
                new SimularQuitacaoBuilder();

        String xml = builder.montarRequest("2310664");

        assertThat(xml)
                .contains("<con:IdConta>2310664</con:IdConta>")
                .doesNotContain("${idConta}");
    }
}
