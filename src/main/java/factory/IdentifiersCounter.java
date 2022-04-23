package factory;

abstract public class IdentifiersCounter {
    private IndexManager indexManager;

    protected int getNewIdentifier() {
        if (indexManager == null) return 0;
        return indexManager.getIndex();
    }

    public void assignIndexManager(IndexManager indexManager) {
        this.indexManager = indexManager;
    }
}
