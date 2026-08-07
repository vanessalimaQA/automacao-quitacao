package com.automation.model;

import java.math.BigDecimal;
import java.util.Objects;

public record InvoicePaymentData(
        Long idConta,
        Long idHistorico,
        Long idBoleto,
        BigDecimal valorTotal,
        BigDecimal valorMinimo
) {

    public InvoicePaymentData {

        Objects.requireNonNull(
                idConta,
                "idConta não pode ser nulo."
        );

        Objects.requireNonNull(
                idHistorico,
                "idHistorico não pode ser nulo."
        );

        Objects.requireNonNull(
                idBoleto,
                "idBoleto não pode ser nulo."
        );

        Objects.requireNonNull(
                valorTotal,
                "valorTotal não pode ser nulo."
        );

        Objects.requireNonNull(
                valorMinimo,
                "valorMinimo não pode ser nulo."
        );

        if (valorTotal.signum() <= 0) {
            throw new IllegalArgumentException(
                    "valorTotal deve ser maior que zero."
            );
        }

        if (valorMinimo.signum() <= 0) {
            throw new IllegalArgumentException(
                    "valorMinimo deve ser maior que zero."
            );
        }

        if (valorMinimo.compareTo(valorTotal) > 0) {
            throw new IllegalArgumentException(
                    "valorMinimo não pode ser maior que valorTotal."
            );
        }
    }
}