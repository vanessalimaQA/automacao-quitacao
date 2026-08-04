package com.automation.core.report;

import io.qameta.allure.Allure;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;

public final class AllureEvidence {

    private AllureEvidence() {
        // Impede instanciação.
    }

    public static void anexarTexto(
            String nome,
            String conteudo
    ) {
        String valor = conteudo == null ? "" : conteudo;

        Allure.addAttachment(
                nome,
                "text/plain",
                new ByteArrayInputStream(
                        valor.getBytes(StandardCharsets.UTF_8)
                ),
                ".txt"
        );
    }

    public static void anexarXml(
            String nome,
            String xml
    ) {
        String valor = xml == null ? "" : xml;

        Allure.addAttachment(
                nome,
                "application/xml",
                new ByteArrayInputStream(
                        valor.getBytes(StandardCharsets.UTF_8)
                ),
                ".xml"
        );
    }

    public static void anexarStatusHttp(int statusCode) {
        anexarTexto(
                "Status HTTP",
                String.valueOf(statusCode)
        );
    }
}