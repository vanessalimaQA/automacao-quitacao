package com.automation.tests.business;

import com.automation.business.PaymentContext;
import com.automation.business.PaymentContextFactory;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

class PaymentContextFactoryTest {

    private static final BigDecimal MINIMO =
            new BigDecimal("200.00");

    private static final BigDecimal TOTAL =
            new BigDecimal("250.00");

    @Test
    void deveCriarContextoParaPagamentoMinimo() {

        PaymentContext context =
                PaymentContextFactory.pagamentoMinimo(
                        MINIMO,
                        TOTAL
                );

        assertThat(context.getValorPagamento())
                .isEqualByComparingTo("200.00");
    }

    @Test
    void deveCriarContextoParaPagamentoMenorQueMinimo() {

        PaymentContext context =
                PaymentContextFactory.menorQueMinimo(
                        MINIMO,
                        TOTAL
                );

        assertThat(context.getValorPagamento())
                .isEqualByComparingTo("100.00");
    }

    @Test
    void deveCriarContextoParaPagamentoParcialVintePorCento() {

        PaymentContext context =
                PaymentContextFactory.parcial(
                        MINIMO,
                        TOTAL,
                        new BigDecimal("0.20")
                );

        assertThat(context.getValorPagamento())
                .isEqualByComparingTo("210.00");
    }

    @Test
    void deveCriarContextoParaPagamentoParcialSessentaPorCento() {

        PaymentContext context =
                PaymentContextFactory.parcial(
                        MINIMO,
                        TOTAL,
                        new BigDecimal("0.60")
                );

        assertThat(context.getValorPagamento())
                .isEqualByComparingTo("230.00");
    }

    @Test
    void deveCriarContextoParaPagamentoTotal() {

        PaymentContext context =
                PaymentContextFactory.pagamentoTotal(
                        MINIMO,
                        TOTAL
                );

        assertThat(context.getValorPagamento())
                .isEqualByComparingTo("250.00");
    }

    @Test
    void deveCriarContextoParaPagamentoMaiorQueTotal() {

        PaymentContext context =
                PaymentContextFactory.maiorQueTotal(
                        MINIMO,
                        TOTAL
                );

        assertThat(context.getValorPagamento())
                .isEqualByComparingTo("275.00");
    }
}