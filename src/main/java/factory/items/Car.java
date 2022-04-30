package factory.items;


public class Car extends BaseItem implements Item {
    private final Accessory accessory;
    private final Body body;
    private final Motor motor;

    public Car(Accessory accessory, Body body, Motor motor, int identifier) {
        super(identifier);
        this.accessory = accessory;
        this.body = body;
        this.motor = motor;
    }

    public Accessory getAccessory() {
        return accessory;
    }

    public Body getBody() {
        return body;
    }

    public Motor getMotor() {
        return motor;
    }

    @Override
    public int getRarity() {
        return getAccessory().getRarity() * getMotor().getRarity() * getBody().getRarity();
    }
}
