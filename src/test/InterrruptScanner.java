// package test;

// import java.util.Scanner;

// public class InterrruptScanner {
//     public static void main(String[] args) throws InterruptedException {
//         Scanner scanner = new Scanner(System.in);

//         Thread thread = new Thread(() -> {
//             scanner.nextLine();
//         });
//         thread.start();
//         Thread.sleep(1000);
//         thread.interrupt();
//         thread.yield();
//     }
// }
