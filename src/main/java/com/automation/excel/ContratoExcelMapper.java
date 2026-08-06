package com.automation.excel;

import com.automation.model.ContratoData;

import java.util.Map;

public final class ContratoExcelMapper {

    private ContratoExcelMapper() {
    }

    public static ContratoData mapear(Map<String, String> linha) {
        ContratoData contrato = new ContratoData();

        contrato.setContrato(linha.get("contrato"));
        contrato.setProduto(linha.get("produto"));
        contrato.setValorDivida(
                Double.parseDouble(linha.get("valorDivida"))
        );
        contrato.setDesconto(
                Double.parseDouble(linha.get("desconto"))
        );
        contrato.setValorQuitacaoEsperado(
                Double.parseDouble(
                        linha.get("valorQuitacaoEsperado")
                )
        );

        return contrato;
    }
}