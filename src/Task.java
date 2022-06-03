import java.io.InterruptedIOException;

import javax.xml.catalog.Catalog;

import util.storage.StorageApi;
import util.time.Time;

public class Task extends Thread {
    
    private int currentSum;
    
    private final StorageApi storage;
    private TaskContext currentContext;

    @Override
    public void run() {
        while (!interrupted() && !isFinished()) {
            int startTime = Time.getNowMillis();
            int timeToSleep = data.getSleepTime();

            try {
                Thread.sleep(timeToSleep);
            } catch (InterruptedIOException e) {
                int stopTime = Time.getNowMillis();
                currentContext.setLastSleepDuration(stopTime - startTime);
                throw e;
            }

            try {
                storage.obtain(indexToRead, currentContext.getID);
            }
            catch (InterruptedException ex) {
                currentContext.lastReadAttempt = indexToRead;
                throw e;
            }
        }
    }

    public TaskContext interrupt() {
        storage.interrupt();
        this.interrupt();

        return new TaskContext();
    }
}
