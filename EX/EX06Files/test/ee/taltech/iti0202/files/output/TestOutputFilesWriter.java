package ee.taltech.iti0202.files.output;

import ee.taltech.iti0202.files.input.InputFilesScanner;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

public class TestOutputFilesWriter {
    @Test
    public void testCreateFile() {
        OutputFilesWriter writer = new OutputFilesWriter();
        boolean bool = writer.writeLinesToFile(Collections.singletonList("Tere"), "tere.txt");
        Assert.assertTrue(bool);
        InputFilesScanner reader = new InputFilesScanner();
        List<String> lines = reader.readTextFromFile("tere.txt");
        Assert.assertEquals(lines, Collections.singletonList("Tere"));
    }

}
