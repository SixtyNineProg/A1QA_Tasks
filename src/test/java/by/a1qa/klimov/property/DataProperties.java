package by.a1qa.klimov.property;

import by.a1qa.klimov.utils.Constants;
import lombok.extern.slf4j.Slf4j;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static by.a1qa.klimov.utils.Constants.PATH_CONFIGURATION_PROPERTIES;
import static by.a1qa.klimov.utils.Constants.PATH_DATA_PROPERTIES;

@Slf4j
public class DataProperties {
    private static Properties dataProperties = null;

    public static Properties getDataProperties() {
        if (dataProperties == null) {
            try (FileInputStream fis = new FileInputStream(PATH_DATA_PROPERTIES)) {
                dataProperties = new Properties();
                dataProperties.load(fis);
            } catch (IOException e) {
                log.error(Constants.PROPERTY_FILE_UPLOAD_ERROR + PATH_DATA_PROPERTIES, e);
                throw new NullPointerException();
            }
        }
        return dataProperties;
    }

    public static String getDataPropertyByKey(String key) {
        if (dataProperties == null) getDataProperties();
        return dataProperties.getProperty(key);
    }
}
