

package com.automation.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class DatabaseAccessor {

    private final String dbUrl;
    private final String user;
    private final String password;

    public DatabaseAccessor(
            String dbUrl,
            String user,
            String password
    ) {
        this.dbUrl = dbUrl;
        this.user = user;
        this.password = password;
    }

    public Optional<Map<String, Object>> executeQuery(
            String sql,
            Object... parametros
    ) throws SQLException {

        try (
                Connection connection =
                        DriverManager.getConnection(
                                dbUrl,
                                user,
                                password
                        );

                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {

            preencherParametros(statement, parametros);

            try (ResultSet resultSet = statement.executeQuery()) {

                if (!resultSet.next()) {
                    return Optional.empty();
                }

                return Optional.of(
                        converterLinhaParaMap(resultSet)
                );
            }
        }
    }

    private void preencherParametros(
            PreparedStatement statement,
            Object... parametros
    ) throws SQLException {

        for (int i = 0; i < parametros.length; i++) {
            statement.setObject(i + 1, parametros[i]);
        }
    }

    private Map<String, Object> converterLinhaParaMap(
            ResultSet resultSet
    ) throws SQLException {

        Map<String, Object> resultado = new HashMap<>();

        ResultSetMetaData metadata =
                resultSet.getMetaData();

        for (int i = 1; i <= metadata.getColumnCount(); i++) {

            String nomeColuna =
                    metadata.getColumnLabel(i);

            Object valor =
                    resultSet.getObject(i);

            resultado.put(nomeColuna, valor);
        }

        return resultado;
    }
}
