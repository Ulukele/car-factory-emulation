package factory.storages;

import factory.exceptions.StorageOverflowException;

public interface IStorage<T> {
    T getItem();
    void storeItem(T item) throws StorageOverflowException;
    boolean isEmpty();
    int getCapacity();
    int getItemsCount();
}
