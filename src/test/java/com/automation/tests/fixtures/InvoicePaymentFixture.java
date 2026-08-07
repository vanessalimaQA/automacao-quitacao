package com.automation.tests.fixtures;

import com.automation.model.InvoicePaymentData;

import java.math.BigDecimal;

public final class InvoicePaymentFixture {

    private InvoicePaymentFixture() {
    }

    public static InvoicePaymentData faturaPadrao() {
        return new InvoicePaymentData(
                123456L,
                987654L,
                456789L,
                new BigDecimal("250.00"),
                new BigDecimal("200.00")
        );
    }
}