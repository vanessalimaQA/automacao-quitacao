package com.automation.tests.excel;

import com.automation.excel.ExcelReader;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class ExcelReaderTest {

    @Test
    void deveLerPlanilhaComSucesso() {
        List<Map<String, String>> registros =
                ExcelReader.lerPlanilha(
                        "massa/contratos.xlsx",
                        "contratos"
                );

        assertThat(registros)
                .isNotNull()
                .isNotEmpty();

        assertThat(registros.getFirst())
                .containsKeys(
                        "contrato",
                        "produto",
                        "valorDivida",
                        "desconto",
                        "valorQuitacaoEsperado"
                );
    }
}