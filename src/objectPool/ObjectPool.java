package concurrency.objectPool;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class ObjectPool<T> {


    private final Set<T> free = new HashSet<>();
    private final Set<T> locked = new HashSet<>();
    protected abstract T create();

    public synchronized void checkIn(T instance) {
        locked.remove(instance);
        free.add(instance);
        System.out.println(this);
    }

    public synchronized T checkOut() {
        if (free.isEmpty()) {
            T instance = create();
            locked.add(instance);
            return instance;
        }

        T instance = free.iterator().next();
        free.remove(instance);
        locked.add(instance);
        System.out.println(this);
        return instance;
    }

    @Override
    public synchronized String toString() {
        return String.format("Pool state count: free=%d locked=%d", free.size(), locked.size());
    }
}
