package factory;

import common.ValueCommandRecipient;
import control.ValueControl;
import factory.items.Accessory;
import factory.items.Body;
import factory.items.Motor;
import factory.workers.Dealer;
import factory.workers.Supplier;
import factory.workers.Worker;

import java.util.ArrayList;
import java.util.List;

public class ProducersManager {
    private final List<Supplier<Accessory>> accessorySuppliers;

    private final Supplier<Body> bodySupplier;
    private final Supplier<Motor> motorSupplier;

    private final List<Worker> workers;

    private final List<Dealer> dealers;

    private ValueControl accessoryControl;

    private ValueControl bodyControl;
    private ValueControl motorControl;
    private ValueControl workerControl;
    private ValueControl dealerControl;
    public ProducersManager(
            List<Supplier<Accessory>> accessorySuppliers,
            Supplier<Body> bodySupplier,
            Supplier<Motor> motorSupplier,
            List<Worker> workers,
            List<Dealer> dealers
    ) {
        this.accessorySuppliers = accessorySuppliers;
        this.bodySupplier = bodySupplier;
        this.motorSupplier = motorSupplier;
        this.workers = workers;
        this.dealers = dealers;
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

    public List<Worker> getWorkers() {
        return workers;
    }

    public List<Dealer> getDealers() {
        return dealers;
    }

    public ValueControl getAccessoryControl() {
        if (accessoryControl == null) accessoryControl = new ValueControl(new ArrayList<>(accessorySuppliers));
        return accessoryControl;
    }

    public ValueControl getBodyControl() {
        if (bodyControl == null) bodyControl = new ValueControl(bodySupplier);
        return bodyControl;
    }

    public ValueControl getMotorControl() {
        if (motorControl == null) motorControl = new ValueControl(motorSupplier);
        return motorControl;
    }

    public ValueControl getWorkerControl() {
        if (workerControl == null) workerControl = new ValueControl(new ArrayList<>(workers));
        return workerControl;
    }

    public ValueControl getDealerControl() {
        if (dealerControl == null) dealerControl = new ValueControl(new ArrayList<>(dealers));
        return dealerControl;
    }

}
