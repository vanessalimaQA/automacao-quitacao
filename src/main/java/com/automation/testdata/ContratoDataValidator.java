package com.automation.testdata;

import com.automation.core.exceptions.TestDataException;
import com.automation.model.ContratoData;

public final class ContratoDataValidator {

    private ContratoDataValidator() {
    }

    public static void validar(ContratoData contrato) {

        if (contrato.getContrato() == null
                || contrato.getContrato().isBlank()) {
            throw new TestDataException(
                    "Contrato não pode ser vazio."
            );
        }

        if (contrato.getProduto() == null
                || contrato.getProduto().isBlank()) {
            throw new TestDataException(
                    "Produto não pode ser vazio. Contrato: "
                            + contrato.getContrato()
            );
        }

        if (contrato.getValorDivida() <= 0) {
            throw new TestDataException(
                    "Valor da dívida deve ser maior que zero. Contrato: "
                            + contrato.getContrato()
            );
        }

        if (contrato.getDesconto() < 0) {
            throw new TestDataException(
                    "Desconto não pode ser negativo. Contrato: "
                            + contrato.getContrato()
            );
        }

        if (contrato.getValorQuitacaoEsperado() <= 0) {
            throw new TestDataException(
                    "Valor de quitação esperado deve ser maior que zero. Contrato: "
                            + contrato.getContrato()
            );
        }
    }
}