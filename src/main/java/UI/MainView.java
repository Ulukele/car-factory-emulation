package UI;

import common.ProducerStats;
import factory.FactoryConfiguration;
import factory.ProducersManager;
import factory.workers.Model;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;

public class MainView extends JFrame {

    private final ProducerPanel accessories;
    private final ProducerPanel body;
    private final ProducerPanel motor;
    private final ProducerPanel workers;
    private final ProducerPanel dealers;

    private final ProducersManager producersManager;

    public MainView(@NotNull FactoryConfiguration factoryConfiguration, ProducersManager producersManager) {
        super("Car factory emulation");
        this.producersManager = producersManager;

        // Base configuration of main frame
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        accessories = new ProducerPanel("Accessory suppliers");
        body = new ProducerPanel("Body supplier");
        motor = new ProducerPanel("Motor supplier");
        workers = new ProducerPanel("Car building workers");
        dealers = new ProducerPanel("Dealers");

        Container container = getContentPane();
        BoxLayout layout = new BoxLayout(getContentPane(), BoxLayout.PAGE_AXIS);
        setLayout(layout);

        container.add(accessories);
        container.add(body);
        container.add(motor);
        container.add(workers);
        container.add(dealers);

        pack();
    }

    public void connectModels() {
        body.addModel(producersManager.getBodySupplier());
        motor.addModel(producersManager.getMotorSupplier());
        for (final Model<ProducerStats> producerStatsModel : producersManager.getAccessorySuppliers()) {
            accessories.addModel(producerStatsModel);
        }
        for (final Model<ProducerStats> producerStatsModel : producersManager.getWorkers()) {
            workers.addModel(producerStatsModel);
        }
        for (final Model<ProducerStats> producerStatsModel : producersManager.getDealers()) {
            dealers.addModel(producerStatsModel);
        }
    }

    public void connectControl() {
        accessories.addControl(producersManager.getAccessoryControl());
        body.addControl(producersManager.getBodyControl());
        motor.addControl(producersManager.getMotorControl());
        workers.addControl(producersManager.getWorkerControl());
        dealers.addControl(producersManager.getDealerControl());
    }
}
