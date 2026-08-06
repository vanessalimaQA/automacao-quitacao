package com.automation.tests.web;

import com.automation.core.BaseTest;
import com.automation.flow.QuitacaoFlow;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class QuitacaoWebTest extends BaseTest {

    @Test
    void deveSimularQuitacaoComSucesso() {

        QuitacaoFlow quitacaoFlow =
                new QuitacaoFlow(page);

        quitacaoFlow.simularQuitacaoNaTela(
                "123456",
                "1500.00"
        );

        assertThat(
                quitacaoFlow.obterMensagemDaTela()
        ).contains(
                "Simulação realizada com sucesso"
        );
    }
}
