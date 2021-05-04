package by.a1qa.klimov.utils;

import by.a1qa.klimov.properties.ConfigurationProperties;
import lombok.extern.log4j.Log4j;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
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

    //File upload by Robot Class
    public static void uploadFileWithRobot (String imagePath) {
        StringSelection stringSelection = new StringSelection(imagePath);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(stringSelection, null);

        Robot robot = null;

        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }

        if (robot != null) {
            robot.delay(250);
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);
            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.delay(150);
            robot.keyRelease(KeyEvent.VK_ENTER);
        }
    }
}
