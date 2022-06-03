package test;

import java.io.IOException;

import util.network.connection.Connection;
import util.network.server.Server;

public class ConnectionInterruptTest {
    public static void main(String[] args) throws IOException, InterruptedException {
        new Server(con -> {
            try {
                Thread.sleep(3000);
                con.send("hello there");
                con.send("still listening?");
            } catch (InterruptedException e) {}

        }).listen(8080);

        var con = new Connection(8080);
        con.listen(System.out::println);
        con.interrupt();
        
        System.out.println("cancelled last listen");
        con.listen(System.out::println);
    }
}
