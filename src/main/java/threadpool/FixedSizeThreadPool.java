package threadpool;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class FixedSizeThreadPool {

    private final List<ThreadPoolWorker> workers;
    private final Queue<Runnable> queue = new ArrayDeque<>();

    public FixedSizeThreadPool(int threadsCount) {
        this.workers = new ArrayList<>(threadsCount);

        for (int i = 0; i < threadsCount; ++i) {
            ThreadPoolWorker worker = new ThreadPoolWorker();
            workers.add(worker);
            worker.start();
        }
    }

    public void submit(Runnable task) {
        synchronized (queue) {
            queue.add(task);
            queue.notify();
        }
    }

    private class ThreadPoolWorker extends Thread {
        public void run() {
            Runnable task;
            while (true) {
                synchronized (queue) {
                    if (queue.isEmpty()) {
                        try {
                            queue.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    task = queue.poll();
                }
                if (task != null) task.run();
            }
        }
    }

}
