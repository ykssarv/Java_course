package ee.taltech.iti0202.files.input;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class InputFilesBufferReader implements InputFilesReader {

    @Override
    public List<String> readTextFromFile(String filename) {
        FileReader fr;
        try {
            fr = new FileReader(filename);
        } catch (FileNotFoundException e) {
            throw new FileReaderException("No such file", e);
        }
        BufferedReader bufferedReader = new BufferedReader(fr);
        Stream<String> lines = bufferedReader.lines();
        return lines.collect(Collectors.toList());
    }
}
