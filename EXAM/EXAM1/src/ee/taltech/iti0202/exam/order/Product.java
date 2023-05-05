package ee.taltech.iti0202.exam.order;

/**
 * The type Product.
 */
public class Product {
    private String name;
    private int price;
    private boolean available;

    /**
     * Instantiates a new Product.
     *
     * @param name  the name
     * @param price the price
     */
    public Product(String name, int price) {
        this.name = name;
        this.price = price;
        this.available = true;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets price.
     *
     * @return the price
     */
    public int getPrice() {
        return price;
    }

    /**
     * Is available boolean.
     *
     * @return the boolean
     */
    public boolean isAvailable() {
        return available;
    }

    /**
     * Sets available.
     *
     * @param available the available
     */
    public void setAvailable(boolean available) {
        this.available = available;
    }
}
