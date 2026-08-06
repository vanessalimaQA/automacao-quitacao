package com.automation.tests.service;

import com.automation.integrations.soap.builder.SimularQuitacaoBuilder;
import com.automation.integrations.soap.client.SoapClientContract;
import com.automation.integrations.soap.parser.SoapResponseParser;
import com.automation.integrations.soap.response.SimularQuitacaoResponse;
import com.automation.integrations.soap.service.SimularQuitacaoService;
import org.junit.jupiter.api.Test;

import javax.net.ssl.SSLSession;
import java.math.BigDecimal;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpHeaders;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class SimularQuitacaoServiceTest {

    private static final String ENDPOINT =
            "http://localhost/Conta.asmx";

    private static final String SOAP_ACTION =
            "http://conductor.com.br/SimularQuitacaoAntecipada";

    @Test
    void deveSimularQuitacaoEConverterRespostaSoap() {

        String xmlResposta = """
                <?xml version="1.0" encoding="UTF-8"?>
                <soapenv:Envelope
                    xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"
                    xmlns:con="http://conductor.com.br/">

                    <soapenv:Body>
                        <con:SimularQuitacaoAntecipadaResponse>
                            <con:SimularQuitacaoAntecipadaResult>
                                <con:CodRetorno>0</con:CodRetorno>
                                <con:DescricaoRetorno>
                                    SimulaÃ§Ã£o realizada com sucesso
                                </con:DescricaoRetorno>
                                <con:ValorMinimo>450.30</con:ValorMinimo>
                                <con:ValorTotal>1580.90</con:ValorTotal>
                            </con:SimularQuitacaoAntecipadaResult>
                        </con:SimularQuitacaoAntecipadaResponse>
                    </soapenv:Body>
                </soapenv:Envelope>
                """;

        SoapClientContract soapClientFake =
                (endpoint, soapAction, xmlRequest) -> {

                    assertThat(endpoint)
                            .isEqualTo(ENDPOINT);

                    assertThat(soapAction)
                            .isEqualTo(SOAP_ACTION);

                    assertThat(xmlRequest)
                            .contains(
                                    "<con:IdConta>123456</con:IdConta>"
                            )
                            .doesNotContain("${idConta}");

                    return criarRespostaHttp(
                            200,
                            xmlResposta
                    );
                };

        SimularQuitacaoService service =
                criarService(soapClientFake);

        SimularQuitacaoResponse response =
                service.simularConvertendoResposta(
                        "123456"
                );

        assertThat(response.getCodRetorno())
                .isZero();

        assertThat(response.getDescricaoRetorno())
                .isEqualTo(
                        "SimulaÃ§Ã£o realizada com sucesso"
                );

        assertThat(response.getValorMinimo())
                .isEqualByComparingTo(
                        new BigDecimal("450.30")
                );

        assertThat(response.getValorTotal())
                .isEqualByComparingTo(
                        new BigDecimal("1580.90")
                );
    }

    @Test
    void deveReprovarStatusHttpInvalido() {

        SoapClientContract soapClientFake =
                (endpoint, soapAction, xmlRequest) ->
                        criarRespostaHttp(
                                500,
                                "<erro>Falha interna</erro>"
                        );

        SimularQuitacaoService service =
                criarService(soapClientFake);

        assertThatThrownBy(
                () -> service.simularConvertendoResposta(
                        "123456"
                )
        )
                .isInstanceOf(
                        IllegalStateException.class
                )
                .hasMessageContaining(
                        "status HTTP invÃ¡lido: 500"
                );
    }

    private SimularQuitacaoService criarService(
            SoapClientContract soapClient
    ) {
        return new SimularQuitacaoService(
                soapClient,
                new SimularQuitacaoBuilder(),
                new SoapResponseParser(),
                ENDPOINT,
                SOAP_ACTION
        );
    }

    private HttpResponse<String> criarRespostaHttp(
            int statusCode,
            String body
    ) {
        return new HttpResponse<>() {

            @Override
            public int statusCode() {
                return statusCode;
            }

            @Override
            public HttpRequest request() {
                return HttpRequest.newBuilder()
                        .uri(URI.create(ENDPOINT))
                        .build();
            }

            @Override
            public Optional<HttpResponse<String>>
            previousResponse() {
                return Optional.empty();
            }

            @Override
            public HttpHeaders headers() {
                return HttpHeaders.of(
                        java.util.Map.of(),
                        (nome, valor) -> true
                );
            }

            @Override
            public String body() {
                return body;
            }

            @Override
            public Optional<SSLSession> sslSession() {
                return Optional.empty();
            }

            @Override
            public URI uri() {
                return URI.create(ENDPOINT);
            }

            @Override
            public HttpClient.Version version() {
                return HttpClient.Version.HTTP_1_1;
            }
        };
    }
}
