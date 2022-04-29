package factory.workers;

import common.ValueCommandRecipient;
import factory.items.Car;
import factory.storages.Storage;

import java.util.logging.Logger;

public class Dealer extends DelaysWorker implements Runnable, ValueCommandRecipient {

    private final Logger logger = Logger.getLogger(Dealer.class.getName());
    private final Storage<Car> carStorage;
    private final int idx;

    public Dealer(long waitTime, int idx, Storage<Car> carStorage) {
        super(waitTime);
        this.idx = idx;
        this.carStorage = carStorage;
    }

    private void requestAndSellCar() {
        carStorage.getItem();
        logger.info("Dealer " + idx); // TODO fill info
    }

    @Override
    protected void customTask() {
        requestAndSellCar();
    }
}
