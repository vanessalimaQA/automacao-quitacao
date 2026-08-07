package com.automation.business;

import com.automation.model.InvoicePaymentData;

import java.math.BigDecimal;

public final class PaymentScenarioResolver {

    private PaymentScenarioResolver() {
    }

    public static PaymentContext resolver(
            InvoicePaymentData invoice,
            PaymentScenario scenario
    ) {
        return switch (scenario) {

            case BELOW_MINIMUM ->
                    InvoicePaymentContextFactory.menorQueMinimo(invoice);

            case MINIMUM ->
                    InvoicePaymentContextFactory.pagamentoMinimo(invoice);

            case PARTIAL_20 ->
                    InvoicePaymentContextFactory.parcial(
                            invoice,
                            new BigDecimal("0.20")
                    );

            case PARTIAL_60 ->
                    InvoicePaymentContextFactory.parcial(
                            invoice,
                            new BigDecimal("0.60")
                    );

            case TOTAL ->
                    InvoicePaymentContextFactory.pagamentoTotal(invoice);

            case ABOVE_TOTAL ->
                    InvoicePaymentContextFactory.maiorQueTotal(invoice);
        };
    }
}