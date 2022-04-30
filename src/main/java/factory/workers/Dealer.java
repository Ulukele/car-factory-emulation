package factory.workers;

import common.ValueCommandRecipient;
import factory.items.Car;
import factory.storages.Storage;

import java.util.logging.Logger;

public class Dealer extends DelaysWorker implements Runnable, ValueCommandRecipient {

    private final Logger logger = Logger.getLogger(Dealer.class.getName());
    private final Storage<Car> carStorage;
    private final int idx;
    private final boolean useLogs;

    public Dealer(long waitTime, int idx, boolean useLogs, Storage<Car> carStorage) {
        super(waitTime);
        this.useLogs = useLogs;
        this.idx = idx;
        this.carStorage = carStorage;
    }

    private void requestAndSellCar() {
        Car car = carStorage.getItem();
        if (car == null || !useLogs) return;

        logger.info("Dealer " + idx +
                ": Auto <" + car.getId() +
                "> (Body <" + car.getBody().getId() +
                "> Motor <" + car.getMotor().getId() +
                "> Accessory <" + car.getAccessory().getId() +
                ">). Car rarity: " + car.getRarity()
                );
    }

    @Override
    protected void customTask() {
        requestAndSellCar();
    }
}
