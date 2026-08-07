package com.automation.tests.business;

import com.automation.business.PaymentAmountCalculator;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

class PaymentAmountCalculatorTest {

    private static final BigDecimal MINIMO =
            new BigDecimal("200.00");

    private static final BigDecimal TOTAL =
            new BigDecimal("250.00");

    @Test
    void deveCalcularPagamentoMinimo() {

        BigDecimal resultado =
                PaymentAmountCalculator.pagamentoMinimo(MINIMO);

        assertThat(resultado)
                .isEqualByComparingTo("200.00");
    }

    @Test
    void deveCalcularPagamentoMenorQueMinimo() {

        BigDecimal resultado =
                PaymentAmountCalculator.menorQueMinimo(MINIMO);

        assertThat(resultado)
                .isEqualByComparingTo("100.00");
    }

    @Test
    void deveCalcularPagamentoParcialVintePorCento() {

        BigDecimal resultado =
                PaymentAmountCalculator.parcial(
                        MINIMO,
                        TOTAL,
                        new BigDecimal("0.20")
                );

        assertThat(resultado)
                .isEqualByComparingTo("210.00");
    }

    @Test
    void deveCalcularPagamentoParcialSessentaPorCento() {

        BigDecimal resultado =
                PaymentAmountCalculator.parcial(
                        MINIMO,
                        TOTAL,
                        new BigDecimal("0.60")
                );

        assertThat(resultado)
                .isEqualByComparingTo("230.00");
    }

    @Test
    void deveCalcularPagamentoTotal() {

        BigDecimal resultado =
                PaymentAmountCalculator.pagamentoTotal(TOTAL);

        assertThat(resultado)
                .isEqualByComparingTo("250.00");
    }

    @Test
    void deveCalcularPagamentoMaiorQueTotal() {

        BigDecimal resultado =
                PaymentAmountCalculator.maiorQueTotal(TOTAL);

        assertThat(resultado)
                .isEqualByComparingTo("275.00");
    }
}