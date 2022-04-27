package factory.storages;


public interface IStorage<T> {
    T getItem();
    void storeItem(T item);
    boolean isEmpty();
    boolean isFull();
    int getCapacity();
    int getItemsCount();
}
