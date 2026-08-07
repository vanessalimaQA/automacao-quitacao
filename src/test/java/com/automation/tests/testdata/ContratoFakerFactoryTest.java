package com.automation.tests.testdata;

import com.automation.model.ContratoData;
import com.automation.testdata.ContratoDataValidator;
import com.automation.testdata.ContratoFakerFactory;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

class ContratoFakerFactoryTest {

    @Test
    void deveGerarContratoValido() {

        ContratoData contrato =
                ContratoFakerFactory.criarContratoValido();

        assertThat(contrato).isNotNull();

        assertThat(contrato.getContrato())
                .startsWith("CTR-");

        assertThat(contrato.getProduto())
                .isIn("596", "597", "598");

        assertThat(contrato.getValorDivida())
                .isPositive();

        assertThat(contrato.getDesconto())
                .isGreaterThanOrEqualTo(0);

        assertThat(contrato.getValorQuitacaoEsperado())
                .isPositive();

        assertThatCode(
                () -> ContratoDataValidator.validar(contrato)
        ).doesNotThrowAnyException();
    }
}