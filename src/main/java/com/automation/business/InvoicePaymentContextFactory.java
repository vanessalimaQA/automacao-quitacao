package com.automation.business;

import com.automation.model.InvoicePaymentData;

import java.math.BigDecimal;

public final class InvoicePaymentContextFactory {

    private InvoicePaymentContextFactory() {
    }

    public static PaymentContext pagamentoMinimo(
            InvoicePaymentData invoice
    ) {
        return PaymentContextFactory.pagamentoMinimo(
                invoice.valorMinimo(),
                invoice.valorTotal()
        );
    }

    public static PaymentContext menorQueMinimo(
            InvoicePaymentData invoice
    ) {
        return PaymentContextFactory.menorQueMinimo(
                invoice.valorMinimo(),
                invoice.valorTotal()
        );
    }

    public static PaymentContext parcial(
            InvoicePaymentData invoice,
            BigDecimal percentual
    ) {
        return PaymentContextFactory.parcial(
                invoice.valorMinimo(),
                invoice.valorTotal(),
                percentual
        );
    }

    public static PaymentContext pagamentoTotal(
            InvoicePaymentData invoice
    ) {
        return PaymentContextFactory.pagamentoTotal(
                invoice.valorMinimo(),
                invoice.valorTotal()
        );
    }

    public static PaymentContext maiorQueTotal(
            InvoicePaymentData invoice
    ) {
        return PaymentContextFactory.maiorQueTotal(
                invoice.valorMinimo(),
                invoice.valorTotal()
        );
    }
}