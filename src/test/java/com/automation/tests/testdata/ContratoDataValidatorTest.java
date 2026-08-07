package com.automation.tests.testdata;

import com.automation.core.exceptions.TestDataException;
import com.automation.model.ContratoData;
import com.automation.testdata.ContratoDataValidator;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ContratoDataValidatorTest {

    @Test
    void deveReprovarContratoVazio() {
        ContratoData contrato = criarContratoValido();
        contrato.setContrato("");

        assertThatThrownBy(() -> ContratoDataValidator.validar(contrato))
                .isInstanceOf(TestDataException.class)
                .hasMessageContaining("Contrato não pode ser vazio");
    }

    @Test
    void deveReprovarProdutoVazio() {
        ContratoData contrato = criarContratoValido();
        contrato.setProduto("");

        assertThatThrownBy(() -> ContratoDataValidator.validar(contrato))
                .isInstanceOf(TestDataException.class)
                .hasMessageContaining("Produto não pode ser vazio");
    }

    @Test
    void deveReprovarValorDividaInvalido() {
        ContratoData contrato = criarContratoValido();
        contrato.setValorDivida(0);

        assertThatThrownBy(() -> ContratoDataValidator.validar(contrato))
                .isInstanceOf(TestDataException.class)
                .hasMessageContaining("Valor da dívida deve ser maior que zero");
    }

    @Test
    void deveReprovarDescontoNegativo() {
        ContratoData contrato = criarContratoValido();
        contrato.setDesconto(-1);

        assertThatThrownBy(() -> ContratoDataValidator.validar(contrato))
                .isInstanceOf(TestDataException.class)
                .hasMessageContaining("Desconto não pode ser negativo");
    }

    @Test
    void deveReprovarValorQuitacaoInvalido() {
        ContratoData contrato = criarContratoValido();
        contrato.setValorQuitacaoEsperado(0);

        assertThatThrownBy(() -> ContratoDataValidator.validar(contrato))
                .isInstanceOf(TestDataException.class)
                .hasMessageContaining(
                        "Valor de quitação esperado deve ser maior que zero"
                );
    }

    private ContratoData criarContratoValido() {
        ContratoData contrato = new ContratoData();

        contrato.setContrato("CTR-001");
        contrato.setProduto("596");
        contrato.setValorDivida(1000);
        contrato.setDesconto(100);
        contrato.setValorQuitacaoEsperado(900);

        return contrato;
    }
}