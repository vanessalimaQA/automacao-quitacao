package com.automation.testdata;

import com.automation.excel.ContratoExcelMapper;
import com.automation.excel.ExcelReader;
import com.automation.model.ContratoData;

import java.util.List;

public final class ContratoDataFactory {

    private ContratoDataFactory() {
    }

    public static List<ContratoData> carregarDoExcel() {
        return ExcelReader.lerPlanilha(
                        "massa/contratos.xlsx",
                        "contratos"
                )
                .stream()
                .map(ContratoExcelMapper::mapear)
                .toList();
    }
}