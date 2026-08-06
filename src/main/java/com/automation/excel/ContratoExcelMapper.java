package com.automation.excel;

import com.automation.model.ContratoData;

import java.util.Map;

public final class ContratoExcelMapper {

    private ContratoExcelMapper() {
    }

    public static ContratoData mapear(Map<String, String> linha) {
        return ContratoData.builder()
                .contrato(linha.get("contrato"))
                .produto(linha.get("produto"))
                .valorDivida(
                        Double.parseDouble(linha.get("valorDivida"))
                )
                .desconto(
                        Double.parseDouble(linha.get("desconto"))
                )
                .valorQuitacaoEsperado(
                        Double.parseDouble(
                                linha.get("valorQuitacaoEsperado")
                        )
                )
                .build();
    }
}