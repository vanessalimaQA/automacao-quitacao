package com.automation.flow;

import com.automation.pages.QuitacaoPage;
import com.automation.response.SimularQuitacaoResponse;
import com.automation.service.SimularQuitacaoService;
import com.microsoft.playwright.Page;

import java.util.Objects;

public final class QuitacaoFlow {

    private final QuitacaoPage quitacaoPage;
    private final SimularQuitacaoService simulacaoService;

    /**
     * Construtor usado pelos testes Web.
     * Não cria nem carrega configurações SOAP.
     */
    public QuitacaoFlow(Page page) {
        this.quitacaoPage = new QuitacaoPage(
                Objects.requireNonNull(
                        page,
                        "A Page não pode ser nula."
                )
        );

        this.simulacaoService = null;
    }

    /**
     * Construtor usado quando o fluxo também precisa chamar SOAP.
     */
    public QuitacaoFlow(
            QuitacaoPage quitacaoPage,
            SimularQuitacaoService simulacaoService
    ) {
        this.quitacaoPage = Objects.requireNonNull(
                quitacaoPage,
                "A QuitacaoPage não pode ser nula."
        );

        this.simulacaoService = Objects.requireNonNull(
                simulacaoService,
                "A SimularQuitacaoService não pode ser nula."
        );
    }

    public void simularQuitacaoNaTela(
            String idConta,
            String valor
    ) {
        quitacaoPage.acessar();

        quitacaoPage.simularQuitacao(
                idConta,
                valor
        );
    }

    public SimularQuitacaoResponse simularQuitacaoViaSoap(
            String idConta
    ) {
        validarServiceSoapConfigurada();

        return simulacaoService
                .simularConvertendoResposta(idConta);
    }

    public String obterMensagemDaTela() {
        return quitacaoPage.obterMensagemResultado();
    }

    private void validarServiceSoapConfigurada() {
        if (simulacaoService == null) {
            throw new IllegalStateException(
                    "A SimularQuitacaoService não foi configurada para esta Flow."
            );
        }
    }
}
