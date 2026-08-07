package com.automation.business;

import java.math.BigDecimal;

public class PaymentContext {

    private final BigDecimal valorMinimo;
    private final BigDecimal valorTotal;
    private final BigDecimal valorPagamento;

    public PaymentContext(
            BigDecimal valorMinimo,
            BigDecimal valorTotal,
            BigDecimal valorPagamento
    ) {
        this.valorMinimo = valorMinimo;
        this.valorTotal = valorTotal;
        this.valorPagamento = valorPagamento;
    }

    public BigDecimal getValorMinimo() {
        return valorMinimo;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public BigDecimal getValorPagamento() {
        return valorPagamento;
    }
}