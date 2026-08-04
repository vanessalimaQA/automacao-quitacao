package com.automation.client;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.Objects;

public final class SoapClient implements SoapClientContract {

    private static final Duration TIMEOUT_CONEXAO =
            Duration.ofSeconds(20);

    private static final Duration TIMEOUT_REQUISICAO =
            Duration.ofSeconds(60);

    private static final String CONTENT_TYPE_SOAP =
            "text/xml; charset=UTF-8";

    private final HttpClient httpClient;

    public SoapClient() {
        this(
                HttpClient.newBuilder()
                        .connectTimeout(TIMEOUT_CONEXAO)
                        .build()
        );
    }

    SoapClient(HttpClient httpClient) {
        this.httpClient = Objects.requireNonNull(
                httpClient,
                "O HttpClient não pode ser nulo."
        );
    }

    @Override
    public HttpResponse<String> enviar(
            String endpoint,
            String soapAction,
            String xml
    ) {
        validarParametros(endpoint, xml);

        HttpRequest requisicao = criarRequisicao(
                endpoint,
                soapAction,
                xml
        );

        try {
            return httpClient.send(
                    requisicao,
                    HttpResponse.BodyHandlers.ofString(
                            StandardCharsets.UTF_8
                    )
            );

        } catch (IOException e) {
            throw new IllegalStateException(
                    "Erro de comunicação com o endpoint SOAP: "
                            + endpoint,
                    e
            );

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();

            throw new IllegalStateException(
                    "A requisição SOAP foi interrompida.",
                    e
            );
        }
    }

    private HttpRequest criarRequisicao(
            String endpoint,
            String soapAction,
            String xml
    ) {
        HttpRequest.Builder requestBuilder = HttpRequest.newBuilder()
                .uri(criarUri(endpoint))
                .timeout(TIMEOUT_REQUISICAO)
                .header(
                        "Content-Type",
                        CONTENT_TYPE_SOAP
                )
                .POST(
                        HttpRequest.BodyPublishers.ofString(
                                xml,
                                StandardCharsets.UTF_8
                        )
                );

        if (soapAction != null && !soapAction.isBlank()) {
            requestBuilder.header(
                    "SOAPAction",
                    soapAction.trim()
            );
        }

        return requestBuilder.build();
    }

    private URI criarUri(String endpoint) {
        try {
            return URI.create(endpoint);

        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(
                    "Endpoint SOAP inválido: " + endpoint,
                    e
            );
        }
    }

    private void validarParametros(
            String endpoint,
            String xml
    ) {
        if (endpoint == null || endpoint.isBlank()) {
            throw new IllegalArgumentException(
                    "O endpoint SOAP não pode ser vazio."
            );
        }

        if (xml == null || xml.isBlank()) {
            throw new IllegalArgumentException(
                    "O XML SOAP não pode ser vazio."
            );
        }
    }
}