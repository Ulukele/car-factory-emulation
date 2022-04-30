package factory;

public class SimpleIndexManager implements IndexManager {
    private int lastUnused = 0;

    private void generateNewIndex() {
        if (lastUnused == Integer.MAX_VALUE) {
            lastUnused = 0;
        }
        lastUnused++;
    }

    @Override
    synchronized public int getIndex() {
        int value = lastUnused;
        generateNewIndex();
        return value;
    }
}
