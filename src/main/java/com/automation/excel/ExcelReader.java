package com.automation.excel;

import com.automation.core.exceptions.TestDataException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public final class ExcelReader {

    private ExcelReader() {
    }

    public static List<Map<String, String>> lerPlanilha(
            String caminhoArquivo,
            String nomeAba
    ) {
        try (InputStream arquivo = ExcelReader.class
                .getClassLoader()
                .getResourceAsStream(caminhoArquivo)) {

            if (arquivo == null) {
                throw new TestDataException(
                        "Arquivo Excel não encontrado: " + caminhoArquivo
                );
            }

            try (Workbook workbook = new XSSFWorkbook(arquivo)) {
                Sheet sheet = workbook.getSheet(nomeAba);

                if (sheet == null) {
                    throw new TestDataException(
                            "Aba não encontrada: " + nomeAba
                    );
                }

                return lerLinhas(sheet);
            }

        } catch (IOException e) {
            throw new TestDataException(
                    "Erro ao ler o arquivo Excel: " + caminhoArquivo,
                    e
            );
        }
    }

    public static void validarColunasObrigatorias(
            List<Map<String, String>> registros,
            Set<String> colunasObrigatorias
    ) {
        if (registros == null || registros.isEmpty()) {
            throw new TestDataException(
                    "A planilha não possui registros."
            );
        }

        Set<String> colunasEncontradas = registros.get(0).keySet();

        List<String> colunasAusentes = colunasObrigatorias.stream()
                .filter(coluna -> !colunasEncontradas.contains(coluna))
                .toList();

        if (!colunasAusentes.isEmpty()) {
            throw new TestDataException(
                    "Colunas obrigatórias ausentes: " + colunasAusentes
            );
        }
    }

    private static List<Map<String, String>> lerLinhas(Sheet sheet) {
        List<Map<String, String>> registros = new ArrayList<>();

        Row cabecalho = sheet.getRow(0);

        if (cabecalho == null) {
            throw new TestDataException(
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

                String nomeColuna = formatter
                        .formatCellValue(cabecalho.getCell(indiceColuna))
                        .trim();

                if (nomeColuna.isBlank()) {
                    throw new TestDataException(
                            "Cabeçalho vazio na coluna " + indiceColuna
                    );
                }

                String valor = formatter
                        .formatCellValue(linha.getCell(indiceColuna))
                        .trim();

                registro.put(nomeColuna, valor);
            }

            registros.add(registro);
        }

        if (registros.isEmpty()) {
            throw new TestDataException(
                    "A planilha não possui linhas de dados."
            );
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