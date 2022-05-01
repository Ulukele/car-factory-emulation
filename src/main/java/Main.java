import UI.MainView;
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

import javax.swing.*;
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

        List<Supplier<Accessory>> accessorySuppliers = new ArrayList<>();
        for (int i = 0; i < factoryConfiguration.getAccessorySuppliersCount(); ++i) {
            Supplier<Accessory> supplier = new Supplier<>(accessoryWait, accessoryFactory, accessoryStorage);
            accessorySuppliers.add(supplier);
            fixedSizeThreadPool.submit(supplier);
        }

        Supplier<Body> bodySupplier = new Supplier<>(bodyWait, bodyFactory, bodyStorage);
        fixedSizeThreadPool.submit(bodySupplier);

        Supplier<Motor> motorSupplier = new Supplier<>(motorWait, motorFactory, motorStorage);
        fixedSizeThreadPool.submit(motorSupplier);

        // Create workers
        List<Worker> workers = new ArrayList<>();
        for (int i = 0; i < factoryConfiguration.getWorkersCount(); ++i) {
            Worker worker = new Worker(
                    factoryConfiguration.getWorkerWait(),
                    carsBuilder,
                    accessoryStorage,
                    bodyStorage,
                    motorStorage,
                    carStorage
                    );
            workers.add(worker);
            fixedSizeThreadPool.submit(worker);
        }

        // Create dealer
        List<Dealer> dealers = new ArrayList<>();
        for (int i = 0; i < factoryConfiguration.getDealersCount(); ++i) {
            Dealer dealer = new Dealer(
                    factoryConfiguration.getDealerWait(),
                    i,
                    factoryConfiguration.isLogInfo(),
                    carStorage
            );
            dealers.add(dealer);
            fixedSizeThreadPool.submit(dealer);
        }

        ProducersManager producersManager = new ProducersManager(
                accessorySuppliers,
                bodySupplier,
                motorSupplier,
                workers,
                dealers
        );


        MainView viewApp = new MainView(factoryConfiguration, producersManager);

        SwingUtilities.invokeLater(() -> {
            viewApp.connectControl();
            viewApp.connectModels();
            viewApp.setVisible(true);
        });

    }
}
