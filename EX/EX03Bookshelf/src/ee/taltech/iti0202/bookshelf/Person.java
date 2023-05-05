package ee.taltech.iti0202.bookshelf;

import java.util.ArrayList;
import java.util.List;

public class Person {
    private String name;
    private int money;
    private List<Book> books;

    /**
     * Person constructor.
     * @param name of person
     * @param money of person
     */
    public Person(String name, int money) {
        this.name = name;
        this.money = money;
        this.books = new ArrayList<>();
    }

    /**
     * Get name.
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Get money
     * @return money
     */
    public int getMoney() {
        return money;
    }

    /**
     * Get books
     * @return books
     */
    public List<Book> getBooks() {
        return books;
    }

    /**
     * Buy book.
     * @param book to buy
     * @return boolean
     */
    public boolean buyBook(Book book) {
        if (!canBuy(book)) {
            return false;
        }
        if (book.getOwner() != null) {
            return false;
        }
        money -= book.getPrice();
        book.setPerson(this);
        this.books.add(book);
        return true;
    }

    /**
     * Check.
     * @param book to check
     * @return boolean
     */
    public boolean canBuy(Book book) {
        if (book == null) {
            return false;
        }
        if (money < book.getPrice()) {
            return false;
        }
        return true;
    }

    /**
     * Sell book.
     * @param book to sell
     * @return boolean
     */
    public boolean sellBook(Book book) {
        if (!canSell(book)) {
            return false;
        }
        money += book.getPrice();
        book.setPerson(null);
        this.books.remove(book);
        return true;
    }

    /**
     * Can sell
     * @param book to sell
     * @return boolean
     */
    public boolean canSell(Book book) {
        if (book == null) {
            return false;
        }
        if (this != book.getOwner()) {
            return false;
        }
        return true;
    }
}
