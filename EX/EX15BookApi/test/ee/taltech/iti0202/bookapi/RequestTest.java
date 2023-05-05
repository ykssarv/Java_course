package ee.taltech.iti0202.bookapi;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class RequestTest {

    @Test
    public void testSaveToFileAndReadFromFile() {
        try {
            Request.saveToFile("testdata", "testfile.txt");
            Assertions.assertEquals("testdata", Request.readFromFile("testfile.txt"));
        } catch (IOException e) {
            Assertions.fail();
        }
    }
}
