package Chat_Application;
import java.net.*;
import java.io.*;

public class client {
    public static void main(String[] args) {
        try (Socket socket = new Socket("127.0.0.1", 2020)) {
            System.out.println("Connected to server!");

            // Streams for reading/writing
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader console = new BufferedReader(new InputStreamReader(System.in));

            // Read/Send threads
            Thread readThread = new Thread(() -> {
                try {
                    String msg;
                    while ((msg = in.readLine()) != null) {
                        if (msg.equalsIgnoreCase("exit")) {
                            System.out.println("Server exited.");
                            System.exit(0);
                        }
                        System.out.println("Server: " + msg);
                    }
                } catch (IOException e) {}
            });

            Thread writeThread = new Thread(() -> {
                try {
                    String msg;
                    while ((msg = console.readLine()) != null) {
                        out.println(msg);
                        if (msg.equalsIgnoreCase("exit")) {
                            System.out.println("You exited.");
                            System.exit(0);
                        }
                    }
                } catch (IOException e) {}
            });

            readThread.start();
            writeThread.start();
            readThread.join();
            writeThread.join();
        } catch (Exception e) {
            System.out.println("Client error: " + e.getMessage());
        }
    }
}
