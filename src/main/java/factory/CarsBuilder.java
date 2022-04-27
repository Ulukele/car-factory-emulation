package factory;

import factory.items.Accessory;
import factory.items.Body;
import factory.items.Car;
import factory.items.Motor;

public class CarsBuilder extends IdentifiersCounter {

    public CarsBuilder(IndexManager indexManager) {
        assignIndexManager(indexManager);
    }

    public Car buildCar(Accessory accessory, Body body, Motor motor) {
        return new Car(accessory, body, motor, getNewIdentifier());
    }

}
