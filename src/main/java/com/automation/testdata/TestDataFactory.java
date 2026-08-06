package com.automation.testdata;

import net.datafaker.Faker;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Locale;

public final class TestDataFactory {

    private static final Faker FAKER =
            new Faker(new Locale("pt", "BR"));

    private TestDataFactory() {
    }

    public static String nomeCompleto() {
        return FAKER.name().fullName();
    }

    public static String email() {
        return FAKER.internet().emailAddress();
    }

    public static String telefone() {
        return FAKER.phoneNumber().cellPhone();
    }

    public static String cidade() {
        return FAKER.address().city();
    }

    public static String estado() {
        return FAKER.address().state();
    }

    public static String idConta() {
        return FAKER.number().digits(8);
    }

    public static String contrato() {
        return FAKER.number().digits(10);
    }

    public static String produto() {
        String[] produtos = {
                "596",
                "597",
                "598"
        };

        return produtos[
                FAKER.random().nextInt(produtos.length)
                ];
    }

    public static BigDecimal valor(
            double minimo,
            double maximo
    ) {
        if (minimo < 0) {
            throw new IllegalArgumentException(
                    "O valor mÃ­nimo nÃ£o pode ser negativo."
            );
        }

        if (maximo <= minimo) {
            throw new IllegalArgumentException(
                    "O valor mÃ¡ximo deve ser maior que o mÃ­nimo."
            );
        }

        double valorGerado = FAKER.number()
                .randomDouble(
                        2,
                        (long) minimo,
                        (long) maximo
                );

        return BigDecimal.valueOf(valorGerado)
                .setScale(2, RoundingMode.HALF_UP);
    }
}
