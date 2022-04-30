package UI;

import factory.FactoryConfiguration;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

public class MainView extends JFrame {
    public MainView(@NotNull FactoryConfiguration factoryConfiguration) {
        super("Car factory emulation");

        // Base configuration of main frame
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        

    }

    private void connectModels() {

    }

    private void connectControl() {

    }
}
