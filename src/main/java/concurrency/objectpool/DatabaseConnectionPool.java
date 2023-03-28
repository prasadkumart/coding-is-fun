package concurrency.objectpool;

public class DatabaseConnectionPool extends ObjectPool<ExpensiveDatabaseConnection> {
    @Override
    protected ExpensiveDatabaseConnection create() {
        return new ExpensiveDatabaseConnection();
    }
}
