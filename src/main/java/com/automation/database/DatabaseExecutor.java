package com.automation.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public final class DatabaseExecutor {

    public <T> Optional<T> consultarUm(
            String sql,
            RowMapper<T> rowMapper,
            Object... parametros
    ) {
        validarConsulta(sql, rowMapper);

        try (
                Connection connection = DatabaseConnection.abrir();
                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {
            preencherParametros(statement, parametros);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (!resultSet.next()) {
                    return Optional.empty();
                }

                return Optional.ofNullable(
                        rowMapper.mapear(resultSet)
                );
            }

        } catch (SQLException e) {
            throw new IllegalStateException(
                    "Erro ao executar consulta no banco de dados.",
                    e
            );
        }
    }

    public <T> List<T> consultarLista(
            String sql,
            RowMapper<T> rowMapper,
            Object... parametros
    ) {
        validarConsulta(sql, rowMapper);

        List<T> resultados = new ArrayList<>();

        try (
                Connection connection = DatabaseConnection.abrir();
                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {
            preencherParametros(statement, parametros);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    resultados.add(
                            rowMapper.mapear(resultSet)
                    );
                }
            }

            return resultados;

        } catch (SQLException e) {
            throw new IllegalStateException(
                    "Erro ao executar consulta no banco de dados.",
                    e
            );
        }
    }

    public int executarAtualizacao(
            String sql,
            Object... parametros
    ) {
        if (sql == null || sql.isBlank()) {
            throw new IllegalArgumentException(
                    "O SQL não pode ser vazio."
            );
        }

        try (
                Connection connection = DatabaseConnection.abrir();
                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {
            preencherParametros(statement, parametros);

            return statement.executeUpdate();

        } catch (SQLException e) {
            throw new IllegalStateException(
                    "Erro ao executar atualização no banco de dados.",
                    e
            );
        }
    }

    private void preencherParametros(
            PreparedStatement statement,
            Object... parametros
    ) throws SQLException {
        if (parametros == null) {
            return;
        }

        for (int indice = 0; indice < parametros.length; indice++) {
            statement.setObject(
                    indice + 1,
                    parametros[indice]
            );
        }
    }

    private <T> void validarConsulta(
            String sql,
            RowMapper<T> rowMapper
    ) {
        if (sql == null || sql.isBlank()) {
            throw new IllegalArgumentException(
                    "O SQL não pode ser vazio."
            );
        }

        if (rowMapper == null) {
            throw new IllegalArgumentException(
                    "O RowMapper não pode ser nulo."
            );
        }
    }
}