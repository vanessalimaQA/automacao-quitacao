package com.automation.tests.business;

import com.automation.business.PaymentInstruction;
import com.automation.business.PaymentScenario;
import com.automation.flow.PaymentScenarioFlow;
import com.automation.model.InvoicePaymentData;
import com.automation.repository.InvoicePaymentRepository;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@Tag("integration")
class InvoicePaymentIntegrationTest {

    @Test
    void deveCalcularTodosOsCenariosComDadosReaisDaFatura() {

        String idContaProperty = System.getProperty("idConta");

        if (idContaProperty == null || idContaProperty.isBlank()) {
            throw new IllegalArgumentException(
                    "Informe o IdConta usando -DidConta=NUMERO_DA_CONTA"
            );
        }

        Long idConta = Long.valueOf(idContaProperty);

        InvoicePaymentRepository repository =
                new InvoicePaymentRepository();

        InvoicePaymentData invoice =
                repository.buscarFaturaPorIdConta(idConta)
                        .orElseThrow(() ->
                                new AssertionError(
                                        "Nenhuma fatura tipo 7 encontrada para IdConta: "
                                                + idConta
                                )
                        );

        PaymentScenarioFlow flow =
                new PaymentScenarioFlow();

        System.out.println();
        System.out.println("====================================================");
        System.out.println("FATURA ENCONTRADA");
        System.out.println("====================================================");
        System.out.println("IdConta.............: " + invoice.idConta());
        System.out.println("IdHistorico.........: " + invoice.idHistorico());
        System.out.println("IdBoleto............: " + invoice.idBoleto());
        System.out.println("Valor Total.........: " + invoice.valorTotal());
        System.out.println("Valor Minimo........: " + invoice.valorMinimo());
        System.out.println("====================================================");

        for (PaymentScenario scenario : PaymentScenario.values()) {

            PaymentInstruction instruction =
                    flow.prepararInstrucao(
                            invoice,
                            scenario
                    );

            System.out.println(
                    scenario
                            + " -> "
                            + instruction.valorPagamento()
            );

            assertThat(instruction.valorPagamento())
                    .isPositive();
        }

        System.out.println("====================================================");
    }
}