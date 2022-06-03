package util.storage;

import java.io.IOException;

import util.network.connection.Connection;

public class StorageApi {
    private final Connection connection;
    private boolean interrupted;

    public StorageApi(int storagePort) throws IOException {
        connection = new Connection(storagePort);
    }

    public int obtain(int index, int id) throws InterruptedException {
        connection.send(StorageRequest.OBTAIN);
        connection.send(index);
        connection.send(id);

        String result = "";
        while (!interrupted) {
            if (connection.hasNextLine()) {
                result = connection.receive();
                break;
            }
        }
        if (interrupted) {
            interrupted = false;
            connection.send(StorageRequest.CANCEL);
            throw new InterruptedException();
        }

        return Integer.parseInt(result);
    }

    public void release() {
        
    }

    public void interrupt() {
        interrupted = true;
    }
}
