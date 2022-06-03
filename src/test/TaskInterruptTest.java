package test;

import java.io.IOException;
import java.util.List;

import task.TaskContext;
import task.TaskRunner;
import util.storage.Storage;
import util.storage.StorageApi;
import util.storage.StorageServer;

public class TaskInterruptTest {
    public static void main(String[] args) throws InterruptedException, IOException {
        int storagePort = 8080;
        new StorageServer(
            new Storage(
                List.of(1,2,3,4)
            )
        ).listen(storagePort);

        new StorageApi(storagePort).obtain(0, 1);

        TaskRunner taskRunner = new TaskRunner(storagePort);
        taskRunner.runTask(new TaskContext());
        Thread.sleep(200);
        var task = taskRunner.interrupt();
        System.out.println("interrupt done");
        System.out.println("task result was: " + task.getResult());
    }
}
