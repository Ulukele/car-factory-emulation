package factory.storages;


import java.util.ArrayDeque;
import java.util.Queue;
import java.util.logging.Logger;

public class Storage<T> implements IStorage<T> {

    private final Queue<T> itemsQueue;
    private final int capacity;

    private final Logger logger = Logger.getLogger(Storage.class.getName());

    public Storage(int capacity) {
        itemsQueue = new ArrayDeque<>();
        this.capacity = capacity;
    }

    @Override
    synchronized public T getItem() {
        T item = itemsQueue.remove();
        notify();
        return item;
    }

    @Override
    synchronized public void storeItem(T item) {
        if (isFull()) {
            try {
                wait();
            } catch (InterruptedException exception) {
                logger.warning(exception.getMessage());
            }
        }
        itemsQueue.add(item);
    }

    @Override
    synchronized public boolean isEmpty() {
        return itemsQueue.isEmpty();
    }

    @Override
    synchronized public boolean isFull() {
        return itemsQueue.size() >= capacity;
    }

    @Override
    public int getCapacity() {
        return capacity;
    }

    @Override
    synchronized public int getItemsCount() {
        return itemsQueue.size();
    }
}
