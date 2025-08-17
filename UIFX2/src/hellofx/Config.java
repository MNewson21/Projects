package hellofx;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;


//This is no longer used for my files since I added policies to the db
public class Config {
    private static Properties props = new Properties();

    static {
        try {
            props.load(new FileInputStream("config.properties"));
        } catch (IOException e) {
            throw new RuntimeException("Could not load config.properties", e);
        }
    }

    public static String get(String key) {
        return props.getProperty(key);
    }
}
