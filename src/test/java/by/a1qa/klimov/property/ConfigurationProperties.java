package by.a1qa.klimov.property;

import by.a1qa.klimov.utils.Constants;
import lombok.extern.slf4j.Slf4j;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static by.a1qa.klimov.utils.Constants.PATH_CONFIGURATION_PROPERTIES;

@Slf4j
public class ConfigurationProperties {
    private static Properties configurationProperties = null;

    public static Properties getConfigurationProperties() {
        if (configurationProperties == null) {
            try (FileInputStream fis = new FileInputStream(PATH_CONFIGURATION_PROPERTIES)) {
                configurationProperties = new Properties();
                configurationProperties.load(fis);
            } catch (IOException e) {
                log.error(Constants.PROPERTY_FILE_UPLOAD_ERROR + PATH_CONFIGURATION_PROPERTIES, e);
            }
        }
        return configurationProperties;
    }
}
