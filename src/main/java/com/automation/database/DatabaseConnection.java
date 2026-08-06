package com.automation.database;

import com.automation.config.DatabaseConfig;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class DatabaseConnection {

    private DatabaseConnection() {
        // Impede instanciaÃ§Ã£o.
    }

    public static Connection abrir() {
        carregarDriver();

        try {
            return DriverManager.getConnection(
                    DatabaseConfig.getUrl(),
                    DatabaseConfig.getUsuario(),
                    DatabaseConfig.getSenha()
            );

        } catch (SQLException e) {
            throw new IllegalStateException(
                    "NÃ£o foi possÃ­vel conectar ao SQL Server.",
                    e
            );
        }
    }

    private static void carregarDriver() {
        try {
            Class.forName(DatabaseConfig.getDriver());

        } catch (ClassNotFoundException e) {
            throw new IllegalStateException(
                    "Driver JDBC do SQL Server nÃ£o encontrado.",
                    e
            );
        }
    }
}
