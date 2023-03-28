package concurrency.objectpool;

import java.util.HashSet;
import java.util.Set;

public abstract class ObjectPool<T> {
    private final Set<T> free = new HashSet<>();
    private final Set<T> locked = new HashSet<>();
    protected abstract T create();

    public synchronized void checkout(T instance) {
        locked.remove(instance);
        free.add(instance);
        System.out.println("Release connection... " + this);
    }

    public synchronized T checkin() {
        if (free.isEmpty()) {
            free.add(create());
        }
        final T instance = free.iterator().next();
        free.remove(instance);
        locked.add(instance);
        System.out.println("Consumed connection... "+ this);
        return instance;
    }

    @Override
    public synchronized String toString() {
        return String.format("Pool state count: free=%d locked=%d", free.size(), locked.size());
    }
}
