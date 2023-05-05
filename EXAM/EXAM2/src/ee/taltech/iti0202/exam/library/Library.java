package ee.taltech.iti0202.exam.library;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class Library {

    private List<Book> books;
    private List<Book> lentBooks;

    /**
     * Constructor.
     */
    public Library() {
        books = new ArrayList<>();
        lentBooks = new ArrayList<>();
    }
    /**
     * Adds a book to the library.
     *
     * If the same book (same instance) is already in the library, return false.
     * Otherwise the book is added to the library and return true.
     */
    public boolean addBook(Book book) {
        if (books.contains(book)) {
            return false;
        }
        books.add(book);
        return true;
    }

    /**
     * Creates a list of available books.
     * @return available books
     */
    public List<Book> availableBooks() {
        return books.stream().filter(x -> !lentBooks.contains(x)).collect(Collectors.toList());
    }

    /**
     * Tries to lend a book.
     *
     * If there is an available book with the exact name, then this book is returned.
     * Otherwise, if there is an available book where the title contains the name,
     * then this book is returned.
     * Otherwise, nothing is returned.
     *
     * If there is a book, then this book will not be available any more (it is lent out).
     */
    public Optional<Book> lendBook(String name) {
        Optional<Book> maybeBook = availableBooks().stream().filter(x -> x.getTitle().equals(name)).findFirst();
        if (maybeBook.isPresent()) {
            maybeBook.get().lend();
            lentBooks.add(maybeBook.get());
            return maybeBook;
        }
        return Optional.empty();
    }

    /**
     * Returns a book.
     *
     * If the given book is lent from the library, returns it and returns true.
     * Otherwise returns false.
     *
     * After returning, the book can be lent again.
     */
    public boolean returnBook(Book book) {
        if (lentBooks.contains(book)) {
            lentBooks.remove(book);
            return true;
        }
        return false;
    }

    /**
     * Returns a map of ISBN and corresponding count of available books.
     */
    public Map<String, Integer> getBookAmounts() {
        Map<String, Integer> bookAmounts = new HashMap<>();
        for (Book book : availableBooks()) {
            bookAmounts.put(book.getIsbn(), 1 + bookAmounts.getOrDefault(book.getIsbn(), 0));
        }
        return bookAmounts;
    }

    /**
     * Returns how many times the book has been lent.
     *
     * If the book is not in the library, returns -1.
     */
    public int getBookLendCount(Book book) {
        if (!books.contains(book)) {
            return -1;
        }
        return book.getTimesLent();
    }
}
