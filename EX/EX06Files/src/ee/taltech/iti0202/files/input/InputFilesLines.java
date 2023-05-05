package ee.taltech.iti0202.files.input;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class InputFilesLines implements InputFilesReader {

    @Override
    public List<String> readTextFromFile(String filename) {
        try {
            return Files.lines(Paths.get(filename)).collect(Collectors.toList());
        } catch (IOException e) {
            throw new FileReaderException("No such file", e);
        }
    }
}
