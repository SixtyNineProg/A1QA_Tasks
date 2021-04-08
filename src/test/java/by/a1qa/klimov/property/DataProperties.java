package by.a1qa.klimov.property;

import by.a1qa.klimov.utils.Constants;
import lombok.extern.slf4j.Slf4j;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

@Slf4j
public class DataProperties {
    private static Properties properties = null;

    public static Properties getProperties() throws Exception {
        if (properties == null) {
            try (FileInputStream fis = new FileInputStream("./data.properties")) {
                properties = new Properties();
                properties.load(fis);
            } catch (IOException e) {
                log.error(Constants.PROPERTY_FILE_UPLOAD_ERROR, e);
                throw new Exception();
            }
        }
        return properties;
    }
}
