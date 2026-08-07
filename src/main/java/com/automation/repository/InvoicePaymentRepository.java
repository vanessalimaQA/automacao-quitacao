package com.automation.repository;

import com.automation.database.DatabaseExecutor;
import com.automation.model.InvoicePaymentData;

import java.util.Optional;

public final class InvoicePaymentRepository {

    private static final int TIPO_BOLETO_FATURA = 7;

    private final DatabaseExecutor databaseExecutor;

    public InvoicePaymentRepository() {
        this.databaseExecutor = new DatabaseExecutor();
    }

    public Optional<InvoicePaymentData> buscarFaturaPorIdConta(
            Long idConta
    ) {

        String sql = """
                SELECT TOP 1
                    b.Id_Conta,
                    b.Id_Historico,
                    b.Id_Boleto,
                    h.saldoAtualFinal AS ValorTotal,
                    h.valorMinimoExtrato AS ValorMinimo
                FROM BoletosEmitidos b
                INNER JOIN HistoricosCorrentes h
                    ON h.Id_Historico = b.Id_Historico
                WHERE b.Id_Conta = ?
                  AND b.id_tipoboleto = ?
                ORDER BY b.Id_Historico DESC
                """;

        return databaseExecutor.consultarUm(
                sql,
                resultSet -> new InvoicePaymentData(
                        resultSet.getLong("Id_Conta"),
                        resultSet.getLong("Id_Historico"),
                        resultSet.getLong("Id_Boleto"),
                        resultSet.getBigDecimal("ValorTotal"),
                        resultSet.getBigDecimal("ValorMinimo")
                ),
                idConta,
                TIPO_BOLETO_FATURA
        );
    }
}