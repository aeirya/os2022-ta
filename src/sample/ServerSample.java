package sample;

import java.io.IOException;

import util.network.connection.Connection;
import util.network.server.Server;

public class ServerSample {
    public static void main(String[] args) throws IOException {
        int port = 8080;

        new Server(
            connection -> {
                connection.send("HI");
                connection.close();
            }
        ).listen(port);
            
        var con = new Connection(8080);
        System.out.println(con.receive());
    }
}
