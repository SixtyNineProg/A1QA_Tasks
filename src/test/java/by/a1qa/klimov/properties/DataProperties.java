package by.a1qa.klimov.properties;

import lombok.extern.log4j.Log4j;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

@Log4j
public class DataProperties {
    private static Properties dataProperties = null;

    public static void setDataProperties() {
        log.info("Set data properties.");
        String pathToDataProperties = "./src/test/resources/testData.properties";
        if (dataProperties == null) {
            try (FileInputStream fis = new FileInputStream(pathToDataProperties)) {
                dataProperties = new Properties();
                dataProperties.load(fis);
            } catch (IOException e) {
                log.error("Data property file upload error. File not found on path: " + pathToDataProperties, e);
                throw new NullPointerException();
            }
        }
    }

    public static String getDataPropertyByKey(String key) {
        log.info("Get data property by key: " + key);
        if (dataProperties == null) setDataProperties();
        return dataProperties.getProperty(key);
    }
}
