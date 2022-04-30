package factory;

import factory.items.Accessory;
import factory.items.Body;
import factory.items.Motor;
import factory.workers.Supplier;

import java.util.List;

public class ProducersManager {
    private final List<Supplier<Accessory>> accessorySuppliers;

    private final Supplier<Body> bodySupplier;
    private final Supplier<Motor> motorSupplier;
    public ProducersManager(List<Supplier<Accessory>> accessorySuppliers, Supplier<Body> bodySupplier, Supplier<Motor> motorSupplier) {
        this.accessorySuppliers = accessorySuppliers;
        this.bodySupplier = bodySupplier;
        this.motorSupplier = motorSupplier;
    }

    public List<Supplier<Accessory>> getAccessorySuppliers() {
        return accessorySuppliers;
    }

    public Supplier<Body> getBodySupplier() {
        return bodySupplier;
    }

    public Supplier<Motor> getMotorSupplier() {
        return motorSupplier;
    }

}
