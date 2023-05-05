package ee.taltech.iti0202.bookapi;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class HelperTest {

    @Test
    public void testBookListToJson() {
        Assertions.assertEquals("[]", Helper.toJson(new ArrayList<>()));
    }

    @Test
    public void testSingleBookToJson() {
        Assertions.assertEquals("{\"topics\":[]}", Helper.toJson(new Book()));
    }

    @Test
    public void testJsonToBook() {
        Book book = Helper.toBook("{\"topics\":[], \"title\":\"someTitle\"}");
        Assertions.assertEquals(new ArrayList<>(), book.getTopics());
        Assertions.assertTrue(book.getTitle().isPresent());
        Assertions.assertEquals("someTitle", book.getTitle().get());
    }
}
