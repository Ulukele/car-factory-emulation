package factory.items;

class BaseItem implements Item {
    private final int identifier;
    private final int rarity;

    public BaseItem(int identifier) {
        this.identifier = identifier;
        this.rarity = 0;
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
