package ee.taltech.iti0202.exam.library;
public class Book {
    /**
     * Creates a new book with the given title and ISBN.
     */
    private String title;
    private String isbn;
    private int timesLent;

    /**
     * Constructor.
     *
     * @param title of book
     * @param isbn of book
     */
    public Book(String title, String isbn) {
        this.title = title;
        this.isbn = isbn;
        this.timesLent = 0;
    }

    public String getTitle() {
        return title;
    }

    public String getIsbn() {
        return isbn;
    }

    public int getTimesLent() {
        return timesLent;
    }

    /**
     * Lends a book.
     */
    public void lend() {
        timesLent++;
    }


}
