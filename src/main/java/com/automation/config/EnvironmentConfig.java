package com.automation.config;

import com.automation.utils.ConfigReader;

public class EnvironmentConfig {

    public static String getEnvironment() {

        return ConfigReader.get("environment");

    }

    public static String getBaseUrl() {

        String environment = getEnvironment();

        return ConfigReader.get(
                "base.url." + environment
        );

    }

}