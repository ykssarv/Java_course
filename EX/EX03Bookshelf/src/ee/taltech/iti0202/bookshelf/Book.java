package ee.taltech.iti0202.bookshelf;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Book {
    private String title;
    private String author;
    private int yearOfPublishing;
    private int price;
    private int id;
    private Person owner;
    private static int counter = 0;
    private static HashMap<String, Book> books = new HashMap<>();
    private static String lastAuthor = null;
    private static int lastYear = 0;
    private static HashMap<String, List<Book>> booksByAuthor = new HashMap<>();

    /**
     * Get title.
     * @return title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Get author.
     * @return author
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Get year of publishing.
     * @return year of publishing
     */
    public int getYearOfPublishing() {
        return yearOfPublishing;
    }

    /**
     * Get price.
     * @return price
     */
    public int getPrice() {
        return price;
    }

    /**
     * Constructor.
     * @param title of book
     * @param author of book
     * @param yearOfPublishing of book
     * @param price of book
     */
    public Book(String title, String author, int yearOfPublishing, int price) {
        this.title = title;
        this.author = author;
        this.yearOfPublishing = yearOfPublishing;
        this.price = price;
        this.id = getAndIncrementNextId();
    }

    /**
     * Get book id.
     * @return book id
     */
    public int getId() {
        return this.id;
    }

    /**
     * Get and increment next id.
     * @return book id
     */
    public static int getAndIncrementNextId() {
        return counter++;
    }

    /**
     * Get owner.
     * @return owner
     */
    public Person getOwner() {
        return this.owner;
    }

    /**
     * Set person.
     * @param owner of book
     */
    public void setPerson(Person owner) {
        this.owner = owner;
    }

    /**
     * Buy a book.
     * @param buyer of book
     * @return boolean
     */
    public boolean buy(Person buyer) {
        if (buyer == this.owner) {
            return false;
        }
        if (buyer != null && !buyer.canBuy(this)) {
            return false;
        }
        if (this.owner != null) {
            this.owner.sellBook(this);
        }
        if (buyer != null) {
            return buyer.buyBook(this);
        }
        return true;
    }

    /**
     * Book map.
     * @param title of book
     * @param author of book
     * @param yearOfPublishing of book
     * @param price of book
     * @return new book
     */
    public static Book of(String title, String author, int yearOfPublishing, int price) {
        String keyString = title + "¤" + author + "¤" + yearOfPublishing;
        if (books.containsKey(keyString)) {
            return books.get(keyString);
        }
        lastAuthor = author;
        lastYear = yearOfPublishing;
        Book newBook = new Book(title, author, yearOfPublishing, price);
        books.put(keyString, newBook);
        addByAuthor(newBook);
        return newBook;
    }

    /**
     * Make new book.
     * @param title of book
     * @param price of book
     * @return new book
     */
    public static Book of(String title, int price) {
        if (lastAuthor == null) {
            return null;
        }
        return of(title, lastAuthor, lastYear, price);
    }

    /**
     * Get books.
     * @param owner of books
     * @return books
     */
    public static List<Book> getBooksByOwner(Person owner) {
        return owner.getBooks();
    }

    /**
     * Remove book.
     * @param book to remove
     * @return boolean
     */
    public static boolean removeBook(Book book) {
        if (book == null) {
            return false;
        }
        String keyString = book.title + "¤" + book.author + "¤" + book.yearOfPublishing;
        if (!books.containsKey(keyString)) {
            return false;
        }
        if (book.owner != null) {
            book.owner.sellBook(book);
        }
        books.remove(keyString);
        removeByAuthor(book);
        return true;
    }

    /**
     * Get.
     * @param author of book
     * @return list
     */
    public static List<Book> getBooksByAuthor(String author) {
        return booksByAuthor.getOrDefault(author.toLowerCase(), new ArrayList<>());
    }

    /**
     * Add book.
     * @param book to add
     */
    private static void addByAuthor(Book book) {
        if (!booksByAuthor.containsKey(book.author.toLowerCase())) {
            booksByAuthor.put(book.author.toLowerCase(), new ArrayList<>());
        }
        List<Book> bookList = booksByAuthor.get(book.author.toLowerCase());
        bookList.add(book);
    }

    /**
     * Remove book.
     * @param book to remove
     */
    private static void removeByAuthor(Book book) {
        if (!booksByAuthor.containsKey(book.author.toLowerCase())) {
            return;
        }
        List<Book> bookList = booksByAuthor.get(book.author.toLowerCase());
        bookList.remove(book);
    }
}
