package com.automation.service;

import com.automation.builder.SimularQuitacaoBuilder;
import com.automation.client.SoapClient;
import com.automation.client.SoapClientContract;
import com.automation.config.SoapConfig;
import com.automation.parser.SoapResponseParser;
import com.automation.response.SimularQuitacaoResponse;

import java.net.http.HttpResponse;
import java.util.Objects;

public final class SimularQuitacaoService {

    private final SoapClientContract soapClient;
    private final SimularQuitacaoBuilder builder;
    private final SoapResponseParser responseParser;
    private final String endpoint;
    private final String soapAction;

    public SimularQuitacaoService() {
        this(
                new SoapClient(),
                new SimularQuitacaoBuilder(),
                new SoapResponseParser(),
                SoapConfig.getEndpointConta(),
                SoapConfig.getSimularQuitacaoAction()
        );
    }

    public SimularQuitacaoService(
            SoapClientContract soapClient,
            SimularQuitacaoBuilder builder,
            SoapResponseParser responseParser,
            String endpoint,
            String soapAction
    ) {
        this.soapClient = Objects.requireNonNull(
                soapClient,
                "O SoapClient não pode ser nulo."
        );

        this.builder = Objects.requireNonNull(
                builder,
                "O Builder não pode ser nulo."
        );

        this.responseParser = Objects.requireNonNull(
                responseParser,
                "O SoapResponseParser não pode ser nulo."
        );

        this.endpoint = validarTexto(
                endpoint,
                "O endpoint SOAP não pode ser vazio."
        );

        this.soapAction = validarTexto(
                soapAction,
                "A SOAP Action não pode ser vazia."
        );
    }

    public HttpResponse<String> simular(String idConta) {
        String xml = montarRequest(idConta);

        return soapClient.enviar(
                endpoint,
                soapAction,
                xml
        );
    }

    public SimularQuitacaoResponse simularConvertendoResposta(
            String idConta
    ) {
        HttpResponse<String> httpResponse = simular(idConta);

        validarStatusHttp(httpResponse);

        return responseParser.converter(
                httpResponse.body()
        );
    }

    public String montarRequest(String idConta) {
        return builder.montarRequest(idConta);
    }

    private void validarStatusHttp(
            HttpResponse<String> httpResponse
    ) {
        Objects.requireNonNull(
                httpResponse,
                "A resposta HTTP não pode ser nula."
        );

        int statusCode = httpResponse.statusCode();

        if (statusCode < 200 || statusCode >= 300) {
            throw new IllegalStateException(
                    "A simulação SOAP retornou status HTTP inválido: "
                            + statusCode
            );
        }
    }

    private String validarTexto(
            String valor,
            String mensagem
    ) {
        if (valor == null || valor.isBlank()) {
            throw new IllegalArgumentException(mensagem);
        }

        return valor.trim();
    }
}