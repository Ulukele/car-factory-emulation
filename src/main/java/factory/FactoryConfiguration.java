package factory;

public class FactoryConfiguration {
    private final int storageBodyCapacity;
    private final int storageMotorCapacity;
    private final int storageAccessoryCapacity;
    private final int storageCarCapacity;
    private final int accessorySuppliersCount;
    private final int workersCount;
    private final int dealersCount;
    private final long accessorySupplierWait;
    private final long bodySupplierWait;
    private final long motorSupplierWait;
    private final boolean logInfo;

    public FactoryConfiguration(
            int storageBodyCapacity,
            int storageMotorCapacity,
            int storageAccessoryCapacity,
            int storageCarCapacity,
            int accessorySuppliersCount,
            int workersCount,
            int dealersCount,
            long accessorySupplierWait,
            long bodySupplierWait,
            long motorSupplierWait,
            boolean logInfo
    ) {
        this.storageBodyCapacity = storageBodyCapacity;
        this.storageMotorCapacity = storageMotorCapacity;
        this.storageAccessoryCapacity = storageAccessoryCapacity;
        this.storageCarCapacity = storageCarCapacity;
        this.accessorySuppliersCount = accessorySuppliersCount;
        this.workersCount = workersCount;
        this.dealersCount = dealersCount;
        this.accessorySupplierWait = accessorySupplierWait;
        this.bodySupplierWait = bodySupplierWait;
        this.motorSupplierWait = motorSupplierWait;
        this.logInfo = logInfo;
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
        return accessorySuppliersCount;
    }

    public int getWorkersCount() {
        return workersCount;
    }

    public int getDealersCount() {
        return dealersCount;
    }

    public boolean isLogInfo() {
        return logInfo;
    }

    public long getAccessorySupplierWait() {
        return accessorySupplierWait;
    }

    public long getBodySupplierWait() {
        return bodySupplierWait;
    }

    public long getMotorSupplierWait() {
        return motorSupplierWait;
    }
}
