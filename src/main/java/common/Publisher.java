package common;

import java.util.ArrayList;
import java.util.List;

public class Publisher implements IPublisher {

    List<ISubscriber> subscribers = new ArrayList<>();

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
