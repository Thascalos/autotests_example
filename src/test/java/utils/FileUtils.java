package utils;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertNotNull;

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

    public String readStringFromFile(String filePath) {
        return new String(readBytesFromFile(filePath));
    }

    public void saveFile(String content, String filePath)  {
        File file = new File(filePath);
        try {
            file.getParentFile().mkdirs();
            file.createNewFile();
            PrintWriter writer = new PrintWriter(file, "UTF-8");
            writer.write(content);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String resourcePath(String file) {
        URL resource = Thread.currentThread().getContextClassLoader().getResource(file);
        assertNotNull(resource, "Resource not found in classpath: " + file);
        return resource.getFile();
    }

}