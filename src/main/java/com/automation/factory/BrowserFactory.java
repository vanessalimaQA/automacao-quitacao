package com.automation.factory;

import com.automation.config.BrowserConfig;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Playwright;

public final class BrowserFactory {

    private static final String CHROME = "chrome";
    private static final String EDGE = "edge";
    private static final String FIREFOX = "firefox";
    private static final String CHROMIUM = "chromium";

    private BrowserFactory() {
        // Impede instanciação.
    }

    public static Browser criarBrowser(Playwright playwright) {

        String browserSelecionado = BrowserConfig.getBrowser();
        boolean headless = BrowserConfig.isHeadless();

        BrowserType.LaunchOptions opcoes =
                criarOpcoesDeInicializacao(headless);

        return switch (browserSelecionado.toLowerCase()) {

            case CHROME ->
                    playwright.chromium().launch(
                            opcoes.setChannel(CHROME)
                    );

            case EDGE ->
                    playwright.chromium().launch(
                            opcoes.setChannel("msedge")
                    );

            case FIREFOX ->
                    playwright.firefox().launch(opcoes);

            case CHROMIUM ->
                    playwright.chromium().launch(opcoes);

            default ->
                    throw new IllegalArgumentException(
                            "Browser não suportado: " + browserSelecionado
                    );
        };
    }

    private static BrowserType.LaunchOptions criarOpcoesDeInicializacao(
            boolean headless
    ) {
        return new BrowserType.LaunchOptions()
                .setHeadless(headless);
    }
}