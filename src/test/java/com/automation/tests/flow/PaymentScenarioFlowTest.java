package com.automation.tests.flow;

import com.automation.business.PaymentInstruction;
import com.automation.business.PaymentScenario;
import com.automation.flow.PaymentScenarioFlow;
import com.automation.model.InvoicePaymentData;
import com.automation.tests.fixtures.InvoicePaymentFixture;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class PaymentScenarioFlowTest {

    private final PaymentScenarioFlow flow =
            new PaymentScenarioFlow();

    private final InvoicePaymentData invoice =
            InvoicePaymentFixture.faturaPadrao();

    @ParameterizedTest(name = "{0} deve gerar pagamento {1}")
    @MethodSource("cenarios")
    void devePrepararInstrucaoParaCadaCenario(
            PaymentScenario scenario,
            String valorEsperado
    ) {

        PaymentInstruction instruction =
                flow.prepararInstrucao(
                        invoice,
                        scenario
                );

        assertThat(instruction.idConta())
                .isEqualTo(invoice.idConta());

        assertThat(instruction.idHistorico())
                .isEqualTo(invoice.idHistorico());

        assertThat(instruction.idBoleto())
                .isEqualTo(invoice.idBoleto());

        assertThat(instruction.scenario())
                .isEqualTo(scenario);

        assertThat(instruction.valorPagamento())
                .isEqualByComparingTo(valorEsperado);
    }

    private static Stream<Arguments> cenarios() {
        return Stream.of(
                Arguments.of(
                        PaymentScenario.BELOW_MINIMUM,
                        "100.00"
                ),
                Arguments.of(
                        PaymentScenario.MINIMUM,
                        "200.00"
                ),
                Arguments.of(
                        PaymentScenario.PARTIAL_20,
                        "210.00"
                ),
                Arguments.of(
                        PaymentScenario.PARTIAL_60,
                        "230.00"
                ),
                Arguments.of(
                        PaymentScenario.TOTAL,
                        "250.00"
                ),
                Arguments.of(
                        PaymentScenario.ABOVE_TOTAL,
                        "275.00"
                )
        );
    }
}