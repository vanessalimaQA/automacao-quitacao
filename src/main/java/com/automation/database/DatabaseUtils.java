package com.automation.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public final class DatabaseUtils {

    private DatabaseUtils() {
        // Impede instanciaÃ§Ã£o.
    }

    public static void fechar(ResultSet resultSet) {
        if (resultSet == null) {
            return;
        }

        try {
            resultSet.close();
        } catch (SQLException e) {
            throw new IllegalStateException(
                    "Erro ao fechar o ResultSet.",
                    e
            );
        }
    }

    public static void fechar(Statement statement) {
        if (statement == null) {
            return;
        }

        try {
            statement.close();
        } catch (SQLException e) {
            throw new IllegalStateException(
                    "Erro ao fechar o Statement.",
                    e
            );
        }
    }

    public static void fechar(Connection connection) {
        if (connection == null) {
            return;
        }

        try {
            connection.close();
        } catch (SQLException e) {
            throw new IllegalStateException(
                    "Erro ao fechar a conexÃ£o com o banco.",
                    e
            );
        }
    }
}
