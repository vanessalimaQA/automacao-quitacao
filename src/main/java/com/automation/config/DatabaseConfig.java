package com.automation.config;

public final class DatabaseConfig {

    private DatabaseConfig() {
        // Impede instanciação.
    }

    public static String getUrl() {
        return ConfigReader.get("db.url");
    }

    public static String getUsuario() {
        return ConfigReader.get("db.user");
    }

    public static String getSenha() {
        return ConfigReader.get("db.password");
    }

    public static String getDriver() {
        return ConfigReader.getOptional(
                "db.driver",
                "com.microsoft.sqlserver.jdbc.SQLServerDriver"
        );
    }
}