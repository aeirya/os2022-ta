package util.network.connection;

import java.io.IOException;
import java.io.PrintStream;
import java.io.Serializable;
import java.net.Socket;
import java.util.Scanner;
import java.util.function.Consumer;

import util.serialize.Serializer;

public class Connection {

    private final PrintStream out;
    private final Scanner in;

    private Thread listenThread;

    /** 
     * constructor used the server 
     * 
     * @param port port of the socket server accepting new connections
     * @throws IOException
     */
    public Connection(Socket socket) throws IOException {
        in = new Scanner(socket.getInputStream());
        out = new PrintStream(socket.getOutputStream());
    }

    /** constructor used by the clients
     * 
     * @param port port of the tcp server to connect to
     */
    public Connection(int port) throws IOException {
        this(
            new Socket("localhost", port)
        );
    }

    public void send(String message) {
        out.println(message);
    }

    public void send(Object obj) {
        this.send(obj.toString());
    }

    /**
     *  returns a new message from server (if available),
     *  otherwise blocks
     */
    public String receive() {
        return in.nextLine();
    }

    public boolean hasNextLine() {
        return in.hasNextLine();
    }

    public void sendSerialized(Serializable object) {
        send(new Serializer().serialize(object));
    }

    public void close() {
        in.close();
        out.close();
    }

    public void listen(Consumer<String> onRecieve) {
        listenThread = new Thread(() -> {
            onRecieve.accept(this.receive());
        });
        listenThread.start();
    }

    public void interrupt() {
        listenThread.interrupt();
    }
}
