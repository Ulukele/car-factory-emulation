package factory.workers;

import factory.CarsBuilder;
import factory.items.Accessory;
import factory.items.Body;
import factory.items.Car;
import factory.items.Motor;
import factory.storages.Storage;

public class Worker extends DelaysWorker implements Runnable {
    private final CarsBuilder carsBuilder;
    private final Storage<Accessory> accessoryStorage;
    private final Storage<Body> bodyStorage;
    private final Storage<Motor> motorStorage;
    private final Storage<Car> carStorage;

    public Worker(
            double workSpeed,
            CarsBuilder carsBuilder,
            Storage<Accessory> accessoryStorage,
            Storage<Body> bodyStorage,
            Storage<Motor> motorStorage, Storage<Car> carStorage) {
        super(workSpeed);
        this.carsBuilder = carsBuilder;
        this.accessoryStorage = accessoryStorage;
        this.bodyStorage = bodyStorage;
        this.motorStorage = motorStorage;
        this.carStorage = carStorage;
    }

    private void buildAndStore() {
        Accessory accessory = accessoryStorage.getItem();
        Body body = bodyStorage.getItem();
        Motor motor = motorStorage.getItem();

        Car car = carsBuilder.buildCar(accessory, body, motor);
        carStorage.storeItem(car);
    }

    @Override
    protected void customTask() {
        buildAndStore();
    }
}
