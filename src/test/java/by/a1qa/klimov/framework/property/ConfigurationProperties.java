package by.a1qa.klimov.framework.property;

import lombok.extern.slf4j.Slf4j;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static by.a1qa.klimov.framework.utils.Constants.PATH_CONFIGURATION_PROPERTIES;

@Slf4j
public class ConfigurationProperties {
    public static final String PROPERTY_FILE_UPLOAD_ERROR = "Configuration property file upload error. File not found on path: ";

    private static Properties configurationProperties = null;

    public static void setConfigurationProperties() {
        if (configurationProperties == null) {
            try (FileInputStream fis = new FileInputStream(PATH_CONFIGURATION_PROPERTIES)) {
                configurationProperties = new Properties();
                configurationProperties.load(fis);
            } catch (IOException e) {
                log.error(PROPERTY_FILE_UPLOAD_ERROR + PATH_CONFIGURATION_PROPERTIES, e);
                throw new NullPointerException();
            }
        }
    }

    public static String getConfigurationPropertyByKey(String key) {
        if (configurationProperties == null) setConfigurationProperties();
        return configurationProperties.getProperty(key);
    }
}
