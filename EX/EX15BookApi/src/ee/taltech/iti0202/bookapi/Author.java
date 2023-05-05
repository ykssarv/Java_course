package ee.taltech.iti0202.bookapi;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * The type Author.
 */
public class Author {

    private String firstName;
    private String lastName;
    private List<Book> books;

    /**
     * Instantiates a new Author.
     */
    public Author() {
        books = new ArrayList<>();
    }

    /**
     * With first name author.
     *
     * @param firstName the first name
     * @return the author
     */
    public Author withFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    /**
     * With last name author.
     *
     * @param lastName the last name
     * @return the author
     */
    public Author withLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    /**
     * With book author.
     *
     * @param book the book
     * @return the author
     */
    public Author withBook(Book book) {
        books.add(book);
        return this;
    }

    /**
     * Gets first name.
     *
     * @return the first name
     */
    public Optional<String> getFirstName() {
        return Optional.ofNullable(firstName);
    }

    /**
     * Gets last name.
     *
     * @return the last name
     */
    public Optional<String> getLastName() {
        return Optional.ofNullable(lastName);
    }

    /**
     * Gets books.
     *
     * @return the books
     */
    public List<Book> getBooks() {
        return books;
    }
}
