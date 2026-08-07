package com.automation.tests.business;

import com.automation.business.PaymentContext;
import com.automation.business.PaymentScenario;
import com.automation.business.PaymentScenarioResolver;
import com.automation.model.InvoicePaymentData;
import com.automation.tests.fixtures.InvoicePaymentFixture;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PaymentScenarioResolverTest {

    private final InvoicePaymentData invoice =
            InvoicePaymentFixture.faturaPadrao();

    @Test
    void deveResolverPagamentoAbaixoDoMinimo() {

        PaymentContext context =
                PaymentScenarioResolver.resolver(
                        invoice,
                        PaymentScenario.BELOW_MINIMUM
                );

        assertThat(context.getValorPagamento())
                .isEqualByComparingTo("100.00");
    }

    @Test
    void deveResolverPagamentoMinimo() {

        PaymentContext context =
                PaymentScenarioResolver.resolver(
                        invoice,
                        PaymentScenario.MINIMUM
                );

        assertThat(context.getValorPagamento())
                .isEqualByComparingTo("200.00");
    }

    @Test
    void deveResolverPagamentoParcialVintePorCento() {

        PaymentContext context =
                PaymentScenarioResolver.resolver(
                        invoice,
                        PaymentScenario.PARTIAL_20
                );

        assertThat(context.getValorPagamento())
                .isEqualByComparingTo("210.00");
    }

    @Test
    void deveResolverPagamentoParcialSessentaPorCento() {

        PaymentContext context =
                PaymentScenarioResolver.resolver(
                        invoice,
                        PaymentScenario.PARTIAL_60
                );

        assertThat(context.getValorPagamento())
                .isEqualByComparingTo("230.00");
    }

    @Test
    void deveResolverPagamentoTotal() {

        PaymentContext context =
                PaymentScenarioResolver.resolver(
                        invoice,
                        PaymentScenario.TOTAL
                );

        assertThat(context.getValorPagamento())
                .isEqualByComparingTo("250.00");
    }

    @Test
    void deveResolverPagamentoAcimaDoTotal() {

        PaymentContext context =
                PaymentScenarioResolver.resolver(
                        invoice,
                        PaymentScenario.ABOVE_TOTAL
                );

        assertThat(context.getValorPagamento())
                .isEqualByComparingTo("275.00");
    }
}