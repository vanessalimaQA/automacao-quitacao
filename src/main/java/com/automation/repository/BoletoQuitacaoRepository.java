package com.automation.repository;

import com.automation.database.DatabaseExecutor;
import com.automation.model.BoletoQuitacao;

import java.util.Optional;

public final class BoletoQuitacaoRepository {

    private final DatabaseExecutor databaseExecutor;

    public BoletoQuitacaoRepository() {
        this.databaseExecutor = new DatabaseExecutor();
    }

    public Optional<BoletoQuitacao> buscarPorIdConta(Long idConta) {

        String sql = """
                SELECT
                    Id_Boleto,
                    Id_Conta,
                    Valor,
                    Status
                FROM BoletosQuitacao
                WHERE Id_Conta = ?
                """;

        return databaseExecutor.consultarUm(
                sql,
                resultSet -> new BoletoQuitacao(
                        resultSet.getLong("Id_Boleto"),
                        resultSet.getLong("Id_Conta"),
                        resultSet.getBigDecimal("Valor"),
                        resultSet.getString("Status")
                ),
                idConta
        );
    }
}
