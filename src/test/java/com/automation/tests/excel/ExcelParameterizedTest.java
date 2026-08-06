package com.automation.tests.excel;

import com.automation.excel.ContratoExcelMapper;
import com.automation.excel.ExcelReader;
import com.automation.model.ContratoData;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class ExcelParameterizedTest {

    static Stream<ContratoData> massaContratos() {
        return ExcelReader.lerPlanilha(
                        "massa/contratos.xlsx",
                        "contratos"
                )
                .stream()
                .map(ContratoExcelMapper::mapear);
    }

    @ParameterizedTest(name = "Contrato: {0}")
    @MethodSource("massaContratos")
    void deveValidarMassaDoContrato(ContratoData contrato) {
        assertThat(contrato.getContrato()).isNotBlank();
        assertThat(contrato.getProduto()).isNotBlank();
        assertThat(contrato.getValorDivida()).isPositive();
        assertThat(contrato.getDesconto()).isGreaterThanOrEqualTo(0);
        assertThat(contrato.getValorQuitacaoEsperado()).isPositive();
    }
}