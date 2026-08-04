package com.automation.model;

public class ContratoData {

    private String contrato;
    private String produto;
    private double valorDivida;
    private double desconto;
    private double valorQuitacaoEsperado;

    public String getContrato() {
        return contrato;
    }

    public void setContrato(String contrato) {
        this.contrato = contrato;
    }

    public String getProduto() {
        return produto;
    }

    public void setProduto(String produto) {
        this.produto = produto;
    }

    public double getValorDivida() {
        return valorDivida;
    }

    public void setValorDivida(double valorDivida) {
        this.valorDivida = valorDivida;
    }

    public double getDesconto() {
        return desconto;
    }

    public void setDesconto(double desconto) {
        this.desconto = desconto;
    }

    public double getValorQuitacaoEsperado() {
        return valorQuitacaoEsperado;
    }

    public void setValorQuitacaoEsperado(double valorQuitacaoEsperado) {
        this.valorQuitacaoEsperado = valorQuitacaoEsperado;
    }
}