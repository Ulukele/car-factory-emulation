package factory.workers;

import common.ValueCommandRecipient;

abstract public class DelaysWorker implements Runnable, ValueCommandRecipient {
    private long waitTime;

    public DelaysWorker(long waitTime) {
        this.waitTime = waitTime;
    }

    public long getWaitTime() {
        return waitTime;
    }

    public void setWaitTime(long waitTime) {
        this.waitTime = waitTime;
    }

    @Override
    public void run() {
        while (true) {
            customTask();
            try {
                Thread.sleep(waitTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void execute(long value) {
        long newValue = waitTime + value;
        if (newValue >= 0) waitTime = newValue;
    }

    abstract protected void customTask();
}
