package by.a1qa.klimov.properties;

import aquality.selenium.core.logging.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;


public class ConfigurationProperties {
    private static Properties configurationProperties = null;

    public static void setConfigurationProperties() {
        Logger.getInstance().info("Set configuration properties.");
        String pathToConfigProperties = "./src/test/resources/config.properties";
        if (configurationProperties == null) {
            try (FileInputStream fis = new FileInputStream(pathToConfigProperties)) {
                configurationProperties = new Properties();
                configurationProperties.load(fis);
            } catch (IOException e) {
                Logger.getInstance().fatal(
                        "Configuration property file upload error. File not found on path: " +
                                pathToConfigProperties, e);
                throw new NullPointerException();
            }
        }
    }

    public static String getConfigurationPropertyByKey(String key) {
        Logger.getInstance().info("Get configuration property by key: " + key);
        if (configurationProperties == null) setConfigurationProperties();
        return configurationProperties.getProperty(key);
    }
}
