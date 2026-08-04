package com.automation.request;

public record SimularQuitacaoRequest(
        String idConta
) {

    public SimularQuitacaoRequest {
        if (idConta == null || idConta.isBlank()) {
            throw new IllegalArgumentException(
                    "O IdConta não pode ser vazio."
            );
        }

        idConta = idConta.trim();
    }
}