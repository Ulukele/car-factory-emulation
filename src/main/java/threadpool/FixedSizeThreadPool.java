package threadpool;

import java.util.ArrayList;
import java.util.List;

public class FixedSizeThreadPool {

    private final int threadsCount;
    private final List<Thread> threads;

    public FixedSizeThreadPool(int threadsCount) {
        this.threadsCount = threadsCount;
        this.threads = new ArrayList<>(threadsCount);
    }

    public void submit(Runnable task) {

    }

    public void terminate() {

    }

    public void waitCompletion() {

    }
}
