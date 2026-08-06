package com.automation.integrations.soap.request;

public record SimularQuitacaoRequest(
        String idConta
) {

    public SimularQuitacaoRequest {
        if (idConta == null || idConta.isBlank()) {
            throw new IllegalArgumentException(
                    "O IdConta nÃ£o pode ser vazio."
            );
        }

        idConta = idConta.trim();
    }
}
