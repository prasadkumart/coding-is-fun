package concurrency.objectpool;

public class ObjectPoolTest {
    public static void main(String[] args) {
        DatabaseConnectionPool pool = new DatabaseConnectionPool();

        //creates a new connection as free pool is empty
        ExpensiveDatabaseConnection con1 = pool.checkin();
        ExpensiveDatabaseConnection con2 = pool.checkin();
        ExpensiveDatabaseConnection con3 = pool.checkin();

        pool.checkout(con1);
        pool.checkout(con2);

        //consumes connection from free pool
        ExpensiveDatabaseConnection con4 = pool.checkin();
    }
}
