package com.automation.factory;

import com.automation.config.BrowserConfig;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;

public final class BrowserContextFactory {

    private BrowserContextFactory() {
        // Impede instanciaÃ§Ã£o.
    }

    public static BrowserContext criarContexto(Browser browser) {

        Browser.NewContextOptions opcoes =
                new Browser.NewContextOptions()
                        .setViewportSize(
                                BrowserConfig.getViewportWidth(),
                                BrowserConfig.getViewportHeight()
                        );

        return browser.newContext(opcoes);
    }
}
