package ee.taltech.iti0202.bookapi;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BookTest {

    @Test
    public void testBookBuildingAndGetters() {
        Book book = new Book()
            .withLink("www.book.ee")
            .withAuthor("Stephen Hawking")
            .withLanguage("English")
            .withPrice(10)
            .withPageAmount(321)
            .withPublisher("Penguin books")
            .withTitle("Black holes")
            .withTopic("Physics")
            .withYearOfPublishing(2017)
            .withId(45);

        Assertions.assertTrue(book.getLink().isPresent());
        Assertions.assertTrue(book.getAuthor().isPresent());
        Assertions.assertTrue(book.getLanguage().isPresent());
        Assertions.assertTrue(book.getPrice().isPresent());
        Assertions.assertTrue(book.getPageAmount().isPresent());
        Assertions.assertTrue(book.getPublisher().isPresent());
        Assertions.assertTrue(book.getTitle().isPresent());
        Assertions.assertEquals(1, book.getTopics().size());
        Assertions.assertTrue(book.getYearOfPublishing().isPresent());
        Assertions.assertTrue(book.getId().isPresent());

        Assertions.assertEquals("www.book.ee", book.getLink().get());
        Assertions.assertEquals("Stephen Hawking", book.getAuthor().get());
        Assertions.assertEquals("English", book.getLanguage().get());
        Assertions.assertEquals(10d, book.getPrice().get());
        Assertions.assertEquals(321, book.getPageAmount().get());
        Assertions.assertEquals("Penguin books", book.getPublisher().get());
        Assertions.assertEquals("Black holes", book.getTitle().get());
        Assertions.assertEquals("Physics", book.getTopics().get(0));
        Assertions.assertEquals(2017, book.getYearOfPublishing().get());
        Assertions.assertEquals(45, book.getId().get());
    }
}
