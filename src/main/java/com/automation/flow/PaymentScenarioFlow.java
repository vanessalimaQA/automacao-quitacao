package com.automation.flow;

import com.automation.business.PaymentContext;
import com.automation.business.PaymentInstruction;
import com.automation.business.PaymentScenario;
import com.automation.business.PaymentScenarioResolver;
import com.automation.model.InvoicePaymentData;

public final class PaymentScenarioFlow {

    public PaymentContext prepararCenario(
            InvoicePaymentData invoice,
            PaymentScenario scenario
    ) {
        return PaymentScenarioResolver.resolver(
                invoice,
                scenario
        );
    }

    public PaymentInstruction prepararInstrucao(
            InvoicePaymentData invoice,
            PaymentScenario scenario
    ) {
        PaymentContext context =
                prepararCenario(invoice, scenario);

        return new PaymentInstruction(
                invoice.idConta(),
                invoice.idHistorico(),
                invoice.idBoleto(),
                scenario,
                context.getValorPagamento()
        );
    }
}