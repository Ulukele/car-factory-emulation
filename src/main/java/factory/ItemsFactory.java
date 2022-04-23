package factory;

import factory.items.Item;

public interface ItemsFactory<T extends Item> {
    T create();
    void assignIndexManager(IndexManager indexManager);
}
