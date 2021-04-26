package by.a1qa.klimov.framework.property;

import lombok.extern.slf4j.Slf4j;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

@Slf4j
public class DataProperties {
    private static Properties dataProperties = null;

    public static void setDataProperties() {
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
        if (dataProperties == null) setDataProperties();
        return dataProperties.getProperty(key);
    }
}
