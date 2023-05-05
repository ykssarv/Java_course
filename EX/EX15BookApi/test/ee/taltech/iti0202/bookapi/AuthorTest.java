package ee.taltech.iti0202.bookapi;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AuthorTest {

    @Test
    public void testAuthorBuildingAndGetters() {
        Book book = new Book();
        Author author = new Author()
            .withFirstName("Stephen")
            .withLastName("Hawking")
            .withBook(book);

        Assertions.assertTrue(author.getFirstName().isPresent());
        Assertions.assertTrue(author.getLastName().isPresent());
        Assertions.assertEquals(1, author.getBooks().size());

        Assertions.assertEquals("Stephen", author.getFirstName().get());
        Assertions.assertEquals("Hawking", author.getLastName().get());
        Assertions.assertEquals(book, author.getBooks().get(0));
    }
}
