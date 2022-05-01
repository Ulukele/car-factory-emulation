package UI;

import common.ISubscriber;
import common.ProducerStats;
import control.ValueControl;
import factory.workers.Model;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ProducerPanel extends JPanel implements ISubscriber {

    private final String baseText;
    private final JLabel label;
    private final JSlider slider;
    private ValueControl valueControl;
    private final List<Model<ProducerStats>> producerStatsModels = new ArrayList<>();

    ProducerPanel(String baseText) {
        super();
        this.baseText = baseText;
        this.label = new JLabel(baseText + ": ");
        this.slider = new JSlider();

        slider.setMinimum(0);
        slider.setMaximum(1000000000);
        slider.addChangeListener(
                e -> changeHandler()
        );

        LayoutManager layout = new BoxLayout(this, BoxLayout.PAGE_AXIS);
        setLayout(layout);
        add(label);
        add(slider);
    }

    private void changeHandler() {
        if (valueControl == null) return;
        long value = slider.getValue();
        valueControl.execute(value);
    }

    public void addControl(ValueControl valueControl) {
        this.valueControl = valueControl;
    }

    public void addModel(Model<ProducerStats> producerStatsModel) {
        this.producerStatsModels.add(producerStatsModel);
        producerStatsModel.addSubscriber(this);
    }

    @Override
    public void reactOnNotify() {
        int sum = 0;
        for (final Model<ProducerStats> producerStats : producerStatsModels) {
            sum += producerStats.getData().getProduced();
        }
        label.setText(baseText + ": " + sum);
    }
}
