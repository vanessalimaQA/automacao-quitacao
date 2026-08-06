package com.automation.utils;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;

import java.util.Objects;

public final class WaitUtils {

    private WaitUtils() {
        // Impede instanciaÃ§Ã£o.
    }

    public static void aguardarPaginaCarregar(Page page) {
        Objects.requireNonNull(page, "A Page nÃ£o pode ser nula.");

        page.waitForLoadState(LoadState.DOMCONTENTLOADED);
    }

    public static void aguardarRedeFicarOciosa(Page page) {
        Objects.requireNonNull(page, "A Page nÃ£o pode ser nula.");

        page.waitForLoadState(LoadState.NETWORKIDLE);
    }

    public static void aguardarElementoVisivel(
            Page page,
            String seletor
    ) {
        validarParametros(page, seletor);

        page.locator(seletor)
                .waitFor(
                        new Locator.WaitForOptions()
                                .setState(
                                        com.microsoft.playwright.options.WaitForSelectorState.VISIBLE
                                )
                );
    }

    public static void aguardarElementoOculto(
            Page page,
            String seletor
    ) {
        validarParametros(page, seletor);

        page.locator(seletor)
                .waitFor(
                        new Locator.WaitForOptions()
                                .setState(
                                        com.microsoft.playwright.options.WaitForSelectorState.HIDDEN
                                )
                );
    }

    public static void aguardarUrlConter(
            Page page,
            String trechoUrl
    ) {
        Objects.requireNonNull(page, "A Page nÃ£o pode ser nula.");

        if (trechoUrl == null || trechoUrl.isBlank()) {
            throw new IllegalArgumentException(
                    "O trecho da URL nÃ£o pode ser vazio."
            );
        }

        page.waitForURL(
                url -> url.contains(trechoUrl)
        );
    }

    private static void validarParametros(
            Page page,
            String seletor
    ) {
        Objects.requireNonNull(page, "A Page nÃ£o pode ser nula.");

        if (seletor == null || seletor.isBlank()) {
            throw new IllegalArgumentException(
                    "O seletor nÃ£o pode ser vazio."
            );
        }
    }
}
