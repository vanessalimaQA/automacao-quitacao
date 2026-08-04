
package com.automation.validator;

import com.automation.model.BoletoQuitacao;

import java.math.BigDecimal;
import java.util.Objects;

public final class BoletoQuitacaoValidator {

    private BoletoQuitacaoValidator() {
        // Impede instanciação.
    }

    public static void validar(BoletoQuitacao boleto) {
        Objects.requireNonNull(
                boleto,
                "O boleto não pode ser nulo."
        );

        validarIdBoleto(boleto.idBoleto());
        validarIdConta(boleto.idConta());
        validarValor(boleto.valor());
        validarStatus(boleto.status());
    }

    private static void validarIdBoleto(Long idBoleto) {
        if (idBoleto <= 0) {
            throw new IllegalStateException(
                    "O IdBoleto deve ser maior que zero."
            );
        }
    }

    private static void validarIdConta(Long idConta) {
        if (idConta <= 0) {
            throw new IllegalStateException(
                    "O IdConta deve ser maior que zero."
            );
        }
    }

    private static void validarValor(BigDecimal valor) {
        if (valor.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalStateException(
                    "O valor do boleto deve ser maior que zero."
            );
        }
    }

    private static void validarStatus(String status) {
        boolean statusValido =
                "PENDENTE".equals(status)
                        || "PAGO".equals(status);

        if (!statusValido) {
            throw new IllegalStateException(
                    "Status do boleto inválido: " + status
            );
        }
    }
}