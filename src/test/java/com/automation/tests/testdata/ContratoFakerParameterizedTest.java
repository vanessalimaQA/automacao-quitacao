package com.automation.tests.testdata;

import com.automation.model.ContratoData;
import com.automation.testdata.ContratoDataValidator;
import com.automation.testdata.ContratoFakerFactory;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThatCode;

class ContratoFakerParameterizedTest {

    static Stream<ContratoData> contratosValidos() {
        return ContratoFakerFactory
                .criarContratosValidos(10)
                .stream();
    }

    @ParameterizedTest(name = "Contrato dinâmico: {0}")
    @MethodSource("contratosValidos")
    void deveValidarContratosGeradosDinamicamente(
            ContratoData contrato
    ) {
        assertThatCode(
                () -> ContratoDataValidator.validar(contrato)
        ).doesNotThrowAnyException();
    }
}