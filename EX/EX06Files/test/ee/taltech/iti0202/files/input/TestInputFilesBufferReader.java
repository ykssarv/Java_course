package ee.taltech.iti0202.files.input;

import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class TestInputFilesBufferReader {
    @Test
    public void testReadTextFromFileSuccess() {
        List<String> lines = new InputFilesBufferReader().readTextFromFile("test.txt");
        Assert.assertEquals(lines, Arrays.asList("tere tere vana kere", "tere"));
    }
    @Test
    public void testReadTextFromFileNoFile() {
        FileReaderException exception = Assertions.assertThrows(FileReaderException.class, () -> {
            new InputFilesBufferReader().readTextFromFile("no.txt");
        });
        Assert.assertEquals(exception.getMessage(), "No such file");
        Assert.assertTrue(exception.getReason() instanceof IOException);
    }

}
