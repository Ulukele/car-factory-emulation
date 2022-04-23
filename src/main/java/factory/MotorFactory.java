package factory;

import factory.items.Motor;

public class MotorFactory extends IdentifiersCounter implements ItemsFactory<Motor> {

    public MotorFactory(IndexManager indexManager) {
        assignIndexManager(indexManager);
    }

    @Override
    public Motor create() {
        return new Motor(getNewIdentifier());
    }
}
