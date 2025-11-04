package Utils;


import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    private static Properties properties;

    public static void loadConfig() {
        if (properties == null) {
            properties = new Properties();
            try (FileInputStream fis = new FileInputStream("src/test/resources/config.properties")) {
                properties.load(fis);
            } catch (IOException e) {
                throw new RuntimeException("Failed to load config.properties file: " + e.getMessage());
            }
        }
    }

    public static String getProperty(String key) {
        if (properties == null) {
            loadConfig();
        }
        return properties.getProperty(key);
    }
}
