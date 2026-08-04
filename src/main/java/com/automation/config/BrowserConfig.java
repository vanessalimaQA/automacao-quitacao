
package com.automation.config;

public final class BrowserConfig {

    private BrowserConfig() {
        // Impede instanciação.
    }

    public static String getBrowser() {
        return ConfigReader.get("browser");
    }

    public static boolean isHeadless() {
        return Boolean.parseBoolean(
                ConfigReader.get("headless")
        );
    }

    public static int getViewportWidth() {
        return Integer.parseInt(
                ConfigReader.get("viewport.width")
        );
    }

    public static int getViewportHeight() {
        return Integer.parseInt(
                ConfigReader.get("viewport.height")
        );
    }
}