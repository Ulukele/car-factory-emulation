package common;


/**
 * Value object to store
 * data about factory producers
 */

public class ProducerStats {
    private final int produced;

    private final long waitTime;

    public ProducerStats(int produced, long waitTime) {
        this.produced = produced;
        this.waitTime = waitTime;
    }

    public int getProduced() {
        return produced;
    }

    public long getWaitTime() {
        return waitTime;
    }
}
