package factory.workers;

import factory.ItemsFactory;
import factory.items.Item;
import factory.storages.IStorage;

public class Supplier<T extends Item> extends DelaysWorker implements Runnable {
    private final ItemsFactory<T> factory;
    private final IStorage<T> storage;

    public Supplier(int productionSpeed, ItemsFactory<T> factory, IStorage<T> storage) {
        super(productionSpeed);
        this.factory = factory;
        this.storage = storage;
    }

    private void produceAndStore() {
        T item = factory.create();
        storage.storeItem(item);
    }

    @Override
    public void run() {

    }

    @Override
    protected void customTask() {
        produceAndStore();
    }
}
