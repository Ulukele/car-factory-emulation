package factory.workers;

abstract public class DelaysWorker implements Runnable {
    private double speed;

    public DelaysWorker(double speed) {
        this.speed = speed;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    private double getTimePerTask() {
        return 1.0 / speed;
    }

    @Override
    public void run() {

    }

    abstract protected void customTask();
}
