package ee.taltech.iti0202.bookapi;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * The type Book.
 */
public class Book {

    private String language;
    private List<String> topics;
    private String title;
    private String publisher;
    private String author;
    private String link;
    private Integer yearOfPublishing;
    private Integer pageAmount;
    private Double price;
    private Integer id;

    /**
     * Instantiates a new Book.
     */
    public Book() {
        topics = new ArrayList<>();
    }

    /**
     * With language book.
     *
     * @param language the language
     * @return the book
     */
    public Book withLanguage(String language) {
        this.language = language;
        return this;
    }

    /**
     * With topic book.
     *
     * @param topic the topic
     * @return the book
     */
    public Book withTopic(String topic) {
        topics.add(topic);
        return this;
    }

    /**
     * With title book.
     *
     * @param title the title
     * @return the book
     */
    public Book withTitle(String title) {
        this.title = title;
        return this;
    }

    /**
     * With publisher book.
     *
     * @param publisher the publisher
     * @return the book
     */
    public Book withPublisher(String publisher) {
        this.publisher = publisher;
        return this;
    }

    /**
     * With author book.
     *
     * @param author the author
     * @return the book
     */
    public Book withAuthor(String author) {
        this.author = author;
        return this;
    }

    /**
     * With link book.
     *
     * @param link the link
     * @return the book
     */
    public Book withLink(String link) {
        this.link = link;
        return this;
    }

    /**
     * With year of publishing book.
     *
     * @param yearOfPublishing the year of publishing
     * @return the book
     */
    public Book withYearOfPublishing(int yearOfPublishing) {
        this.yearOfPublishing = yearOfPublishing;
        return this;
    }

    /**
     * With page amount book.
     *
     * @param pageAmount the page amount
     * @return the book
     */
    public Book withPageAmount(int pageAmount) {
        this.pageAmount = pageAmount;
        return this;
    }

    /**
     * With price book.
     *
     * @param price the price
     * @return the book
     */
    public Book withPrice(double price) {
        this.price = price;
        return this;
    }

    /**
     * With id book.
     *
     * @param id the id
     * @return the book
     */
    public Book withId(int id) {
        this.id = id;
        return this;
    }

    /**
     * Gets language.
     *
     * @return the language
     */
    public Optional<String> getLanguage() {
        return Optional.ofNullable(language);
    }

    /**
     * Gets topics.
     *
     * @return the topics
     */
    public List<String> getTopics() {
        return topics;
    }

    /**
     * Gets title.
     *
     * @return the title
     */
    public Optional<String> getTitle() {
        return Optional.ofNullable(title);
    }

    /**
     * Gets publisher.
     *
     * @return the publisher
     */
    public Optional<String> getPublisher() {
        return Optional.ofNullable(publisher);
    }

    /**
     * Gets author.
     *
     * @return the author
     */
    public Optional<String> getAuthor() {
        return Optional.ofNullable(author);
    }

    /**
     * Gets link.
     *
     * @return the link
     */
    public Optional<String> getLink() {
        return Optional.ofNullable(link);
    }

    /**
     * Gets year of publishing.
     *
     * @return the year of publishing
     */
    public Optional<Integer> getYearOfPublishing() {
        return Optional.ofNullable(yearOfPublishing);
    }

    /**
     * Gets page amount.
     *
     * @return the page amount
     */
    public Optional<Integer> getPageAmount() {
        return Optional.ofNullable(pageAmount);
    }

    /**
     * Gets price.
     *
     * @return the price
     */
    public Optional<Double> getPrice() {
        return Optional.ofNullable(price);
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public Optional<Integer> getId() {
        return Optional.ofNullable(id);
    }
}
