package util.storage;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Semaphore;

public class Storage {
    private final Object lock = new Object();

    private final Map<Integer, Integer> data;   // index, value
    private Map<Integer, Integer> lockOwner; // index, owner

    private final Map<Integer, Semaphore> locks;

    public Storage() {
        data = new HashMap<>();
        lockOwner = new HashMap<>();
        locks = new HashMap<>();
    }

    public int obtain(int index, int id) throws InterruptedException {
        locks.get(index).acquire();
        lockOwner.put(index, id);
        return data.get(index);
    }

    public synchronized void release(int index, int id) {
        if (lockOwner.get(index) == id) {
            locks.get(index).release();
        }
    }
}
