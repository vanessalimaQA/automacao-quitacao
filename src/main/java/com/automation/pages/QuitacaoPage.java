package com.automation.pages;

import com.automation.config.EnvironmentConfig;
import com.automation.locators.QuitacaoLocators;
import com.microsoft.playwright.Page;

public final class QuitacaoPage extends BasePage {

    public QuitacaoPage(Page page) {
        super(page);
    }

    public void acessar() {
        navegarPara(
                EnvironmentConfig.getBaseUrl()
        );
    }

    public void informarIdConta(String idConta) {
        preencher(
                QuitacaoLocators.CAMPO_ID_CONTA,
                idConta
        );
    }

    public void informarValorDivida(String valorDivida) {
        preencher(
                QuitacaoLocators.CAMPO_VALOR,
                valorDivida
        );
    }

    public void clicarEmSimular() {
        clicar(
                QuitacaoLocators.BOTAO_SIMULAR
        );
    }

    public void clicarEmGerarBoleto() {
        clicar(
                QuitacaoLocators.BOTAO_GERAR_BOLETO
        );
    }

    public String obterMensagemResultado() {
        return obterTexto(
                QuitacaoLocators.MENSAGEM_RESULTADO
        );
    }

    public boolean resultadoEstaVisivel() {
        return estaVisivel(
                QuitacaoLocators.MENSAGEM_RESULTADO
        );
    }

    public void simularQuitacao(
            String idConta,
            String valorDivida
    ) {
        informarIdConta(idConta);
        informarValorDivida(valorDivida);
        clicarEmSimular();
    }
}