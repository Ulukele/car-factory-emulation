package factory.workers;

import common.ProducerStats;
import common.ValueCommandRecipient;
import factory.ItemsFactory;
import factory.items.Item;
import factory.storages.IStorage;

public class Supplier<T extends Item> extends DelaysWorkerModel
        implements Runnable, ValueCommandRecipient, Model<ProducerStats> {
    private final ItemsFactory<T> factory;
    private final IStorage<T> storage;

    private int supplied = 0;

    public Supplier(long waitTime, ItemsFactory<T> factory, IStorage<T> storage) {
        super(waitTime);
        this.factory = factory;
        this.storage = storage;
    }

    private void produceAndStore() {
        T item = factory.create();
        storage.storeItem(item);
    }

    @Override
    protected void customTask() {
        produceAndStore();
        supplied++;
        publishNotify();
    }

    @Override
    int getProducedCount() {
        return supplied;
    }
}
