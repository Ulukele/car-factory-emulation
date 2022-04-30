package factory.workers;

import common.ProducerStats;

abstract public class DelaysWorkerModel extends DelaysWorker implements Model<ProducerStats> {

    public DelaysWorkerModel(long waitTime) {
        super(waitTime);
    }

    @Override
    public ProducerStats getData() {
        return new ProducerStats(getProducedCount(), getWaitTime());
    }

    abstract int getProducedCount();
}
