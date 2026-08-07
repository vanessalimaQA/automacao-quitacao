package com.automation.testdata;

import com.automation.model.ContratoData;
import net.datafaker.Faker;

import java.util.Locale;

public final class ContratoFakerFactory {

    private static final Faker FAKER =
            new Faker(new Locale("pt", "BR"));

    private ContratoFakerFactory() {
    }

    public static ContratoData criarContratoValido() {

        ContratoData contrato = new ContratoData();

        double valorDivida =
                FAKER.number().randomDouble(2, 500, 5000);

        double desconto =
                FAKER.number().randomDouble(
                        2,
                        0,
                        (long) (valorDivida * 0.30)
                );

        double valorQuitacao =
                valorDivida - desconto;

        contrato.setContrato(
                "CTR-" + FAKER.number().digits(8)
        );

        contrato.setProduto(
                FAKER.options().option(
                        "596",
                        "597",
                        "598"
                )
        );

        contrato.setValorDivida(valorDivida);
        contrato.setDesconto(desconto);
        contrato.setValorQuitacaoEsperado(valorQuitacao);

        return contrato;
    }
}