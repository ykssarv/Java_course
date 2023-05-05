package ee.taltech.iti0202.coffee;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Logger {

    private static final int LOG_FREQUENCY = 5;

    private static int counter = 0;
    private static List<String> messages = new ArrayList<>();

    public static void log(String message) {
        counter++;
        messages.add(message);
        if (counter % LOG_FREQUENCY == 0) {
            writeToFile();
        }
    }

    public static void writeToFile() {
        try {
            FileWriter writer = new FileWriter("LOG.txt", true);
            for (String line : messages) {
                writer.write(line + "\n");
            }
            writer.close();
            messages.clear();
        } catch (IOException e) {
            System.out.println("Unable to log to file, logging to standard out instead");
            for (String line : messages) {
                System.out.println(line);
            }
            messages.clear();
        }
    }
}
