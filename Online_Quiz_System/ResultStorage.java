package Online_Quiz_System;
import java.io.FileWriter;
import java.io.IOException;

public class ResultStorage {
    private static final String FILE_NAME = "Online_Quiz_System/results.txt";

    public static void saveResult(String userName, int score, int total) {
        try (FileWriter fw = new FileWriter(FILE_NAME, true)) {
            fw.write("User: " + userName + ", Score: " + score + "/" + total + "\n");
        } catch (IOException e) {
            System.out.println("Error saving result: " + e.getMessage());
        }
    }
}
