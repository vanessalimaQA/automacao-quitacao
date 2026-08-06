package com.automation.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {

    private static final Properties properties = new Properties();

    static {
        carregarArquivo("application.properties");
        carregarArquivo("users.properties");
    }

    private static void carregarArquivo(String nomeArquivo) {
        try (InputStream inputStream = ConfigReader.class
                .getClassLoader()
                .getResourceAsStream(nomeArquivo)) {

            if (inputStream == null) {
                throw new RuntimeException("Arquivo nÃ£o encontrado: " + nomeArquivo);
            }

            properties.load(inputStream);

        } catch (IOException e) {
            throw new RuntimeException("Erro ao carregar arquivo: " + nomeArquivo, e);
        }
    }

    public static String get(String key) {
        return properties.getProperty(key);
    }
}
