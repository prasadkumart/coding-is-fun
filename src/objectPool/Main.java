package concurrency.objectPool;

public class Main {
    public static void main(String[] args) {
        DatabaseConnectionPool pool = new DatabaseConnectionPool();

        ExpensiveDatabaseConnection con1 = pool.checkOut();
        ExpensiveDatabaseConnection con2 = pool.checkOut();
        ExpensiveDatabaseConnection con3 = pool.checkOut();
        pool.checkIn(con1); // freeTime T = 1
        pool.checkIn(con2);// FreeTime T = 2

        ExpensiveDatabaseConnection con4 = pool.checkOut(); // T = 3, 1st object freeTime T = 1 -> T -1. Object moved
        // from free -> locked Set
        pool.checkIn(con3);
        pool.checkIn(con4);
    }
}
