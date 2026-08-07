package com.automation.tests.business;

import com.automation.business.PaymentContext;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

class PaymentContextTest {

    @Test
    void deveCriarContextoDePagamento() {

        PaymentContext context = new PaymentContext(
                new BigDecimal("200.00"),
                new BigDecimal("250.00"),
                new BigDecimal("230.00")
        );

        assertThat(context.getValorMinimo())
                .isEqualByComparingTo("200.00");

        assertThat(context.getValorTotal())
                .isEqualByComparingTo("250.00");

        assertThat(context.getValorPagamento())
                .isEqualByComparingTo("230.00");
    }
}