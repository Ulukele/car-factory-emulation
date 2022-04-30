package factory.storages;


public interface IStorage<T> {
    T getItem();
    void storeItem(T item);
}
