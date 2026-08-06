package com.automation.utils;

import com.microsoft.playwright.Page;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public final class ScreenshotUtils {

    private static final String DIRETORIO =
            "target/screenshots";

    private ScreenshotUtils() {
        // Impede instanciaÃ§Ã£o.
    }

    public static void capturar(Page page, String nomeTeste) {

        try {

            Files.createDirectories(
                    Paths.get(DIRETORIO)
            );

            String timestamp =
                    LocalDateTime.now()
                            .format(
                                    DateTimeFormatter.ofPattern(
                                            "yyyyMMdd_HHmmss"
                                    )
                            );

            Path caminho = Paths.get(
                    DIRETORIO,
                    nomeTeste + "_" + timestamp + ".png"
            );

            page.screenshot(
                    new Page.ScreenshotOptions()
                            .setPath(caminho)
                            .setFullPage(true)
            );

        } catch (IOException e) {

            throw new RuntimeException(
                    "Erro ao salvar screenshot.",
                    e
            );

        }

    }

}
