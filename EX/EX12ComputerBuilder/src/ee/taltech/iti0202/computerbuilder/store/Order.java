package ee.taltech.iti0202.computerbuilder.store;

import ee.taltech.iti0202.computerbuilder.Customer;

/**
 * The type Order.
 */
public class Order {

    private double price;
    private String useCase;
    private String type;
    private Customer customer;

    /**
     * Instantiates a new Order.
     */
    public Order() {
        price = Double.MAX_VALUE;
        useCase = "";
        type = "PC";
        customer = null;
    }

    /**
     * With price order.
     *
     * @param price the price
     * @return the order
     */
    public Order withPrice(double price) {
        this.price = price;
        return this;
    }

    /**
     * With use case order.
     *
     * @param useCase the use case
     * @return the order
     */
    public Order withUseCase(String useCase) {
        this.useCase = useCase;
        return this;
    }

    /**
     * With type order.
     *
     * @param type the type
     * @return the order
     */
    public Order withType(String type) {
        this.type = type;
        return this;
    }

    /**
     * With customer order.
     *
     * @param customer the customer
     * @return the order
     */
    public Order withCustomer(Customer customer) {
        this.customer = customer;
        return this;
    }

    /**
     * Gets price.
     *
     * @return the price
     */
    public double getPrice() {
        return price;
    }

    /**
     * Gets use case.
     *
     * @return the use case
     */
    public String getUseCase() {
        return useCase;
    }

    /**
     * Gets type.
     *
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * Gets customer.
     *
     * @return the customer
     */
    public Customer getCustomer() {
        return customer;
    }
}
