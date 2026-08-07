package com.automation.business;

import java.math.BigDecimal;
import java.util.Objects;

public record PaymentInstruction(
        Long idConta,
        Long idHistorico,
        Long idBoleto,
        PaymentScenario scenario,
        BigDecimal valorPagamento
) {

    public PaymentInstruction {

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
                scenario,
                "scenario não pode ser nulo."
        );

        Objects.requireNonNull(
                valorPagamento,
                "valorPagamento não pode ser nulo."
        );

        if (valorPagamento.signum() <= 0) {
            throw new IllegalArgumentException(
                    "valorPagamento deve ser maior que zero."
            );
        }
    }
}