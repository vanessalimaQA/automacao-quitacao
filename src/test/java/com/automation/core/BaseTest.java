package com.automation.core;

import com.automation.factory.PlaywrightFactory;
import com.automation.utils.ScreenshotUtils;
import com.microsoft.playwright.Page;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;

public abstract class BaseTest {

    protected Page page;
    private PlaywrightFactory playwrightFactory;

    @BeforeEach
    protected void setUp() {
        playwrightFactory = new PlaywrightFactory();
        page = playwrightFactory.iniciarNavegador();
    }

    @AfterEach
    protected void tearDown(TestInfo testInfo) {
        try {
            capturarEvidencia(testInfo);
        } finally {
            encerrarNavegador();
        }
    }

    private void capturarEvidencia(TestInfo testInfo) {
        if (page == null
                || page.isClosed()
                || testInfo.getTestMethod().isEmpty()) {
            return;
        }

        String nomeTeste = testInfo.getDisplayName()
                .replace("()", "")
                .replaceAll("[^a-zA-Z0-9_-]", "_");

        ScreenshotUtils.capturar(page, nomeTeste);
    }

    private void encerrarNavegador() {
        if (playwrightFactory != null) {
            playwrightFactory.encerrarNavegador();
            playwrightFactory = null;
        }

        page = null;
    }
}
