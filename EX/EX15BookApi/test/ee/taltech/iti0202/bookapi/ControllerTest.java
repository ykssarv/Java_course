package ee.taltech.iti0202.bookapi;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class ControllerTest {

    @Test
    public void testGetAllBooks() {
        List<Book> books = Controller.getAllBooks();
        Assertions.assertTrue(books.stream()
            .anyMatch(book -> book.getLanguage().isPresent()
                && book.getLanguage().get().equals("Eesti")
            )
        );
    }

    @Test
    public void testBooksPath() {
        String answer = Controller.get("/books");
        Assertions.assertTrue(answer.length() > 10000);
    }

    @Test
    public void testInvalidPath() {
        String answer = Controller.get("wrong");
        Assertions.assertEquals("Invalid path: wrong", answer);
    }

    @Test
    public void testGetBookById() {
        Book book = Controller.getBookById("6");
        Assertions.assertTrue(book.getTitle().isPresent());
        Assertions.assertEquals("Eesti rahva kannatuste aasta. Koguteos II", book.getTitle().get());
    }

    @Test
    public void testGetBookByIdReturnNullWhenNoSuchBook() {
        Book book = Controller.getBookById("2");
        Assertions.assertNull(book);
    }

    @Test
    public void testGetBookByIdPath() {
        String bookJson = Controller.get("/books/4");
        Assertions.assertTrue(bookJson.contains("ajalugu"));
    }

    @Test
    public void testGetOffsetPath() {
        String booksJson = Controller.get("/books?offset=6");
        List<Book> books = Helper.toBookList(booksJson);
        Assertions.assertTrue(books.stream()
            .noneMatch(book -> book.getId().isPresent()
                && book.getId().get() < 6
            )
        );
    }

    @Test
    public void testGetLimitPath() {
        String booksJson = Controller.get("/books?limit=14");
        List<Book> books = Helper.toBookList(booksJson);
        Assertions.assertEquals(14, books.size());
    }

    @Test
    public void testGetYearPath() {
        String booksJson = Controller.get("/books?year=2020");
        List<Book> books = Helper.toBookList(booksJson);
        Assertions.assertTrue(books.stream()
            .allMatch(book -> book.getYearOfPublishing().isPresent()
                && book.getYearOfPublishing().get() == 2020
            )
        );
    }

    @Test
    public void testGetSortedYear() {
        String booksJson = Controller.get("/books?sort=year");
        List<Book> books = Helper.toBookList(booksJson);
        Integer year1 = books.get(0).getYearOfPublishing().orElse(0);
        Integer year2 = books.get(1).getYearOfPublishing().orElse(0);
        Assertions.assertTrue(year2 >= year1);
    }

    @Test
    public void testGetSortedPages() {
        String booksJson = Controller.get("/books?sort=pages");
        List<Book> books = Helper.toBookList(booksJson);
        Integer pages1 = books.get(0).getPageAmount().orElse(0);
        Integer pages2 = books.get(1).getPageAmount().orElse(0);
        Assertions.assertTrue(pages2 >= pages1);
    }

    @Test
    public void testGetMaxId() {
        Integer maxId = Controller.getMaxId();
        Assertions.assertTrue(maxId > 1000);
    }

    @Test
    public void testAddBookWithPost() {
        int nextId = Controller.getMaxId() + 1;
        Controller.post("/books", new Book().withTitle("My book"));
        String bookJson = Controller.get("/books/" + nextId);
        Book book = Helper.toBook(bookJson);
        Assertions.assertTrue(book.getTitle().isPresent());
        Assertions.assertEquals("My book", book.getTitle().get());
    }

    @Test
    public void testAddBookWithPut() {
        Controller.put("/books/82", new Book().withTitle("My book"));
        String bookJson = Controller.get("/books/82");
        Book book = Helper.toBook(bookJson);
        Assertions.assertTrue(book.getTitle().isPresent());
        Assertions.assertEquals("My book", book.getTitle().get());
    }

    @Test
    public void testDeleteBookWithPath() {
        Controller.put("/books/11", new Book().withTitle("My book"));
        Controller.delete("/books/11");
        Assertions.assertEquals("null", Controller.get("/books/11"));
    }

}
