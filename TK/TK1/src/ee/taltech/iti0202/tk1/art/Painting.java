package ee.taltech.iti0202.tk1.art;

public class Painting {
    private String author;
    private String title;

    /**
     * Painting.
     * @param title of painting
     * @param author of painting
     */
    public Painting(String title, String author) {
        this.author = author;
        this.title = title;
    }

    /**
     * Painting.
     * @param title of painting.
     */
    public Painting(String title) {
        this.title = title;
    }

    /**
     * Getter.
     * @return author
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Setter
     * @param author of painting
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * Getter.
     * @return title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Setter
     * @param title of painting
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * String to string.
     * @return title
     */
    @Override
    public String toString() {
        if (author == null) {
            return "This is a painting named " + title + " and made by an unknown artist.";
        }
        return "This is a painting named " + title + " and made by " + author + ".";
    }
}
