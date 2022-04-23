package factory;

import factory.items.Accessory;

public class AccessoryFactory extends IdentifiersCounter implements ItemsFactory<Accessory> {

    public AccessoryFactory(IndexManager indexManager) {
        assignIndexManager(indexManager);
    }

    @Override
    public Accessory create() {
        return new Accessory(getNewIdentifier());
    }
}
