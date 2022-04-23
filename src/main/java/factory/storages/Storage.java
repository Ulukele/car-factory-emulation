package factory.storages;

import factory.exceptions.StorageOverflowException;

import java.util.Queue;
import java.util.concurrent.SynchronousQueue;

public class Storage<T> implements IStorage<T> {

    private final Queue<T> itemsQueue;
    private final int capacity;

    public Storage(int capacity) {
        itemsQueue = new SynchronousQueue<>();
        this.capacity = capacity;
    }

    @Override
    public T getItem() {
        return itemsQueue.remove();
    }

    @Override
    public void storeItem(T item) throws StorageOverflowException {
        if (itemsQueue.size() == capacity) throw new StorageOverflowException();
        itemsQueue.add(item);
    }

    @Override
    public boolean isEmpty() {
        return itemsQueue.isEmpty();
    }

    @Override
    public int getCapacity() {
        return capacity;
    }

    @Override
    public int getItemsCount() {
        return itemsQueue.size();
    }
}
