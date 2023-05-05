package ee.taltech.iti0202.pokemon;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

/**
 * The type Request.
 */
public class Request {

    /**
     * Gets json from url.
     *
     * @param url the url
     * @return the json from url
     * @throws IOException the io exception
     */
    public static String getJsonFromUrl(String url) throws IOException {
        System.out.println("Actually doing a request");
        URL urlObject = new URL(url);
        HttpURLConnection con = (HttpURLConnection) urlObject.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("Content-Type", "application/json");

        BufferedReader in = new BufferedReader(
            new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuilder content = new StringBuilder();

        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }

        in.close();
        con.disconnect();

        return content.toString();
    }

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
        Scanner scanner = new Scanner(new File(filename));
        scanner.useDelimiter("\\Z");
        return scanner.next();
    }
}
