// package test;

// import java.io.IOException;

// import util.storage.StorageApi;
// import util.storage.StorageServer;

// public class StorageTest {
//     public static void main(String[] args) throws IOException {
//         new StorageServer().listen(8081);

//         StorageApi storage = new StorageApi(8081);

//         System.out.println("the result was: " + storage.obtain(1, 1));
//     }
// }
