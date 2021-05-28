package by.a1qa.klimov.properties;

import aquality.selenium.core.logging.Logger;
import aquality.selenium.core.utilities.JsonSettingsFile;

public class ConfigurationData {
    private static JsonSettingsFile configurationFile = null;

    public static void setConfigurationProperties() {
        Logger.getInstance().info("Set configuration file.");
        configurationFile = new JsonSettingsFile("settings.json");
    }

    public static String getConfigurationPropertyByKey(String key) {
        Logger.getInstance().info("Get configuration property by key: " + key);
        if (configurationFile == null) setConfigurationProperties();
        return configurationFile.getValue("/" + key).toString();
    }
}
