import common.ValueCommandRecipient;
import control.ValueControl;
import factory.*;
import factory.exceptions.ConfigurationException;
import factory.items.Accessory;
import factory.items.Body;
import factory.items.Car;
import factory.items.Motor;
import factory.storages.Storage;
import factory.workers.Dealer;
import factory.workers.Supplier;
import factory.workers.Worker;
import threadpool.FixedSizeThreadPool;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class Main {
    static private final Logger logger = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {

        // Configure
        ConfigurationLoader configurationLoader = new ConfigurationLoader("factory-configuration.properties");
        FactoryConfiguration factoryConfiguration;
        try {
            factoryConfiguration = configurationLoader.getConfiguration();
        } catch (ConfigurationException e) {
            logger.severe(e.getLocalizedMessage());
            return;
        }

        // Create factories
        AccessoryFactory accessoryFactory = new AccessoryFactory(new SimpleIndexManager());
        BodyFactory bodyFactory = new BodyFactory(new SimpleIndexManager());
        MotorFactory motorFactory = new MotorFactory(new SimpleIndexManager());
        CarsBuilder carsBuilder = new CarsBuilder(new SimpleIndexManager());

        // Create storages
        Storage<Accessory> accessoryStorage = new Storage<>(factoryConfiguration.getStorageAccessoryCapacity());
        Storage<Body> bodyStorage = new Storage<>(factoryConfiguration.getStorageBodyCapacity());
        Storage<Motor> motorStorage = new Storage<>(factoryConfiguration.getStorageMotorCapacity());
        Storage<Car> carStorage = new Storage<>(factoryConfiguration.getStorageCarCapacity());

        // Create thread pool
        int totalWorkersCount = factoryConfiguration.getWorkersCount()   // Workers
                + factoryConfiguration.getDealersCount()                 // Dealers
                + factoryConfiguration.getAccessorySuppliersCount() + 2; // Suppliers

        FixedSizeThreadPool fixedSizeThreadPool = new FixedSizeThreadPool(totalWorkersCount);


        // Create suppliers
        long accessoryWait = factoryConfiguration.getAccessorySupplierWait();
        long bodyWait = factoryConfiguration.getBodySupplierWait();
        long motorWait = factoryConfiguration.getMotorSupplierWait();

        List<ValueCommandRecipient> suppliers = new ArrayList<>();
        for (int i = 0; i < factoryConfiguration.getAccessorySuppliersCount(); ++i) {
            Supplier<Accessory> supplier = new Supplier<>(accessoryWait, accessoryFactory, accessoryStorage);
            suppliers.add(supplier);
            fixedSizeThreadPool.submit(supplier);
        }

        Supplier<Body> bodySupplier = new Supplier<>(bodyWait, bodyFactory, bodyStorage);
        fixedSizeThreadPool.submit(bodySupplier);

        Supplier<Motor> motorSupplier = new Supplier<>(motorWait, motorFactory, motorStorage);
        fixedSizeThreadPool.submit(motorSupplier);

        // Load controller on suppliers models
        ValueControl accessoryWaitTimeControl = new ValueControl(suppliers);
        ValueControl bodyWaitTimeControl = new ValueControl(bodySupplier);
        ValueControl motorWaitTimeControl = new ValueControl(motorSupplier);

        // Create workers
        for (int i = 0; i < factoryConfiguration.getWorkersCount(); ++i) {
            Worker worker = new Worker(
                    factoryConfiguration.getWorkerWait(),
                    carsBuilder,
                    accessoryStorage,
                    bodyStorage,
                    motorStorage,
                    carStorage
                    );
            fixedSizeThreadPool.submit(worker);
        }

        // Create dealer
        Dealer dealer = new Dealer(
                factoryConfiguration.getDealerWait(),
                1,
                factoryConfiguration.isLogInfo(),
                carStorage
        );
        fixedSizeThreadPool.submit(dealer);

    }
}
