package com.automation.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ContratoData {

    private String contrato;
    private String produto;
    private double valorDivida;
    private double desconto;
    private double valorQuitacaoEsperado;
}
