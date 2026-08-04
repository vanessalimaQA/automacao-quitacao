package com.automation.builder;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

public final class SimularQuitacaoBuilder {

    private static final String TEMPLATE =
            "requests/SimularQuitacaoAntecipada.xml";

    public String montarRequest(String idConta) {

        Objects.requireNonNull(
                idConta,
                "O IdConta não pode ser nulo."
        );

        if (idConta.isBlank()) {
            throw new IllegalArgumentException(
                    "O IdConta não pode ser vazio."
            );
        }

        try (InputStream arquivoXml = getClass()
                .getClassLoader()
                .getResourceAsStream(TEMPLATE)) {

            if (arquivoXml == null) {
                throw new IllegalStateException(
                        "Template SOAP não encontrado: " + TEMPLATE
                );
            }

            String xml = new String(
                    arquivoXml.readAllBytes(),
                    StandardCharsets.UTF_8
            );

            return xml.replace(
                    "${idConta}",
                    idConta.trim()
            );

        } catch (IOException e) {
            throw new IllegalStateException(
                    "Erro ao carregar o template SOAP.",
                    e
            );
        }
    }
}