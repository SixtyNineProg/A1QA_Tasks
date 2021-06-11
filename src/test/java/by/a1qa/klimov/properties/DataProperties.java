package by.a1qa.klimov.properties;

import aquality.selenium.core.logging.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class DataProperties {
    private static Properties dataProperties = null;

    public static void setDataProperties() {
        Logger.getInstance().info("Set data properties.");
        String pathToDataProperties = "./src/test/resources/testData.properties";
        if (dataProperties == null) {
            try (FileInputStream fis = new FileInputStream(pathToDataProperties)) {
                dataProperties = new Properties();
                dataProperties.load(fis);
            } catch (IOException e) {
                Logger.getInstance().fatal(
                        "Data property file upload error. File not found on path: " +
                                pathToDataProperties, e);
                throw new NullPointerException();
            }
        }
    }

    public static String getDataPropertyByKey(String key) {
        Logger.getInstance().info("Get data property by key: " + key);
        if (dataProperties == null) setDataProperties();
        return dataProperties.getProperty(key);
    }
}
