package com.automation.testdata;

import com.automation.excel.ContratoExcelMapper;
import com.automation.excel.ExcelReader;
import com.automation.model.ContratoData;

import java.util.List;
import java.util.Map;
import java.util.Set;

public final class ContratoDataFactory {

    private static final Set<String> COLUNAS_OBRIGATORIAS = Set.of(
            "contrato",
            "produto",
            "valorDivida",
            "desconto",
            "valorQuitacaoEsperado"
    );

    private ContratoDataFactory() {
    }

    public static List<ContratoData> carregarDoExcel() {

        List<Map<String, String>> registros =
                ExcelReader.lerPlanilha(
                        "massa/contratos.xlsx",
                        "contratos"
                );

        ExcelReader.validarColunasObrigatorias(
                registros,
                COLUNAS_OBRIGATORIAS
        );

        return registros.stream()
                .map(ContratoExcelMapper::mapear)
                .toList();
    }
}