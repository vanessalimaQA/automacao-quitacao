package com.automation.model;

import java.math.BigDecimal;
import java.util.Objects;

public record BoletoQuitacao(
        Long idBoleto,
        Long idConta,
        BigDecimal valor,
        String status
) {

    public BoletoQuitacao {
        Objects.requireNonNull(
                idBoleto,
                "O IdBoleto nÃ£o pode ser nulo."
        );

        Objects.requireNonNull(
                idConta,
                "O IdConta nÃ£o pode ser nulo."
        );

        Objects.requireNonNull(
                valor,
                "O valor do boleto nÃ£o pode ser nulo."
        );

        if (valor.signum() <= 0) {
            throw new IllegalArgumentException(
                    "O valor do boleto deve ser maior que zero."
            );
        }

        if (status == null || status.isBlank()) {
            throw new IllegalArgumentException(
                    "O status do boleto nÃ£o pode ser vazio."
            );
        }

        status = status.trim().toUpperCase();
    }

    public boolean estaPago() {
        return "PAGO".equals(status);
    }

    public boolean estaPendente() {
        return "PENDENTE".equals(status);
    }
}
