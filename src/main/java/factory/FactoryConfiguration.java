package factory;

public class FactoryConfiguration {
    private int storageBodyCapacity = 100;
    private int storageMotorCapacity = 100;
    private int storageAccessoryCapacity = 100;
    private int storageCarCapacity = 100;
    private int AccessorySuppliersCount = 5;
    private int WorkersCount = 10;
    private int DealersCount = 10;
    private boolean LogInfo = true;

    public FactoryConfiguration(
            int storageBodyCapacity,
            int storageMotorCapacity,
            int storageAccessoryCapacity,
            int storageCarCapacity,
            int accessorySuppliersCount,
            int workersCount,
            int dealersCount,
            boolean logInfo
    ) {
        this.storageBodyCapacity = storageBodyCapacity;
        this.storageMotorCapacity = storageMotorCapacity;
        this.storageAccessoryCapacity = storageAccessoryCapacity;
        this.storageCarCapacity = storageCarCapacity;
        AccessorySuppliersCount = accessorySuppliersCount;
        WorkersCount = workersCount;
        DealersCount = dealersCount;
        LogInfo = logInfo;
    }

    public int getStorageBodyCapacity() {
        return storageBodyCapacity;
    }

    public int getStorageMotorCapacity() {
        return storageMotorCapacity;
    }

    public int getStorageAccessoryCapacity() {
        return storageAccessoryCapacity;
    }

    public int getStorageCarCapacity() {
        return storageCarCapacity;
    }

    public int getAccessorySuppliersCount() {
        return AccessorySuppliersCount;
    }

    public int getWorkersCount() {
        return WorkersCount;
    }

    public int getDealersCount() {
        return DealersCount;
    }

    public boolean isLogInfo() {
        return LogInfo;
    }
}
