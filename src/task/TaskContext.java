package task;
import java.io.Serializable;

public class TaskContext implements Serializable {
    /**
     * List of read indicies
     * List of sleeps
     * 
     * Total time slept
     * Sum of read numbers
     * 
     */

    private int lastSleepDuration;
    private int currentSum;

    public TaskContext() {
        currentSum = 0;
        lastSleepDuration = 0;
    }

    public void setLastSleepDuration(int duration) {
        this.lastSleepDuration = duration;
    }

    public void setLastReadAttempt(int index) {
        /* TODO */
    }

    public int getNextSleep() {
        if (lastSleepDuration != 0) {
            return lastSleepDuration;
        } else {
            /* TODO */
            return 0;
        }
    }

    public int getNextReadIndex() {
        /* TODO */
        return 0;
    }

    public int getID() {
        /* TODO */
        return 0;
    }

    public int getResult() {
        return currentSum;
    }
}
