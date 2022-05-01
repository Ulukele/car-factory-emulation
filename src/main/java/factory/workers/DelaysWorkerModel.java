package factory.workers;

import common.ISubscriber;
import common.ProducerStats;

import java.util.ArrayList;
import java.util.List;

abstract public class DelaysWorkerModel extends DelaysWorker implements Model<ProducerStats> {

    List<ISubscriber> subscribers = new ArrayList<>();
    public DelaysWorkerModel(long waitTime) {
        super(waitTime);
    }

    @Override
    public ProducerStats getData() {
        return new ProducerStats(getProducedCount(), getWaitTime());
    }

    abstract int getProducedCount();

    @Override
    public void addSubscriber(ISubscriber subscriber) {
        subscribers.add(subscriber);
    }

    @Override
    public void removeSubscriber(ISubscriber subscriber) {
        subscribers.remove(subscriber);
    }

    @Override
    public void publishNotify() {
        for (final ISubscriber subscriber : subscribers) {
            subscriber.reactOnNotify();
        }
    }
}
