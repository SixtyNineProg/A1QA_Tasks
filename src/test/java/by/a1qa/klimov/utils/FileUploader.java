package by.a1qa.klimov.utils;

import by.a1qa.klimov.properties.ConfigurationProperties;
import by.a1qa.klimov.properties.DataProperties;
import lombok.extern.log4j.Log4j;

import java.io.File;
import java.io.IOException;

@Log4j
public class FileUploader {
    /**
     * Заполнить поле файлового менеджера
     * @param filePath - абсолютный путь к файлу
     */
    public static void setUploadedFile(String filePath) {

        File autoIt = new File(ConfigurationProperties.getConfigurationPropertyByKey("pathToScriptUploadFile"));

        try {
            // запуск exe с передачей пути к загружаемому файлу
            // в качестве параметра
            Process p = Runtime.getRuntime().exec(
                    autoIt.getAbsolutePath() + " " + filePath);
            // ожидание выполнения запроса
            p.waitFor();
        } catch (IOException | InterruptedException e) {
            log.error("File don't upload.", e);
        }
    }
}
