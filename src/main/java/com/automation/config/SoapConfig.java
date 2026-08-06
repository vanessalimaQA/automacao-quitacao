package com.automation.config;

public final class SoapConfig {

    private SoapConfig() {
        // Impede instanciaÃ§Ã£o.
    }

    public static String getEndpointConta() {
        return ConfigReader.get(
                "soap.conta.endpoint"
        );
    }

    public static String getSimularQuitacaoAction() {
        return ConfigReader.get(
                "soap.simular-quitacao.action"
        );
    }

    public static String getGerarBoletoAction() {
        return ConfigReader.get(
                "soap.gerar-boleto.action"
        );
    }
}
