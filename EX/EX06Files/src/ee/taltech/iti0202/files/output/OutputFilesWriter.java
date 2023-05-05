package ee.taltech.iti0202.files.output;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class OutputFilesWriter {

    /**
     * Write lines to file
     * @param lines to file
     * @param filename
     * @return
     */
    public boolean writeLinesToFile(List<String> lines, String filename) {
        try {
            FileWriter myWriter = new FileWriter(filename);
            myWriter.write(String.join("\n", lines));
            myWriter.close();
            return true;
        } catch (IOException e) {
            return false;
        }
    }
}
