package ee.taltech.iti0202.files.input;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InputFilesScanner implements InputFilesReader {

    @Override
    public List<String> readTextFromFile(String filename) {
        File text = new File(filename);
        List<String> lines = new ArrayList<>();
        Scanner scanner;
        try {
            scanner = new Scanner(text);
        } catch (FileNotFoundException e) {
            throw new FileReaderException("No such file", e);
        }
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            lines.add(line);
        }
        return lines;
    }
}
