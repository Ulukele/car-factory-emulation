package factory;

import factory.items.Body;

public class BodyFactory extends IdentifiersCounter implements ItemsFactory<Body> {

    public BodyFactory(IndexManager indexManager) {
        assignIndexManager(indexManager);
    }

    @Override
    public Body create() {
        return new Body(getNewIdentifier());
    }
}
