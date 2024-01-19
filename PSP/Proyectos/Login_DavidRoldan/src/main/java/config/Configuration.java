package config;

import common.Constants;
import jakarta.inject.Singleton;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Singleton
public class Configuration {
    private final Properties p;

    private Configuration() {
        p = new Properties();
        InputStream propertiesStream;
        propertiesStream = this.getClass().getResourceAsStream(Constants.PROPERTIES_XML);
        try {
            p.loadFromXML(propertiesStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getProperty(String key) {
        return p.getProperty(key);
    }
}
