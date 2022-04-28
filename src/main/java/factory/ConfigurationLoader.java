package factory;

import factory.exceptions.ConfigurationException;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigurationLoader {
    private final String filename;

    public ConfigurationLoader(String filename) {
        this.filename = filename;
    }

    private Properties getProperties() throws IOException {
        Properties properties = new Properties();
        InputStream stream = ClassLoader.
                getSystemResourceAsStream(filename);
        properties.load(stream);
        return properties;
    }

    public FactoryConfiguration getConfiguration() throws ConfigurationException {
        Properties properties;
        try {
            properties = getProperties();
        } catch (IOException exception) {
            throw new ConfigurationException();
        }

        FactoryConfiguration factoryConfiguration;
        try{
            factoryConfiguration = new FactoryConfiguration(
                    Integer.parseInt(properties.getProperty("storageBodyCapacity")),
                    Integer.parseInt(properties.getProperty("storageMotorCapacity")),
                    Integer.parseInt(properties.getProperty("storageAccessoryCapacity")),
                    Integer.parseInt(properties.getProperty("storageCarCapacity")),
                    Integer.parseInt(properties.getProperty("accessorySuppliersCount")),
                    Integer.parseInt(properties.getProperty("workersCount")),
                    Integer.parseInt(properties.getProperty("dealersCount")),
                    Boolean.parseBoolean(properties.getProperty("logInfo"))
                    );
        } catch (NumberFormatException exception) {
            throw new ConfigurationException();
        }
        return factoryConfiguration;
    }
}
