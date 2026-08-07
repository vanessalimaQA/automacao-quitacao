package com.automation.business;

import java.math.BigDecimal;
import java.math.RoundingMode;

public final class PaymentAmountCalculator {

    private PaymentAmountCalculator() {
    }

    public static BigDecimal pagamentoMinimo(
            BigDecimal valorMinimo
    ) {
        validarValorPositivo(valorMinimo, "valorMinimo");
        return valorMinimo.setScale(2, RoundingMode.HALF_UP);
    }

    public static BigDecimal menorQueMinimo(
            BigDecimal valorMinimo
    ) {
        validarValorPositivo(valorMinimo, "valorMinimo");

        return valorMinimo
                .multiply(BigDecimal.valueOf(0.50))
                .setScale(2, RoundingMode.HALF_UP);
    }

    public static BigDecimal parcial(
            BigDecimal valorMinimo,
            BigDecimal valorTotal,
            BigDecimal percentual
    ) {
        validarFaixa(valorMinimo, valorTotal);
        validarPercentual(percentual);

        BigDecimal diferenca =
                valorTotal.subtract(valorMinimo);

        return valorMinimo
                .add(diferenca.multiply(percentual))
                .setScale(2, RoundingMode.HALF_UP);
    }

    public static BigDecimal pagamentoTotal(
            BigDecimal valorTotal
    ) {
        validarValorPositivo(valorTotal, "valorTotal");
        return valorTotal.setScale(2, RoundingMode.HALF_UP);
    }

    public static BigDecimal maiorQueTotal(
            BigDecimal valorTotal
    ) {
        validarValorPositivo(valorTotal, "valorTotal");

        BigDecimal acrescimo =
                valorTotal.multiply(BigDecimal.valueOf(0.10));

        return valorTotal
                .add(acrescimo)
                .setScale(2, RoundingMode.HALF_UP);
    }

    private static void validarFaixa(
            BigDecimal valorMinimo,
            BigDecimal valorTotal
    ) {
        validarValorPositivo(valorMinimo, "valorMinimo");
        validarValorPositivo(valorTotal, "valorTotal");

        if (valorTotal.compareTo(valorMinimo) <= 0) {
            throw new IllegalArgumentException(
                    "Valor total deve ser maior que o valor mínimo."
            );
        }
    }

    private static void validarPercentual(
            BigDecimal percentual
    ) {
        if (percentual == null
                || percentual.compareTo(BigDecimal.ZERO) <= 0
                || percentual.compareTo(BigDecimal.ONE) >= 0) {
            throw new IllegalArgumentException(
                    "Percentual deve estar entre 0 e 1."
            );
        }
    }

    private static void validarValorPositivo(
            BigDecimal valor,
            String campo
    ) {
        if (valor == null
                || valor.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException(
                    campo + " deve ser maior que zero."
            );
        }
    }
}