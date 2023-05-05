package ee.taltech.iti0202.files.input;
import java.util.List;

public interface InputFilesReader {
    /**
     * Read text
     * @param filename of file
     * @return list of strings
     */
    List<String> readTextFromFile(String filename);
}
