package com.automation.tests;

import com.automation.config.ConfigReader;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ConfigReaderTest {

    @Test
    void deveLerApplicationProperties() {

        String browser = ConfigReader.get("browser");

        System.out.println("Browser: " + browser);

        assertNotNull(browser);

    }
}
