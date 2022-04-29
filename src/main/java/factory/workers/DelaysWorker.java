package factory.workers;

abstract public class DelaysWorker implements Runnable {
    private long waitTime;

    public DelaysWorker(long waitTime) {
        this.waitTime = waitTime;
    }

    public double getWaitTime() {
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

    abstract protected void customTask();
}
