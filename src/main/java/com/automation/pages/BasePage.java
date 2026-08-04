package com.automation.pages;

import com.automation.utils.WaitUtils;
import com.microsoft.playwright.Page;

import java.util.Objects;

public abstract class BasePage {

    protected final Page page;

    protected BasePage(Page page) {
        this.page = Objects.requireNonNull(
                page,
                "A Page do Playwright não pode ser nula."
        );
    }

    protected void navegarPara(String url) {
        page.navigate(url);
    }

    protected void preencher(String seletor, String texto) {
        page.locator(seletor).fill(texto);
    }

    protected void limparCampo(String seletor) {
        page.locator(seletor).fill("");
    }

    protected void clicar(String seletor) {
        page.locator(seletor).click();
    }

    protected void clicarDuasVezes(String seletor) {
        page.locator(seletor).dblclick();
    }

    protected void passarMouse(String seletor) {
        page.locator(seletor).hover();
    }

    protected void pressionarEnter(String seletor) {
        page.locator(seletor).press("Enter");
    }

    protected String obterTexto(String seletor) {
        String texto = page.locator(seletor).textContent();

        return texto == null
                ? ""
                : texto.trim();
    }

    protected boolean estaVisivel(String seletor) {
        return page.locator(seletor).isVisible();
    }

    protected void aguardarElemento(String seletor) {
        WaitUtils.aguardarElementoVisivel(
                page,
                seletor
        );
    }

    protected void aguardarUrl(String urlEsperada) {
        page.waitForURL(urlEsperada);
    }

    protected void aguardarUrlConter(String trechoUrl) {
        WaitUtils.aguardarUrlConter(
                page,
                trechoUrl
        );
    }

    protected void aguardarTitulo(String tituloEsperado) {
        page.waitForFunction(
                "titulo => document.title === titulo",
                tituloEsperado
        );
    }

    protected String obterUrlAtual() {
        return page.url();
    }

    protected String obterTituloAtual() {
        return page.title();
    }
}