package factory.items;

import java.util.concurrent.ThreadLocalRandom;

class BaseItem implements Item {
    private final int identifier;
    private final int rarity;

    public BaseItem(int identifier) {
        this.identifier = identifier;
        this.rarity = ThreadLocalRandom.current().nextInt(1, 100);
    }

    @Override
    public int getId() {
        return identifier;
    }

    @Override
    public int getRarity() {
        return rarity;
    }
}
