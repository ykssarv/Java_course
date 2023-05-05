package ee.taltech.iti0202.bookapi;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * The type Request.
 */
public class Request {

    /**
     * Save to file.
     *
     * @param json     the json
     * @param filename the filename
     * @throws IOException the io exception
     */
    public static void saveToFile(String json, String filename) throws IOException {
        FileWriter myWriter = new FileWriter(filename);
        myWriter.write(json);
        myWriter.close();
    }

    /**
     * Read from file string.
     *
     * @param filename the filename
     * @return the string
     * @throws FileNotFoundException the file not found exception
     */
    public static String readFromFile(String filename) throws FileNotFoundException {
        return readFromFile(new File(filename));
    }

    /**
     * Read from file string.
     *
     * @param file the file
     * @return the string
     * @throws FileNotFoundException the file not found exception
     */
    public static String readFromFile(File file) throws FileNotFoundException {
        Scanner scanner = new Scanner(file);
        scanner.useDelimiter("\\Z");
        return scanner.hasNext() ? scanner.next() : "";
    }
}
