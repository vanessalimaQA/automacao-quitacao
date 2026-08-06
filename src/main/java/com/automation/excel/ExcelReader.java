package com.automation.excel;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public final class ExcelReader {

    private ExcelReader() {
    }

    public static List<Map<String, String>> lerPlanilha(
            String caminhoArquivo,
            String nomeAba
    ) {
        try (
                InputStream arquivo = ExcelReader.class
                        .getClassLoader()
                        .getResourceAsStream(caminhoArquivo)
        ) {
            if (arquivo == null) {
                throw new IllegalArgumentException(
                        "Arquivo não encontrado em resources: " + caminhoArquivo
                );
            }

            try (Workbook workbook = new XSSFWorkbook(arquivo)) {
                Sheet sheet = workbook.getSheet(nomeAba);

                if (sheet == null) {
                    throw new IllegalArgumentException(
                            "Aba não encontrada: " + nomeAba
                    );
                }

                return lerLinhas(sheet);
            }

        } catch (IOException e) {
            throw new IllegalStateException(
                    "Erro ao ler arquivo Excel: " + caminhoArquivo,
                    e
            );
        }
    }

    private static List<Map<String, String>> lerLinhas(Sheet sheet) {
        List<Map<String, String>> registros = new ArrayList<>();

        Row cabecalho = sheet.getRow(0);

        if (cabecalho == null) {
            throw new IllegalArgumentException(
                    "A planilha não possui cabeçalho."
            );
        }

        DataFormatter formatter = new DataFormatter();

        for (int indiceLinha = 1;
             indiceLinha <= sheet.getLastRowNum();
             indiceLinha++) {

            Row linha = sheet.getRow(indiceLinha);

            if (linha == null || linhaVazia(linha, formatter)) {
                continue;
            }

            Map<String, String> registro = new LinkedHashMap<>();

            for (int indiceColuna = 0;
                 indiceColuna < cabecalho.getLastCellNum();
                 indiceColuna++) {

                String nomeColuna = formatter.formatCellValue(
                        cabecalho.getCell(indiceColuna)
                );

                String valor = formatter.formatCellValue(
                        linha.getCell(indiceColuna)
                );

                registro.put(nomeColuna, valor);
            }

            registros.add(registro);
        }

        return registros;
    }

    private static boolean linhaVazia(
            Row linha,
            DataFormatter formatter
    ) {
        for (Cell cell : linha) {
            if (!formatter.formatCellValue(cell).isBlank()) {
                return false;
            }
        }

        return true;
    }
}