package by.a1qa.klimov.framework.property;

import lombok.extern.slf4j.Slf4j;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static by.a1qa.klimov.framework.utils.Constants.PATH_DATA_PROPERTIES;

@Slf4j
public class DataProperties {
    public static final String PROPERTY_FILE_UPLOAD_ERROR = "Data property file upload error. File not found on path: ";

    private static Properties dataProperties = null;

    public static void setDataProperties() {
        if (dataProperties == null) {
            try (FileInputStream fis = new FileInputStream(PATH_DATA_PROPERTIES)) {
                dataProperties = new Properties();
                dataProperties.load(fis);
            } catch (IOException e) {
                log.error(PROPERTY_FILE_UPLOAD_ERROR + PATH_DATA_PROPERTIES, e);
                throw new NullPointerException();
            }
        }
    }

    public static String getDataPropertyByKey(String key) {
        if (dataProperties == null) setDataProperties();
        return dataProperties.getProperty(key);
    }
}
