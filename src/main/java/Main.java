import factory.ConfigurationLoader;
import factory.FactoryConfiguration;
import factory.exceptions.ConfigurationException;

import java.util.logging.Logger;

public class Main {
    static private Logger logger;
    public static void main(String[] args) {
        ConfigurationLoader configurationLoader = new ConfigurationLoader("factory-configuration.properties");
        try {
            FactoryConfiguration factoryConfiguration = configurationLoader.getConfiguration();
        } catch (ConfigurationException e) {
            logger.severe(e.getLocalizedMessage());
        }
    }
}
