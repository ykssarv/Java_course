package ee.taltech.iti0202.computerbuilder;
import ee.taltech.iti0202.computerbuilder.components.Component;
import ee.taltech.iti0202.computerbuilder.store.Computer;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Customer.
 */
public class Customer {
    private String name;
    private double balance;
    private final List<Component> components = new ArrayList<>();
    private final List<Computer> computers = new ArrayList<>();

    /**
     * Add component to customer.
     *
     * @param component of customer.
     */
    public void addComponent(Component component) {
        components.add(component);
    }

    /**
     * Add computer.
     *
     * @param computer the computer
     */
    public void addComputer(Computer computer) {
        computers.add(computer);
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
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets balance.
     *
     * @return the balance
     */
    public double getBalance() {
        return balance;
    }

    /**
     * Sets balance.
     *
     * @param balance the balance
     */
    public void setBalance(double balance) {
        this.balance = balance;
    }

    /**
     * Gets components.
     *
     * @return the components
     */
    public List<Component> getComponents() {
        return components;
    }

    /**
     * Gets computers.
     *
     * @return the computers
     */
    public List<Computer> getComputers() {
        return computers;
    }

    /**
     * Customer constructor.
     *
     * @param name    of customer.
     * @param balance of customer.
     */
    public Customer(String name, double balance) {
        this.name = name;
        this.balance = balance;
    }

    /**
     * Remove money from customer.
     *
     * @param balance to remove.
     */
    public void removeMoney(double balance) {
        this.balance -= balance;
    }
}
