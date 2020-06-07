package utils;

import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

import static com.codeborne.selenide.Selenide.sleep;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static helpers.Environment.selenoid_url;
import static org.apache.commons.io.FileUtils.copyURLToFile;

public class FileUtils {

    public static byte[] readBytesFromFile(String filePath) {
        File file = new File(filePath);
        try {
            return Files.readAllBytes(Paths.get(file.getAbsolutePath()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new byte[]{};
    }

    public static void getFileFromContainer(String exportFileName) {

        String session = ((RemoteWebDriver) getWebDriver()).getSessionId().toString();
        String path = "http://" + selenoid_url + ":4444/download/" + session + "/" + exportFileName;

        int retryCount = 1;
        while (retryCount <= 10) {
            try {
                copyURLToFile(new URL(path), new File("build/downloads/" + exportFileName));
                break;
            } catch (IOException e) {
                System.out.println("Файл не найден. Попытка " + retryCount + " из 10 " + path);
                sleep(1000);
                retryCount++;
            }
        }
    }

    public static String getFileNameFromUrl(String url) {
        return url.substring(url.lastIndexOf('/')).substring(1);
    }

}