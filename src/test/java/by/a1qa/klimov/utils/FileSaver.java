package by.a1qa.klimov.utils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class FileSaver {
    public static Boolean savePicture(String pathName, String formatFile, String url) throws IOException {
        BufferedImage bufferedImage = ImageIO.read(new URL(url));
        File outputFile = new File(pathName);
        ImageIO.write(bufferedImage, formatFile, outputFile);
        return true;
    }
}
