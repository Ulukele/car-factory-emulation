package factory.workers;

import factory.ItemsFactory;
import factory.items.Item;

public class Supplier<T extends Item> {
    private int productionSpeed;
    private ItemsFactory<T> factory;

    public Supplier(int productionSpeed) {
        this.productionSpeed = productionSpeed;
    }

    public int getProductionSpeed() {
        return productionSpeed;
    }

    public void setProductionSpeed(int productionSpeed) {
        this.productionSpeed = productionSpeed;
    }
}
