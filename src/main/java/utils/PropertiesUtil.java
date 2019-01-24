package utils;

import java.io.InputStream;
import java.util.Properties;

public class PropertiesUtil {
    public static Properties properties = null;
    static {
        InputStream stream = ClassLoader.getSystemResourceAsStream("UserDraw.properties");
        properties = new Properties();
        try {
            properties.load(stream);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static String getProperty(String key){
        return properties.getProperty(key);
    }
}
