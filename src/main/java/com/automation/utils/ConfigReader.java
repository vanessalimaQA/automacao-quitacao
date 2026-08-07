package com.automation.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;
import java.util.Properties;

public final class ConfigReader {

    private static final Properties PROPERTIES = new Properties();

    static {
        carregarArquivo("application.properties");
        carregarArquivo("users.properties");
    }

    private ConfigReader() {
    }

    private static void carregarArquivo(String nomeArquivo) {

        try (InputStream inputStream = ConfigReader.class
                .getClassLoader()
                .getResourceAsStream(nomeArquivo)) {

            if (inputStream == null) {
                throw new IllegalStateException(
                        "Arquivo de configuração não encontrado: " + nomeArquivo
                );
            }

            Properties propriedadesArquivo = new Properties();
            propriedadesArquivo.load(inputStream);

            propriedadesArquivo.forEach((chave, valor) -> {

                String chaveNormalizada =
                        chave.toString()
                                .replace("\uFEFF", "")
                                .trim();

                PROPERTIES.setProperty(
                        chaveNormalizada,
                        valor.toString().trim()
                );
            });

        } catch (IOException e) {
            throw new IllegalStateException(
                    "Erro ao carregar arquivo de configuração: " + nomeArquivo,
                    e
            );
        }
    }

    public static String get(String chave) {

        String valor = buscarValor(chave);

        if (valor == null || valor.isBlank()) {
            throw new IllegalStateException(
                    "Configuração obrigatória não encontrada: " + chave
            );
        }

        return valor.trim();
    }

    public static String getOptional(
            String chave,
            String valorPadrao
    ) {

        String valor = buscarValor(chave);

        if (valor == null || valor.isBlank()) {
            return valorPadrao;
        }

        return valor.trim();
    }

    private static String buscarValor(String chave) {

        String valorJVM = System.getProperty(chave);

        if (valorJVM != null && !valorJVM.isBlank()) {
            return valorJVM;
        }

        String nomeVariavelAmbiente =
                chave.toUpperCase(Locale.ROOT)
                        .replace(".", "_")
                        .replace("-", "_");

        String valorAmbiente =
                System.getenv(nomeVariavelAmbiente);

        if (valorAmbiente != null && !valorAmbiente.isBlank()) {
            return valorAmbiente;
        }

        return PROPERTIES.getProperty(chave);
    }
}