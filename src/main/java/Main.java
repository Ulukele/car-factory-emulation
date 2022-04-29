import factory.*;
import factory.exceptions.ConfigurationException;
import factory.items.Accessory;
import factory.items.Body;
import factory.items.Motor;
import factory.storages.Storage;
import factory.workers.Supplier;
import threadpool.FixedSizeThreadPool;

import java.util.logging.Logger;

public class Main {
    static private Logger logger;
    public static void main(String[] args) {
        ConfigurationLoader configurationLoader = new ConfigurationLoader("factory-configuration.properties");
        FactoryConfiguration factoryConfiguration;
        try {
            factoryConfiguration = configurationLoader.getConfiguration();
        } catch (ConfigurationException e) {
            logger.severe(e.getLocalizedMessage());
            return;
        }

        AccessoryFactory accessoryFactory = new AccessoryFactory(new SimpleIndexManager());
        BodyFactory bodyFactory = new BodyFactory(new SimpleIndexManager());
        MotorFactory motorFactory = new MotorFactory(new SimpleIndexManager());

        Storage<Accessory> accessoryStorage = new Storage<>(factoryConfiguration.getStorageAccessoryCapacity());
        Storage<Body> bodyStorage = new Storage<>(factoryConfiguration.getStorageBodyCapacity());
        Storage<Motor> motorStorage = new Storage<>(factoryConfiguration.getStorageMotorCapacity());

        int totalWorkersCount = factoryConfiguration.getWorkersCount()   // Workers
                + factoryConfiguration.getDealersCount()                 // Dealers
                + factoryConfiguration.getAccessorySuppliersCount() + 2; // Suppliers

        FixedSizeThreadPool fixedSizeThreadPool = new FixedSizeThreadPool(totalWorkersCount);


        long accessoryWait = factoryConfiguration.getAccessorySupplierWait();
        long bodyWait = factoryConfiguration.getBodySupplierWait();
        long motorWait = factoryConfiguration.getMotorSupplierWait();

        for (int i = 0; i < factoryConfiguration.getAccessorySuppliersCount(); ++i) {
            Supplier<Accessory> supplier = new Supplier<>(accessoryWait, accessoryFactory, accessoryStorage);
            fixedSizeThreadPool.submit(supplier);
        }
        fixedSizeThreadPool.submit(new Supplier<>(bodyWait, bodyFactory, bodyStorage));
        fixedSizeThreadPool.submit(new Supplier<>(motorWait, motorFactory, motorStorage));


    }
}
