package util.storage;


import util.network.connection.Connection;
import util.network.server.AbstractServer;

public class StorageServer extends AbstractServer {
    private final Storage storage;

    public StorageServer() {
        storage = new Storage();
    }

    @Override
    public void acceptConnection(Connection connection) {
        new Thread(() -> {
            while (true) {
                serverConnection(connection);
            }
        }).start();
    }

    private void serverConnection(Connection connection) {
        StorageRequest request = StorageRequest.valueOf(connection.receive());
        switch (request) {
            case OBTAIN:
            // handleObtain(connection);

            Thread thread = new Thread(() -> {
                int value;
                try {
                    value = storage.obtain(
                        Integer.parseInt(connection.receive()),
                        Integer.parseInt(connection.receive()) 
                        );
                    connection.send(value);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });
            thread.start();
    
            while (thread.isAlive()) {
                if (connection.hasNextLine()) {
                    if (StorageRequest.valueOf(connection.receive()).equals(StorageRequest.CANCEL)) {
                        thread.interrupt();
                    }
                }
            }

            break;

            case RELEASE:
            // storage.release(index, id);
            break;

            case CANCEL:
            Thread.currentThread().interrupt();
        }
    }

    // private void handleObtain(Connection connection) {
        
    // }
}
