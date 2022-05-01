package factory.workers;

import common.IPublisher;

public interface Model<T> extends IPublisher {
    T getData();
}
