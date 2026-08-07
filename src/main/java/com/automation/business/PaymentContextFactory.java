package com.automation.business;

import java.math.BigDecimal;

public final class PaymentContextFactory {

    private PaymentContextFactory() {
    }

    public static PaymentContext pagamentoMinimo(
            BigDecimal valorMinimo,
            BigDecimal valorTotal
    ) {
        return new PaymentContext(
                valorMinimo,
                valorTotal,
                PaymentAmountCalculator.pagamentoMinimo(valorMinimo)
        );
    }

    public static PaymentContext menorQueMinimo(
            BigDecimal valorMinimo,
            BigDecimal valorTotal
    ) {
        return new PaymentContext(
                valorMinimo,
                valorTotal,
                PaymentAmountCalculator.menorQueMinimo(valorMinimo)
        );
    }

    public static PaymentContext parcial(
            BigDecimal valorMinimo,
            BigDecimal valorTotal,
            BigDecimal percentual
    ) {
        return new PaymentContext(
                valorMinimo,
                valorTotal,
                PaymentAmountCalculator.parcial(
                        valorMinimo,
                        valorTotal,
                        percentual
                )
        );
    }

    public static PaymentContext pagamentoTotal(
            BigDecimal valorMinimo,
            BigDecimal valorTotal
    ) {
        return new PaymentContext(
                valorMinimo,
                valorTotal,
                PaymentAmountCalculator.pagamentoTotal(valorTotal)
        );
    }

    public static PaymentContext maiorQueTotal(
            BigDecimal valorMinimo,
            BigDecimal valorTotal
    ) {
        return new PaymentContext(
                valorMinimo,
                valorTotal,
                PaymentAmountCalculator.maiorQueTotal(valorTotal)
        );
    }
}