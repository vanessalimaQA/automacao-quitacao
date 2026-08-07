package com.automation.tests.business;

import com.automation.business.InvoicePaymentContextFactory;
import com.automation.business.PaymentContext;
import com.automation.model.InvoicePaymentData;
import com.automation.tests.fixtures.InvoicePaymentFixture;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

class InvoicePaymentContextFactoryTest {

    private final InvoicePaymentData invoice =
            InvoicePaymentFixture.faturaPadrao();

    @Test
    void deveCriarPagamentoMinimo() {

        PaymentContext context =
                InvoicePaymentContextFactory.pagamentoMinimo(invoice);

        assertThat(context.getValorPagamento())
                .isEqualByComparingTo("200.00");
    }

    @Test
    void deveCriarPagamentoMenorQueMinimo() {

        PaymentContext context =
                InvoicePaymentContextFactory.menorQueMinimo(invoice);

        assertThat(context.getValorPagamento())
                .isEqualByComparingTo("100.00");
    }

    @Test
    void deveCriarPagamentoParcialVintePorCento() {

        PaymentContext context =
                InvoicePaymentContextFactory.parcial(
                        invoice,
                        new BigDecimal("0.20")
                );

        assertThat(context.getValorPagamento())
                .isEqualByComparingTo("210.00");
    }

    @Test
    void deveCriarPagamentoParcialSessentaPorCento() {

        PaymentContext context =
                InvoicePaymentContextFactory.parcial(
                        invoice,
                        new BigDecimal("0.60")
                );

        assertThat(context.getValorPagamento())
                .isEqualByComparingTo("230.00");
    }

    @Test
    void deveCriarPagamentoTotal() {

        PaymentContext context =
                InvoicePaymentContextFactory.pagamentoTotal(invoice);

        assertThat(context.getValorPagamento())
                .isEqualByComparingTo("250.00");
    }

    @Test
    void deveCriarPagamentoMaiorQueTotal() {

        PaymentContext context =
                InvoicePaymentContextFactory.maiorQueTotal(invoice);

        assertThat(context.getValorPagamento())
                .isEqualByComparingTo("275.00");
    }
}