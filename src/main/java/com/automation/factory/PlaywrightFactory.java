package com.automation.factory;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public final class PlaywrightFactory {

    private Playwright playwright;
    private Browser browser;
    private BrowserContext context;
    private Page page;

    public Page iniciarNavegador() {
        playwright = Playwright.create();
        browser = BrowserFactory.criarBrowser(playwright);
        context = BrowserContextFactory.criarContexto(browser);
        page = context.newPage();

        return page;
    }

    public void encerrarNavegador() {
        if (page != null) {
            page.close();
            page = null;
        }

        if (context != null) {
            context.close();
            context = null;
        }

        if (browser != null) {
            browser.close();
            browser = null;
        }

        if (playwright != null) {
            playwright.close();
            playwright = null;
        }
    }
}